package com.hivision.hivision.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PatientDTO {
    String patientID;
    String name;
    Instant dob;
    String gender;
    String medNo;
    Instant medDate;
    String medFac;
}
