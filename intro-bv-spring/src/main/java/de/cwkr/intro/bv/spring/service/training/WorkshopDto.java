package de.cwkr.intro.bv.spring.service.training;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.cwkr.intro.bv.constraints.DatePropertyAfter;
import de.cwkr.intro.bv.spring.domain.training.TopicRepository;
import de.cwkr.intro.bv.spring.validation.constraints.ExistingId;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@DatePropertyAfter(beforeProperty = "firstDay", afterProperty = "lastDay")
public class WorkshopDto {
    private Long id;
    @NotBlank
    @JsonProperty(required = true)
    private String title;
    @NotNull
    @JsonFormat(shape = Shape.STRING, pattern = "HH:mm")
    private LocalTime begin;
    @NotNull
    @JsonFormat(shape = Shape.STRING, pattern = "HH:mm")
    private LocalTime end;
    @NotNull
    private LocalDate firstDay;
    private LocalDate lastDay;
    @NotNull
    @ExistingId(repository = TopicRepository.class)
    private Long topicId;
}
