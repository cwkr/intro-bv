package de.cwkr.intro.bv.spring.service.training;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class TopicDto {
    private Long id;
    @NotBlank
    @JsonProperty(required = true)
    private String description;
    @NotNull
    private Boolean active;
}
