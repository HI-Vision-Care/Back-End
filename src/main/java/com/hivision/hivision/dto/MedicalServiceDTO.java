package com.hivision.hivision.dto;

import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.hivision.hivision.pojo.MedicalService}
 */
@Value
public class MedicalServiceDTO implements Serializable {
    Long serviceID;
    String name;
    String description;
    Double price;
    Boolean isActive;
    Boolean isRequireDoctor;
    Boolean isOnline;
    Instant createAt;
    String img;
}