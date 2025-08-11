package com.hivision.hivision.service.cservice;

import com.hivision.hivision.dto.DoctorDTO;
import com.hivision.hivision.enums.ErrorCode;
import com.hivision.hivision.exception.AppException;
import com.hivision.hivision.mapper.IDoctorMapper;
import com.hivision.hivision.payload.response.FacilityResponse;
import com.hivision.hivision.pojo.Doctor;
import com.hivision.hivision.pojo.MedicalFacility;
import com.hivision.hivision.pojo.MedicalService;
import com.hivision.hivision.repository.IDoctorRepo;
import com.hivision.hivision.repository.IMedicalFacilityRepo;
import com.hivision.hivision.repository.IMedicalServiceRepo;
import com.hivision.hivision.service.iservice.IMedicalFacilityService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class MedicalFacilityService implements IMedicalFacilityService {

    IDoctorRepo doctorRepo;
    IMedicalServiceRepo medicalServiceRepo;
    IMedicalFacilityRepo medicalFacilityRepo;

    IDoctorMapper doctorMapper;

    @Override
    public FacilityResponse getByFacility(String facilityID) {
        MedicalFacility facility = medicalFacilityRepo.findById(facilityID)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_EMAIL));

        List<Doctor> doctors = doctorRepo.findByMedicalFacility(facility);
        List<DoctorDTO> doctorDTOS = doctorMapper.toDoctorDTO(doctors);


//        List<MedicalService>

        return FacilityResponse.builder()
                .name(facility.getName())
                .phone(facility.getPhone())
                .address(facility.getAddress())
                .doctors(doctorDTOS)
                .build();
    }

    @Override
    public List<MedicalFacility> getAll() {
        return medicalFacilityRepo.findAll();
    }


}
