package com.hivision.hivision.dto;

import com.hivision.hivision.pojo.MedicalRecord;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LabResultDTO {

    //MedicalRecord medicalRecord;
    String recordId;
    String testType;
    String resultText;
    String resultValue;
    String unit;
    String referenceRange;
    Instant testDate;
    String performedBy;
}
