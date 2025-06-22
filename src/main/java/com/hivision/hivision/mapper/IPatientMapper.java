package com.hivision.hivision.mapper;

import com.hivision.hivision.dto.PatientDTO;
import com.hivision.hivision.pojo.Patient;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface IPatientMapper {
    PatientDTO toPatientDTO(Patient patient);
    List<PatientDTO> toPatientDTO(List<Patient> patients);
}
