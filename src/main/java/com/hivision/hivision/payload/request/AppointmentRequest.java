package com.hivision.hivision.payload.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppointmentRequest {
    Long serviceID;
    String doctorID;
    String slot;
    Instant appointmentDate;
    Boolean isAnonymous;
    String note;

}
