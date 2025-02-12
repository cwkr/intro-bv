package de.cwkr.intro.bv.spring.validation.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import org.springframework.data.repository.CrudRepository;

@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ExistingIdLongValidator.class})
@Documented
public @interface ExistingId {
    String message() default "{de.cwkr.intro.bv.spring.validation.constraints.ExistingId.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends CrudRepository<?, Long>> repository();
}
