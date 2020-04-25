package de.cwkr.intro.bv.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DatePropertyAfterValidator.class)
@Documented
public @interface DatePropertyAfter {
    String message() default "{de.cwkr.intro.bv.constraints.DatePropertyAfter.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String beforeProperty();

    String afterProperty();
}
