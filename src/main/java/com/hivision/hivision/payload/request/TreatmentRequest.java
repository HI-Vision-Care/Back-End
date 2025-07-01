package com.hivision.hivision.payload.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TreatmentRequest {
    String arvID;
    Instant startDate;
    Instant endDate;
    @Size(max = 255)
    String prescribeBy;
    @Size(max = 255)
    String status;
    String note;
}
