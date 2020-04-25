package de.cwkr.intro.bv.spring.service.training;

import io.swagger.annotations.Api;
import java.util.Collections;
import java.util.List;
import java.util.Set;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Api(tags = "WorkshopService")
@RestController
public class WorkshopServiceController {
    private final WorkshopService workshopService;

    public WorkshopServiceController(WorkshopService workshopService) {
        this.workshopService = workshopService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping(path = "workshops/{id:\\d+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkshopDto> getWorkshopById(@PathVariable("id") Long id) {
        return ResponseEntity.of(workshopService.getWorkshopById(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "workshops", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkshopDto> addWorkshop(@RequestBody WorkshopDto workshopDto) {
        WorkshopDto createdWorkshopDto = workshopService.addWorkshop(workshopDto);
        return ResponseEntity.created(
            ServletUriComponentsBuilder.fromCurrentRequestUri()
                                       .pathSegment(createdWorkshopDto.getId().toString())
                                       .build(Collections.emptyMap())
        ).body(createdWorkshopDto);
    }

    @DeleteMapping(path = "workshops/{id:\\d+}")
    public ResponseEntity<Void> deleteWorkshopById(@PathVariable("id") Long id) {
        workshopService.deleteWorkshopById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "workshops", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WorkshopDto>> getWorkshops() {
        return ResponseEntity.ok(workshopService.getWorkshops());
    }

    @GetMapping(path = "workshops/{workshopId:\\d+}/participants", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ParticipantDto>> getParticipants(@PathVariable("workshopId") Long workshopId) {
        return ResponseEntity.ok(workshopService.getParticipants(workshopId));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "workshops/{workshopId:\\d+}/participants", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ParticipantDto> addParticipant(@PathVariable("workshopId") Long workshopId, @RequestBody ParticipantDto participantDto) {
        ParticipantDto createdParticipantDto = workshopService.addParticipant(workshopId, participantDto);
        return ResponseEntity.created(
            ServletUriComponentsBuilder.fromCurrentRequestUri()
                                       .pathSegment(createdParticipantDto.getId().toString())
                                       .build(Collections.emptyMap())
        ).body(createdParticipantDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(path = "workshops/{workshopId:\\d+}/participants/{ids}")
    public void addParticipantIds(@PathVariable Long workshopId, @PathVariable Set<Long> ids) {
        workshopService.addParticipantIds(workshopId, ids);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "workshops/{workshopId:\\d+}/participants/{ids}")
    public void deleteParticipantById(@PathVariable("workshopId") Long workshopId, @PathVariable("ids") Set<Long> ids) {
        workshopService.deleteParticipantIds(workshopId, ids);
    }
}
