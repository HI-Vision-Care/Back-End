package com.hivision.hivision.payload.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MedicalRecordRequest {
    String diagnosis; // Chẩn đoán bệnh
    String note;
}
