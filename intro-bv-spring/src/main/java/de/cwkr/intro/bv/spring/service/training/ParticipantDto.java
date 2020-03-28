package de.cwkr.intro.bv.spring.service.training;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.cwkr.intro.bv.spring.service.staffing.Gender;
import java.time.LocalDate;
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
public class ParticipantDto {
    private Long id;
    @NotBlank
    @JsonProperty(required = true)
    private String firstName;
    @NotBlank
    @JsonProperty(required = true)
    private String lastName;
    @NotNull
    private LocalDate birthday;
    @NotNull
    private Gender gender;
}
