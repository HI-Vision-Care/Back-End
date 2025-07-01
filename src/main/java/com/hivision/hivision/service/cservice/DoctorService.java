package com.hivision.hivision.service.cservice;

import com.hivision.hivision.dto.DoctorDTO;
import com.hivision.hivision.enums.AppointmentStatus;
import com.hivision.hivision.enums.ErrorCode;
import com.hivision.hivision.exception.AppException;
import com.hivision.hivision.mapper.IDoctorMapper;
import com.hivision.hivision.pojo.Appointment;
import com.hivision.hivision.pojo.Doctor;
import com.hivision.hivision.repository.IAppointmentRepo;
import com.hivision.hivision.repository.IDoctorRepo;
import com.hivision.hivision.service.iservice.IDoctorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class DoctorService implements IDoctorService {

    IDoctorRepo doctorRepo;
    IDoctorMapper doctorMapper;
    IAppointmentRepo appointmentRepo;

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

    @Override
    public void confirm(String appointmentId) {
        Appointment appointment = appointmentRepo.findById(appointmentId)
                .orElseThrow(() -> new AppException(ErrorCode.APPOINTMENT_NOT_FOUND));
        appointment.setStatus(AppointmentStatus.ONGOING);
    }

    @Override
    public void complete(String appointmentId) {
        Appointment appointment = appointmentRepo.findById(appointmentId)
                .orElseThrow(() -> new AppException(ErrorCode.APPOINTMENT_NOT_FOUND));
        appointment.setStatus(AppointmentStatus.COMPLETED);
    }


}
