package com.hivision.hivision.payload.response;

import com.hivision.hivision.dto.DoctorDTO;
import com.hivision.hivision.dto.MedicalServiceDTO;
import com.hivision.hivision.dto.PatientDTO;
import com.hivision.hivision.pojo.MedicalService;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FacilityResponse {
    String name;
    String address;
    String phone;
    List<DoctorDTO> doctors;
    List<MedicalServiceDTO> medicalServices;
}
