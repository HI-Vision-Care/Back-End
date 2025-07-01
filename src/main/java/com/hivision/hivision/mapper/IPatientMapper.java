package com.hivision.hivision.mapper;

import com.hivision.hivision.dto.PatientDTO;
import com.hivision.hivision.payload.request.PatientRequest;
import com.hivision.hivision.pojo.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface IPatientMapper {
    PatientDTO toPatientDTO(Patient patient);
    List<PatientDTO> toPatientDTO(List<Patient> patients);
    void updatePatient(@MappingTarget Patient patient, PatientRequest request);
}
