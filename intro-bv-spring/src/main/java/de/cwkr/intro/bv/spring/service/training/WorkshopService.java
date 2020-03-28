package de.cwkr.intro.bv.spring.service.training;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface WorkshopService {
    Optional<WorkshopDto> getWorkshopById(@NotNull Long id);

    WorkshopDto addWorkshop(@Valid @NotNull WorkshopDto workshopDto);

    void deleteWorkshopById(@NotNull Long id);

    void updateWorkshopById(@NotNull Long id, @Valid @NotNull WorkshopDto workshopDto);

    List<WorkshopDto> getWorkshops();

    List<ParticipantDto> getParticipants(@NotNull Long workshopId);

    ParticipantDto addParticipant(@NotNull Long workshopId, @Valid @NotNull ParticipantDto personDto);

    void addParticipantIds(@NotNull Long workshopId, @NotNull Set<@NotNull Long> ids);

    void deleteParticipantIds(@NotNull Long workshopId, @NotNull Set<@NotNull Long> ids);
}
