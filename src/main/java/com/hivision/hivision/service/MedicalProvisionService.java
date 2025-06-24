package com.hivision.hivision.service;

import com.hivision.hivision.dto.MedicalServiceDTO;
import com.hivision.hivision.pojo.MedicalService;
import com.hivision.hivision.repository.IMedicalServiceRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class MedicalProvisionService implements IMedicalService {
    IMedicalServiceRepo medicalServiceRepo;

    @Override
    public List<MedicalService> getAllMedicalServices() {
        return medicalServiceRepo.findAll();
    }

    @Override
    public MedicalServiceDTO getMedicalServiceById(Long id) {
        return medicalServiceRepo.findMedicalServiceByServiceID(id);
    }


}
