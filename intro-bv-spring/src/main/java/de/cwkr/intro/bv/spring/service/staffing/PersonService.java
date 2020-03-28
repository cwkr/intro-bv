package de.cwkr.intro.bv.spring.service.staffing;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface PersonService {
    @NotNull
    PersonDto addPerson(@Valid @NotNull PersonDto personDto);
    @NotNull
    Optional<PersonDto> getPersonById(@NotNull Long id);
    void deletePersonById(@NotNull Long id);
    void updatePersonById(@NotNull Long id, @Valid @NotNull PersonDto personDto);
    @NotNull
    default List<PersonDto> getPeople() {
        return getPeople(Collections.emptySet());
    }
    @NotNull
    List<PersonDto> getPeople(@NotNull Set<Long> ids);
}
