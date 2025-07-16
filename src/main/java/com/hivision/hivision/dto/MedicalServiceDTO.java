package com.hivision.hivision.dto;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

/**
 * DTO for {@link com.hivision.hivision.pojo.MedicalService}
 */
@Value
@Builder
public class MedicalServiceDTO implements Serializable {
    Long serviceID;
    String name;
    String description;
    Double price;
    String type;
    Boolean isActive;
    Boolean isRequireDoctor;
    Boolean isOnline;
    Instant createAt;
    String img;

    List<TestItemDTO> testItems;
}