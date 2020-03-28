package de.cwkr.intro.bv.spring.service.staffing;

import de.cwkr.intro.bv.spring.service.training.ParticipantDto;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class PersonServiceController {
    private final PersonService personService;

    public PersonServiceController(PersonService personService) {
        this.personService = personService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "people", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDto> addPerson(@RequestBody PersonDto personDto) {
        PersonDto createdPersonDto = personService.addPerson(personDto);
        return ResponseEntity.created(
            ServletUriComponentsBuilder.fromCurrentRequestUri()
                                       .pathSegment(createdPersonDto.getId().toString())
                                       .build(Collections.emptyMap())
        ).body(createdPersonDto);
    }

    @GetMapping(path = "people/{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDto> getPersonById(@PathVariable("id") Long id) {
        return ResponseEntity.of(personService.getPersonById(id));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "people/{id:\\d+}")
    public void deletePersonById(@PathVariable("id") Long id) {
        personService.deletePersonById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(path = "people/{id:\\d+}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updatePersonById(@PathVariable("id") Long id, @RequestBody PersonDto personDto) {
        personService.updatePersonById(id, personDto);
    }

    @GetMapping(path = "people", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonDto> getPeople(@RequestParam(required = false) Set<Long> ids) {
        return personService.getPeople(Objects.isNull(ids) ? Collections.emptySet() : ids);
    }
}
