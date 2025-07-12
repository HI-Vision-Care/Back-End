package com.hivision.hivision.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PatientDTO {
    String patientID;
    String name;
    String dob;
    String gender;
    String medNo;
    String medDate;
    String medFac;
    List<String> underlyingDiseases;
}
