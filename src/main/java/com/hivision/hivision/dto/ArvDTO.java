package com.hivision.hivision.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hivision.hivision.pojo.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

/**
 * DTO for {@link ARV}
 */
@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArvDTO implements Serializable {
    @Size(max = 255)
    String arvId;
    @Size(max = 255)
    String genericName;
    @Size(max = 50)
    String drugClass;
    @Size(max = 50)
    String dosageStrength;
    @Size(max = 50)
    String admRoute;
    @Size(max = 255)
    String rcmDosage;
    @Size(max = 50)
    String shelfLife;
    @Size(max = 255)
    String fundingSource;
    @Size(max = 50)
    String regimenLevel;
    Instant lastUpdated;

}