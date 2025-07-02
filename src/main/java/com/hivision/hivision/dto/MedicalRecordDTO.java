package com.hivision.hivision.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MedicalRecordDTO {
    String recordId;
    String appointmentId;
    String diagnosis; // chẩn đoán bệnh
    Instant createDate;
    String note;
}
