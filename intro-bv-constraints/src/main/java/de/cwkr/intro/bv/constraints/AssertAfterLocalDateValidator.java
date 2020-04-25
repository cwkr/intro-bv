package de.cwkr.intro.bv.constraints;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

public class AssertAfterLocalDateValidator implements ConstraintValidator<AssertAfter, LocalDate> {
    private LocalDate afterDate;

    @Override
    public void initialize(AssertAfter constraintAnnotation) {
        if (StringUtils.isNotBlank(constraintAnnotation.value())) {
            afterDate = LocalDate.parse(constraintAnnotation.value(), DateTimeFormatter.ISO_LOCAL_DATE);
        }
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        return Objects.isNull(value) || Objects.isNull(afterDate) || value.isAfter(afterDate);
    }
}
