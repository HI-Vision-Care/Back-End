package com.hivision.hivision.mapper;

import com.hivision.hivision.dto.MedicalServiceDTO;
import com.hivision.hivision.pojo.MedicalService;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface IMedicalServiceMapper {
    MedicalServiceDTO toMedicalServiceDTO(MedicalService medicalService);
    List<MedicalServiceDTO> toMedicalServiceDTOs(List<MedicalService> medicalServices);
}
