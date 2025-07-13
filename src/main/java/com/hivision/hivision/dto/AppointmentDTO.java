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
//    Long serviceID;
//    String doctorID;
    PatientDTO patient;
    MedicalServiceDTO medicalService;
    DoctorDTO doctor;

    Instant appointmentDate;
    Boolean isAnonymous;
    Boolean isRecordCreated;
    Boolean isPrescriptionCreated;
    String note;
    String urlLink;
    String status;
    String paymentStatus;
    Instant createAt;
}
