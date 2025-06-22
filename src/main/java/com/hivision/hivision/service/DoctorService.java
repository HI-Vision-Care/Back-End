package com.hivision.hivision.service;

import com.hivision.hivision.dto.DoctorDTO;
import com.hivision.hivision.mapper.IDoctorMapper;
import com.hivision.hivision.pojo.Doctor;
import com.hivision.hivision.repository.IDoctorRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class DoctorService implements IDoctorService{

    IDoctorRepo doctorRepo;
    IDoctorMapper doctorMapper;

//    @Override
//    public List<DoctorDTO> getAllDoctors() {
//        // trà về danh sách các bác sĩ theo DoctorDTO
//       return doctorRepo.findAll()
//                .stream()
//                .map(doctorMapper::toDoctorDTO)
//                .toList();
//    }

    @Override
    public List<DoctorDTO> getAllDoctors() {
        List<Doctor> doctors = doctorRepo.findAll();
        return doctorMapper.toDoctorDTO(doctors);
    }

    @Override
    public List<Doctor> findDoctorsBySpecialty(String specialty) {
        return doctorRepo.findBySpecialty(specialty);
    }
}
