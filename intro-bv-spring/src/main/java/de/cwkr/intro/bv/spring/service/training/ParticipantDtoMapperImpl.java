package de.cwkr.intro.bv.spring.service.training;

import de.cwkr.intro.bv.spring.service.staffing.PersonDto;
import org.springframework.stereotype.Component;

@Component
public class ParticipantDtoMapperImpl implements ParticipantDtoMapper {
    @Override
    public PersonDto toPersonDto(ParticipantDto participantDto) {
        return PersonDto.builder()
                        .withId(participantDto.getId())
                        .withFirstName(participantDto.getFirstName())
                        .withLastName(participantDto.getLastName())
                        .withBirthday(participantDto.getBirthday())
                        .withGender(participantDto.getGender())
                        .build();
    }

    @Override
    public ParticipantDto fromPersonDto(PersonDto personDto) {
        return ParticipantDto.builder()
                             .withId(personDto.getId())
                             .withFirstName(personDto.getFirstName())
                             .withLastName(personDto.getLastName())
                             .withBirthday(personDto.getBirthday())
                             .withGender(personDto.getGender())
                             .build();
    }
}
