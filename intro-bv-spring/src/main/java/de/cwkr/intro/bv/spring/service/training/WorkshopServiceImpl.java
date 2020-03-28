package de.cwkr.intro.bv.spring.service.training;

import de.cwkr.intro.bv.spring.infrastructure.NotFoundException;
import de.cwkr.intro.bv.spring.service.staffing.PersonService;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
public class WorkshopServiceImpl implements WorkshopService {
    private final Map<Long, Pair<WorkshopDto, Set<Long>>> workshopRepository = new ConcurrentHashMap<>();
    private final AtomicLong workshopSeq = new AtomicLong();
    private final PersonService personService;
    private final ParticipantDtoMapper participantDtoMapper;

    public WorkshopServiceImpl(PersonService personService, ParticipantDtoMapper participantDtoMapper) {
        this.personService = personService;
        this.participantDtoMapper = participantDtoMapper;
    }

    private Set<Long> getParticipantIds(Long workshopId) {
        return Optional.ofNullable(
            workshopRepository.getOrDefault(workshopId, ImmutablePair.nullPair())
                              .getRight()
        )
                       .orElseThrow(() -> new NotFoundException("Workshop not found"));
    }

    @Override
    public Optional<WorkshopDto> getWorkshopById(Long id) {
        logger.debug("getWorkshopById(id = {})", id);
        return Optional.ofNullable(
            workshopRepository.getOrDefault(id, ImmutablePair.nullPair())
                              .getLeft()
        );
    }

    @Override
    public WorkshopDto addWorkshop(WorkshopDto workshopDto) {
        logger.debug("addWorkshop(workshopDto = {})", workshopDto);
        Long id = workshopSeq.incrementAndGet();
        logger.info("workshop id = {}", id);
        workshopDto.setId(id);
        workshopRepository.put(id, ImmutablePair.of(workshopDto, new CopyOnWriteArraySet<>()));
        return workshopDto;
    }

    @Override
    public void updateWorkshopById(Long id, WorkshopDto workshopDto) {
        logger.debug("updateWorkshopById(id = {}, workshopDto = {})", id, workshopDto);
        if (!workshopRepository.containsKey(id)) {
            throw new NotFoundException("Workshop not found");
        }
        Set<Long> participantIds = getParticipantIds(id);
        workshopDto.setId(id);
        workshopRepository.put(id, ImmutablePair.of(workshopDto, participantIds));
    }

    @Override
    public void deleteWorkshopById(Long id) {
        logger.debug("deleteWorkshopById(id = {})", id);
        if (!workshopRepository.containsKey(id)) {
            throw new NotFoundException("Workshop not found");
        }
        workshopRepository.remove(id);
    }

    @Override
    public List<WorkshopDto> getWorkshops() {
        logger.debug("getWorkshops()");
        return workshopRepository.values()
                                 .stream()
                                 .map(Pair::getLeft)
                                 .collect(Collectors.toList());
    }

    @Override
    public List<ParticipantDto> getParticipants(Long workshopId) {
        logger.debug("getParticipants(workshopId = {})", workshopId);
        Set<Long> participantIds = getParticipantIds(workshopId);
        if (participantIds.isEmpty()) {
            return Collections.emptyList();
        }
        return personService.getPeople(participantIds)
                            .stream()
                            .map(participantDtoMapper::fromPersonDto)
                            .collect(Collectors.toList());
    }

    @Override
    public ParticipantDto addParticipant(Long workshopId, ParticipantDto participantDto) {
        logger.debug("addParticipant(workshopId = {}, participantDto = {})", workshopId, participantDto);
        ParticipantDto savedParticipant = participantDtoMapper.fromPersonDto(
            personService.addPerson(
                participantDtoMapper.toPersonDto(participantDto)
            )
        );
        getParticipantIds(workshopId).add(savedParticipant.getId());
        return savedParticipant;
    }

    @Override
    public void addParticipantIds(Long workshopId, Set<Long> ids) {
        logger.debug("addParticipantIds(workshopId = {}, ids = {})", workshopId, ids);
        getParticipantIds(workshopId).addAll(ids);
    }

    @Override
    public void deleteParticipantIds(Long workshopId, Set<Long> ids) {
        logger.debug("deleteParticipantIds(workshopId = {}, ids = {})", workshopId, ids);
        getParticipantIds(workshopId).removeAll(ids);
    }
}
