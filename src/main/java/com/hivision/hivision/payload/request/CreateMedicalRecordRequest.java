package com.hivision.hivision.payload.request;

import com.hivision.hivision.dto.LabResultDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateMedicalRecordRequest {
    String diagnosis;
    String note;
    List<LabResultDTO> labResults;
}
