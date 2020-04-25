package de.cwkr.intro.bv.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.CONSTRUCTOR,
         ElementType.METHOD,
         ElementType.PARAMETER,
         ElementType.FIELD,
         ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AssertAfterLocalDateValidator.class)
@Documented
public @interface AssertAfter {
    String message() default "{de.cwkr.intro.bv.constraints.AssertAfter.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String value();
}
