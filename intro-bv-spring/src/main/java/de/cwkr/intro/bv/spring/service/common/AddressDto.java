package de.cwkr.intro.bv.spring.service.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.cwkr.intro.bv.constraints.Postleitzahl;
import de.cwkr.intro.bv.groups.Germany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    @NotBlank
    @JsonProperty(required = true)
    private String street;
    @NotBlank
    @JsonProperty(required = true)
    private String city;
    @NotBlank
    @Postleitzahl(groups = Germany.class)
    @JsonProperty(required = true)
    private String postalCode;
}
