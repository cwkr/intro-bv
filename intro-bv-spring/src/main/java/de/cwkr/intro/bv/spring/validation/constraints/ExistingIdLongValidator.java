package de.cwkr.intro.bv.spring.validation.constraints;

import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.repository.CrudRepository;

public class ExistingIdLongValidator implements ConstraintValidator<ExistingId, Long>, ApplicationContextAware {
    private ApplicationContext applicationContext;
    private CrudRepository<?, Long> repository;

    @Override
    public void initialize(ExistingId constraintAnnotation) {
        repository = applicationContext.getBean(constraintAnnotation.repository());
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        return Objects.isNull(id) || repository.existsById(id);
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
