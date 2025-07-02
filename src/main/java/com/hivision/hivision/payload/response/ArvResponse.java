package com.hivision.hivision.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hivision.hivision.pojo.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArvResponse {
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
    List<String> contraindication;
    List<String> indication;
    List<String> sideEffect;
    List<String> drugInteraction;
    List<String> targetPopulation;
}
