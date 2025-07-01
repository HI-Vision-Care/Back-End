package com.hivision.hivision.payload.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PrescriptionRequest {
    String patientId;
    String dosage;
    String duration;
    String prescribeBy;
}
