package com.hivision.hivision.mapper;

import com.hivision.hivision.dto.PatientDTO;
import com.hivision.hivision.payload.request.PatientRequest;
import com.hivision.hivision.pojo.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface IPatientMapper {
    @Mapping(target = "underlyingDiseases", expression = "java(mapDiseases(patient))")
    PatientDTO toPatientDTO(Patient patient);
    List<PatientDTO> toPatientDTO(List<Patient> patients);
    void updatePatient(@MappingTarget Patient patient, PatientRequest request);

    // Hàm phụ trợ ánh xạ bệnh nền
    default List<String> mapDiseases(Patient patient) {
        if (patient.getPatientDiseases() == null) return Collections.emptyList();
        return patient.getPatientDiseases().stream()
                .map(pd -> pd.getDisease().getName())
                .collect(Collectors.toList());
    }
}
