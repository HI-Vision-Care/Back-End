package com.hivision.hivision.service.cservice;

import com.hivision.hivision.dto.DoctorDTO;
import com.hivision.hivision.dto.MedicalServiceDTO;
import com.hivision.hivision.enums.ErrorCode;
import com.hivision.hivision.exception.AppException;
import com.hivision.hivision.mapper.IDoctorMapper;
import com.hivision.hivision.mapper.IMedicalServiceMapper;
import com.hivision.hivision.payload.response.FacilityResponse;
import com.hivision.hivision.pojo.Doctor;
import com.hivision.hivision.pojo.Facility;
import com.hivision.hivision.pojo.MedicalService;
import com.hivision.hivision.repository.IDoctorRepo;
import com.hivision.hivision.repository.IFacilityRepo;
import com.hivision.hivision.repository.IMedicalServiceRepo;
import com.hivision.hivision.service.iservice.IFacilityService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class FacilityService implements IFacilityService {

    IDoctorRepo doctorRepo;
    IMedicalServiceRepo medicalServiceRepo;
    IFacilityRepo medicalFacilityRepo;

    IDoctorMapper doctorMapper;
    IMedicalServiceMapper medicalServiceMapper;

    @Override
    public FacilityResponse getByFacility(String facilityID) {
        Facility facility = medicalFacilityRepo.findById(facilityID)
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_EMAIL));

        List<Doctor> doctors = doctorRepo.findByFacility(facility);
        List<DoctorDTO> doctorDTOS = doctorMapper.toDoctorDTO(doctors);
        List<MedicalService> medicalServices = medicalServiceRepo.findByFacility(facility);
        List<MedicalServiceDTO> medicalServiceDTOS = medicalServiceMapper.toMedicalServiceDTOs(medicalServices);

//        List<MedicalService>

        return FacilityResponse.builder()
                .name(facility.getName())
                .phone(facility.getPhone())
                .address(facility.getAddress())
                .img(facility.getImg())
                .des(facility.getDes())
                .time(facility.getTime())
                .doctors(doctorDTOS)
                .medicalServices(medicalServiceDTOS)
                .build();
    }

    @Override
    public List<Facility> getAll() {
        return medicalFacilityRepo.findAll();
    }


}
