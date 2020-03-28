package de.cwkr.intro.bv.spring.service.training;

import de.cwkr.intro.bv.spring.service.staffing.PersonDto;
import javax.validation.constraints.NotNull;

public interface ParticipantDtoMapper {
    @NotNull
    PersonDto toPersonDto(@NotNull ParticipantDto participantDto);
    @NotNull
    ParticipantDto fromPersonDto(@NotNull PersonDto personDto);
}
