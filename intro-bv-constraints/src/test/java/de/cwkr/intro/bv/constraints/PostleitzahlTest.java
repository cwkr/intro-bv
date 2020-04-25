package de.cwkr.intro.bv.constraints;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.cwkr.intro.bv.groups.Germany;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.junit.jupiter.api.Test;

public class PostleitzahlTest {
    private static final Validator validator = Validation.buildDefaultValidatorFactory()
                                                         .getValidator();

    @Test
    void valid() {
        AddressDto addressDto = AddressDto.builder()
                                          .withStreet("Platz der Republik 1")
                                          .withPostalCode("11011")
                                          .withCity("Berlin")
                                          .build();
        Set<ConstraintViolation<AddressDto>> violations = validator.validate(addressDto, Germany.class);
        assertTrue(violations.isEmpty());
    }

    @Test
    void invalid() {
        AddressDto addressDto = AddressDto.builder()
                                          .withPostalCode("5")
                                          .withCity("Mainz")
                                          .build();
        Set<ConstraintViolation<AddressDto>> violations = validator.validate(addressDto, Germany.class);
        violations.forEach(v -> System.out.printf("%s: %s%n", v.getPropertyPath(), v.getMessage()));
        assertFalse(violations.isEmpty());
    }
}
