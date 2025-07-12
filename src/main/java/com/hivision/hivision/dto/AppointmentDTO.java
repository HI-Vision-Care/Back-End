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
    String appointmentID;
//    String patientID;
    PatientDTO patient;
    Long serviceID;
    String doctorID;
    Instant appointmentDate;
    Boolean isAnonymous;
    Boolean isRecordCreated;
    String note;
    String urlLink;
    String status;
    String paymentStatus;
    Instant createAt;
}
