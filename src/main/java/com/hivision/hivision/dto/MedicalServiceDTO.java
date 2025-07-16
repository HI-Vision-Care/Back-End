package com.hivision.hivision.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

/**
 * DTO for {@link com.hivision.hivision.pojo.MedicalService}
 */
//@Value
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MedicalServiceDTO implements Serializable {
    Long serviceID;
    String name;
    String description;
    Double price;
    String type;
    String specialty;
    Boolean isActive;
    Boolean isRequireDoctor;
    Boolean isOnline;
    Instant createAt;
    String img;

    List<TestItemDTO> testItems;
}