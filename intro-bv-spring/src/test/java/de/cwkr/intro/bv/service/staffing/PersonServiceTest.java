package de.cwkr.intro.bv.service.staffing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.cwkr.intro.bv.spring.service.staffing.PersonDto;
import de.cwkr.intro.bv.spring.service.staffing.PersonService;
import de.cwkr.intro.bv.spring.service.staffing.PersonServiceImpl;
import javax.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PersonServiceImpl.class, ValidationAutoConfiguration.class})
public class PersonServiceTest {
    @Autowired
    PersonService personService;

    @Test
    void addPerson_invalid() {
        ConstraintViolationException ex = assertThrows(ConstraintViolationException.class, () -> {
            personService.addPerson(new PersonDto());
        });
        assertEquals(4, ex.getConstraintViolations().size());
    }
}
