package com.hivision.hivision.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppointmentDTO {
    String patientID;
    Long serviceID;
    String doctorID;
    Instant appointmentDate;
    Boolean isAnonymous;
    String note;
    Instant createAt;
}
