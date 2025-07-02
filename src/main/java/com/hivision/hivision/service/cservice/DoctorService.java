package com.hivision.hivision.service.cservice;

import com.hivision.hivision.dto.DoctorDTO;

import com.hivision.hivision.dto.LabResultDTO;
import com.hivision.hivision.dto.MedicalRecordDTO;
import com.hivision.hivision.enums.AppointmentStatus;
import com.hivision.hivision.enums.ErrorCode;
import com.hivision.hivision.enums.Role;
import com.hivision.hivision.exception.AppException;
import com.hivision.hivision.mapper.*;
import com.hivision.hivision.payload.request.MedicalRecordRequest;
import com.hivision.hivision.pojo.*;

import com.hivision.hivision.enums.ErrorCode;
import com.hivision.hivision.exception.AppException;
import com.hivision.hivision.mapper.IDoctorMapper;
import com.hivision.hivision.payload.request.DoctorRequest;
import com.hivision.hivision.payload.response.AppointmentResponse;
import com.hivision.hivision.pojo.Appointment;
import com.hivision.hivision.pojo.Doctor;
import com.hivision.hivision.repository.*;

import com.hivision.hivision.service.iservice.IDoctorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class DoctorService implements IDoctorService {
    IAccountRepo accountRepo;

    IDoctorRepo doctorRepo;
    IDoctorMapper doctorMapper;
    IAppointmentRepo appointmentRepo;

    IAppointmentMapper appointmentMapper;

    IMedicalServiceRepo medicalServiceRepo;
    IMedicalRecordRepo medicalRecordRepo;
    IMedicalRecordMapper medicalRecordMapper;

    ILabResultRepo labResultRepo;
    ILabResultMapper labResultMapper;

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
//        List<Doctor> doctors = doctorRepo.findAll();
//        return doctorMapper.toDoctorDTO(doctors);
        List<Doctor> doctors = doctorRepo.findAll()
                .stream()
                .filter(d -> d.getAccount() != null && !Boolean.TRUE.equals(d.getAccount().getIsDeleted()))
                .toList();

        return doctorMapper.toDoctorDTO(doctors);
    }



    @Override
    public List<DoctorDTO> findDoctorsBySpecialty(String specialty) {
        List<Doctor> doctors = doctorRepo.findBySpecialty(specialty);
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
    public void updateDoctor(String doctorId, DoctorRequest request) {
//        Account account = accountRepo.findById(accountId)
//                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        Doctor doctor = doctorRepo.findById(doctorId)
                .orElseThrow(() -> new AppException(ErrorCode.DOCTOR_NOT_FOUND));

        Account account = doctor.getAccount();
        if (account == null || account.getIsDeleted() != null && account.getIsDeleted()) {
            throw new AppException(ErrorCode.ACCOUNT_DELETED);
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
    public void deleteDoctor(String doctorId) {
        Doctor doctor = doctorRepo.findById(doctorId)
                .orElseThrow(() -> new AppException(ErrorCode.DOCTOR_NOT_FOUND));
        Account account = doctor.getAccount();
        if (account == null) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        account.setRole(Role.BANNED);
        account.setIsDeleted(true);
        accountRepo.save(account);
    }

    @Override
    public MedicalRecordDTO createMedicalRecord(String appointmentId, MedicalRecordRequest request) {
        Appointment appointment = appointmentRepo.findById(appointmentId)
                .orElseThrow(() -> new AppException(ErrorCode.APPOINTMENT_NOT_FOUND));
        if( appointment.getStatus() != AppointmentStatus.ONGOING) {
            throw new AppException(ErrorCode.APPOINTMENT_NOT_ONGOING);
        }
        MedicalRecord medicalRecord = MedicalRecord.builder()
                .appointment(appointment)
                .diagnosis(request.getDiagnosis())
                .createDate(Instant.now())
                .note(request.getNote())
                .build();
        medicalRecordRepo.save(medicalRecord);

    return medicalRecordMapper.toMedicalRecordDTO(medicalRecord);

    }

    @Override
    public LabResultDTO createLabResult(LabResultDTO labResultDTO) {
        MedicalRecord medicalRecord = medicalRecordRepo.findById(labResultDTO.getRecordId())
                .orElseThrow(() -> new AppException(ErrorCode.MEDICAL_RECORD_NOT_FOUND));

        LabResult labResult = LabResult.builder()
                .medicalRecord(medicalRecord)
                .testType(labResultDTO.getTestType())
                .resultText(labResultDTO.getResultText())
                .resultValue(labResultDTO.getResultValue())
                .unit(labResultDTO.getUnit())
                .referenceRange(labResultDTO.getReferenceRange())
                .testDate(Instant.now())
                .performedBy(labResultDTO.getPerformedBy())
                .build();

        labResultRepo.save(labResult);
        return labResultMapper.toLabResultDTO(labResult);
    }

    @Override
    public void confirm(String appointmentId) {
        Appointment appointment = appointmentRepo.findById(appointmentId)
                .orElseThrow(() -> new AppException(ErrorCode.APPOINTMENT_NOT_FOUND));
        appointment.setStatus(AppointmentStatus.ONGOING);
        appointmentRepo.save(appointment);
    }

    @Override
    public void complete(String appointmentId) {
        Appointment appointment = appointmentRepo.findById(appointmentId)
                .orElseThrow(() -> new AppException(ErrorCode.APPOINTMENT_NOT_FOUND));
        appointment.setStatus(AppointmentStatus.COMPLETED);
        appointmentRepo.save(appointment);
    }


}
