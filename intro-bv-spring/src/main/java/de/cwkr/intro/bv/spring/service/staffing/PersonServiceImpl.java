package de.cwkr.intro.bv.spring.service.staffing;

import de.cwkr.intro.bv.spring.infrastructure.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
public class PersonServiceImpl implements PersonService {
    private final AtomicLong personSeq = new AtomicLong();
    private final Map<Long, PersonDto> personRepository = new ConcurrentHashMap<>();

    @Override
    public PersonDto addPerson(PersonDto personDto) {
        logger.debug("addPerson(personDto = {})", personDto);
        Long id = personSeq.incrementAndGet();
        logger.info("person id = {}", id);
        personDto.setId(id);
        personRepository.put(id, personDto);
        return personDto;
    }

    @Override
    public Optional<PersonDto> getPersonById(Long id) {
        logger.debug("getPersonById(id = {})", id);
        return Optional.ofNullable(personRepository.get(id));
    }

    @Override
    public void deletePersonById(Long id) {
        logger.debug("deletePersonById(id = {})", id);
        if (!personRepository.containsKey(id)) {
            throw new NotFoundException("Person not found");
        }
        personRepository.remove(id);
    }

    @Override
    public void updatePersonById(Long id, PersonDto personDto) {
        logger.debug("updatePersonById(id = {}, personDto = {})", id, personDto);
        if (!personRepository.containsKey(id)) {
            throw new NotFoundException("Person not found");
        }
        personDto.setId(id);
        personRepository.put(id, personDto);
    }

    @Override
    public List<PersonDto> getPeople(Set<Long> ids) {
        logger.debug("getPeople()");
        if (ids.isEmpty()) {
           return new ArrayList<>(personRepository.values());
        }
        return personRepository.values()
                               .stream()
                               .filter(p -> ids.contains(p.getId()))
                               .collect(Collectors.toList());
    }
}
