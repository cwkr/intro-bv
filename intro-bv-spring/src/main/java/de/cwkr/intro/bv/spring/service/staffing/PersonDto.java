package de.cwkr.intro.bv.spring.service.staffing;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.cwkr.intro.bv.spring.service.common.AddressDto;
import de.cwkr.intro.bv.spring.service.common.Gender;
import java.time.LocalDate;
import javax.validation.Valid;
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
public class PersonDto {
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
    @Valid
    private AddressDto postalAddress;
}
