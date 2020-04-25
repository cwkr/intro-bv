package de.cwkr.intro.bv.constraints;

import java.time.LocalDate;
import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

public class DatePropertyAfterValidator implements ConstraintValidator<DatePropertyAfter, Object> {
    private String beforePropertyName;
    private String afterPropertyName;

    @Override
    public void initialize(DatePropertyAfter constraintAnnotation) {
        beforePropertyName = constraintAnnotation.beforeProperty();
        afterPropertyName = constraintAnnotation.afterProperty();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        boolean valid = true;

        if (!Objects.isNull(value)
            && StringUtils.isNotBlank(beforePropertyName)
            && StringUtils.isNotBlank(afterPropertyName)) {
            try {
                Object beforeDateObject = PropertyUtils.getProperty(value, beforePropertyName);
                Object afterDateObject = PropertyUtils.getProperty(value, afterPropertyName);
                if (ObjectUtils.allNotNull(beforeDateObject, afterDateObject)) {
                    LocalDate beforeDate = (LocalDate) beforeDateObject;
                    LocalDate afterDate = (LocalDate) afterDateObject;
                    valid = afterDate.isAfter(beforeDate);
                    context.disableDefaultConstraintViolation();
                    context
                        .buildConstraintViolationWithTemplate("must be before " + afterDate.toString())
                        .addPropertyNode(beforePropertyName)
                        .addConstraintViolation();
                    context
                        .buildConstraintViolationWithTemplate("must be after " + beforeDate.toString())
                        .addPropertyNode(afterPropertyName)
                        .addConstraintViolation();
                }
            } catch (ReflectiveOperationException | ClassCastException ex) {
                throw new ValidationException("Error while performing cross property validation", ex);
            }
        }

        return valid;
    }
}
