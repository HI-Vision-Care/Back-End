package com.hivision.hivision.service.iservice;

import com.hivision.hivision.dto.MedicalServiceDTO;
import com.hivision.hivision.pojo.MedicalService;

import java.util.List;

public interface IMedicalService {
    List<MedicalService> getAllMedicalServices();
    MedicalServiceDTO getMedicalServiceById(Long id);
    List<MedicalServiceDTO> getAllServicesWithTests();
}
