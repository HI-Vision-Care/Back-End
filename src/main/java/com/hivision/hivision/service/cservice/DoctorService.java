package com.hivision.hivision.service.cservice;

import com.hivision.hivision.dto.DoctorDTO;

import com.hivision.hivision.enums.AppointmentStatus;
import com.hivision.hivision.enums.ErrorCode;
import com.hivision.hivision.exception.AppException;
import com.hivision.hivision.mapper.IDoctorMapper;
import com.hivision.hivision.pojo.Appointment;
import com.hivision.hivision.pojo.Doctor;

import com.hivision.hivision.enums.ErrorCode;
import com.hivision.hivision.exception.AppException;
import com.hivision.hivision.mapper.IAppointmentMapper;
import com.hivision.hivision.mapper.IDoctorMapper;
import com.hivision.hivision.payload.request.DoctorRequest;
import com.hivision.hivision.payload.response.AppointmentResponse;
import com.hivision.hivision.pojo.Account;
import com.hivision.hivision.pojo.Appointment;
import com.hivision.hivision.pojo.Doctor;
import com.hivision.hivision.pojo.MedicalService;
import com.hivision.hivision.repository.IAccountRepo;

import com.hivision.hivision.repository.IAppointmentRepo;
import com.hivision.hivision.repository.IDoctorRepo;
import com.hivision.hivision.repository.IMedicalServiceRepo;
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
    IAccountRepo accountRepo;

    IDoctorRepo doctorRepo;
    IDoctorMapper doctorMapper;
    IAppointmentRepo appointmentRepo;

    IAppointmentRepo appointmentRepo;
    IAppointmentMapper appointmentMapper;

    IMedicalServiceRepo medicalServiceRepo;

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
    public List<DoctorDTO> findDoctorsBySpecialty(Long serviceId) {
        MedicalService medicalService = medicalServiceRepo.findById(serviceId)
                .orElseThrow(() -> new AppException(ErrorCode.MEDICAL_SERVICE_NOT_FOUND));

        List<Doctor> doctors = doctorRepo.findBySpecialty(medicalService.getSpecialty());
        return doctorMapper.toDoctorDTO(doctors);
    }

    @Override
    public Doctor getDoctorByAccountID(String accountId) {
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return doctorRepo.findDoctorByAccount(account);
    }

    @Override
    public List<AppointmentResponse> getAppointmentsByDoctor(String doctorID) {
        Doctor doctor = doctorRepo.findById(doctorID)
                .orElseThrow(() -> new AppException(ErrorCode.DOCTOR_NOT_FOUND));
        List<Appointment> appointments = appointmentRepo.findByDoctor(doctor);
        if (appointments.isEmpty()) {
            throw new AppException(ErrorCode.APPOINTMENT_NOT_FOUND);
        }
        return appointmentMapper.toAppointmentResponses(appointments);
    }

    @Override
    public void updateDoctor(String accountId, DoctorRequest request) {
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        Doctor doctor = doctorRepo.findDoctorByAccount(account);

        if (doctor == null) {
            throw new AppException(ErrorCode.DOCTOR_NOT_FOUND);
        }
        doctor.setName(request.getFullName());
        doctor.setGender(request.getGender());
        doctor.setSpecialty(request.getSpecialty());
        doctor.setDegrees(request.getDegrees());

        doctorMapper.updateDoctor(doctor, request);
        doctorRepo.save(doctor);
        doctorMapper.toDoctorDTO(doctor);
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
