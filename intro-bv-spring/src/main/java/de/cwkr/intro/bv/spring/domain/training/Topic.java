package de.cwkr.intro.bv.spring.domain.training;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class Topic {
    private Long id;
    private String description;
    private Boolean active;
}
