package com.hivision.hivision.payload.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

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
    Instant appointmentDate;
    Boolean isAnonymous;
    String note;
    Instant createAt;
    String status;

}
