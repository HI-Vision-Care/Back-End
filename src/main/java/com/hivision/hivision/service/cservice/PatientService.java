package com.hivision.hivision.service.cservice;

import com.hivision.hivision.dto.LabResultDTO;
import com.hivision.hivision.dto.PatientDTO;
import com.hivision.hivision.enums.ErrorCode;
import com.hivision.hivision.exception.AppException;
import com.hivision.hivision.mapper.ILabResultMapper;
import com.hivision.hivision.mapper.IPatientMapper;
import com.hivision.hivision.payload.request.PatientRequest;
import com.hivision.hivision.pojo.*;
import com.hivision.hivision.repository.*;
import com.hivision.hivision.service.iservice.IPatientService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PatientService implements IPatientService{

    IAccountRepo accountRepo;
    IPatientRepo patientRepo;
    IPatientMapper patientMapper;

    IAppointmentRepo appointmentRepo;
    IMedicalRecordRepo medicalRecordRepo;

    ILabResultRepo labResultRepo;
    ILabResultMapper labResultMapper;
//    @Override
//    public List<Patient> getAllPatients() { return patientRepo.findAll(); }

    @Override
    public List<PatientDTO> getAllPatients() {
        List<Patient> patients = patientRepo.findAll();
        return patientMapper.toPatientDTO(patients);
    }

    @Override
    public Patient getPatientByAccountID(String accountId) {
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return patientRepo.findPatientByAccount(account);
    }

    @Override
    public PatientDTO updatePatient(String accountId, PatientRequest request) {
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        Patient patient = patientRepo.findPatientByAccount(account);
        if (patient == null) {
            throw new AppException(ErrorCode.PATIENT_NOT_FOUND);
        }
        patient.setName(request.getName());
        patient.setDob(request.getDob());
        patient.setGender(request.getGender());
        patient.setMedNo(request.getMedNo());
        patient.setMedDate(request.getMedDate());
        patient.setMedFac(request.getMedFac());

        patientMapper.updatePatient(patient, request);

        patientRepo.save(patient);
        return patientMapper.toPatientDTO(patient);
    }


    @Override
    @Transactional(readOnly = true) // Ensure this method does not modify the database
    public List<LabResultDTO> getLabResults(String patientId) {
        Patient patient = patientRepo.findById(patientId)
                .orElseThrow(() -> new AppException(ErrorCode.PATIENT_NOT_FOUND));

        List<Appointment> appointments = appointmentRepo.findByPatient(patient);
        if( appointments.isEmpty()) {
            return List.of(); // Return an empty list if no appointments found
        }

        List<MedicalRecord> records = medicalRecordRepo.findByAppointment_AppointmentIDIn(
                appointments.stream()
                        .map(Appointment::getAppointmentID)
                        .toList()
        );

        List<LabResult> results = labResultRepo.findByMedicalRecord_RecordIdIn(
                records.stream()
                        .map(MedicalRecord::getRecordId)
                        .toList()
        );

        return labResultMapper.toLabResultDTO(results);
    }


}
