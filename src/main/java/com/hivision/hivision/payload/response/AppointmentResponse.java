package com.hivision.hivision.payload.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppointmentResponse {
    String appointmentID;
    String patientName;
    String doctorName;
    String serviceName;
    LocalDate appointmentDate;
    Boolean isAnonymous;
    String note;
    Instant createAt;
    String status;

}
