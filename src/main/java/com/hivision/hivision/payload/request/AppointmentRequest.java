package com.hivision.hivision.payload.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppointmentRequest {
    String facilityID;
    Long serviceID;
    String doctorID;
    String slot;
    LocalDate appointmentDate;
    Boolean isAnonymous;
    String note;

}
