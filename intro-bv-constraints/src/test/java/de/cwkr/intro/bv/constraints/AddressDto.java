package de.cwkr.intro.bv.constraints;

import de.cwkr.intro.bv.groups.Germany;
import javax.validation.constraints.NotBlank;
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
    private String street;
    @NotBlank
    private String city;
    @NotBlank
    @Postleitzahl(groups = Germany.class)
    private String postalCode;
}
