package com.hivision.hivision.service.cservice;

import com.hivision.hivision.dto.AppointmentDTO;
import com.hivision.hivision.dto.LabResultDTO;
import com.hivision.hivision.dto.PatientDTO;
import com.hivision.hivision.dto.TestItemDTO;
import com.hivision.hivision.enums.ErrorCode;
import com.hivision.hivision.enums.Role;
import com.hivision.hivision.exception.AppException;
import com.hivision.hivision.mapper.IAppointmentMapper;
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
    IServiceTestItemRepo serviceTestItemRepo;

    IAppointmentRepo appointmentRepo;
    IMedicalRecordRepo medicalRecordRepo;

    ILabResultRepo labResultRepo;
    ILabResultMapper labResultMapper;
    IAppointmentMapper appointmentMapper;

    IDiseaseRepo diseaseRepo;
    IPatientDiseaseRepo patientDiseaseRepo;
//    @Override
//    public List<Patient> getAllPatients() { return patientRepo.findAll(); }

    @Override
    public List<PatientDTO> getAllPatients() {
//        List<Patient> patients = patientRepo.findAll();
//        return patientMapper.toPatientDTO(patients);

//        List<Patient> patients = patientRepo.findAll()
//                .stream()
//                .filter(d -> d.getAccount() != null && !Boolean.TRUE.equals(d.getAccount().getIsDeleted()))
//                .toList();
//
//        return patientMapper.toPatientDTO(patients);

        return patientRepo.findAll().stream()
                .filter(p -> p.getAccount() != null && !Boolean.TRUE.equals(p.getAccount().getIsDeleted()))
                .map(patient -> {
                    PatientDTO dto = patientMapper.toPatientDTO(patient);
                    List<String> diseases = patientDiseaseRepo.findByPatient(patient).stream()
                            .map(pd -> pd.getDisease().getName())
                            .toList();
                    dto.setUnderlyingDiseases(diseases);
                    return dto;
                })
                .toList();
    }

    @Override
    public PatientDTO getPatientByAccountID(String accountId) {
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        Patient patient = patientRepo.findPatientByAccount(account);
        if (patient == null) {
            throw new AppException(ErrorCode.PATIENT_NOT_FOUND);
        }
        PatientDTO dto = patientMapper.toPatientDTO(patient);

        List<String> diseases = patientDiseaseRepo.findByPatient(patient).stream()
                .map(pd -> pd.getDisease().getName())
                .toList();

        dto.setUnderlyingDiseases(diseases);

        return dto;
//        return patientRepo.findPatientByAccount(account);
    }

    @Override
    @Transactional
    public PatientDTO updatePatient(String patientId, PatientRequest request) {

        Patient patient = patientRepo.findById(patientId)
                .orElseThrow(() -> new AppException(ErrorCode.PATIENT_NOT_FOUND));
        Account account = patient.getAccount();
        if(account.getIsDeleted() != null && account.getIsDeleted()) {
            throw new AppException(ErrorCode.ACCOUNT_DELETED);
        }

        patient.setName(request.getName());
        patient.setDob(request.getDob());
        patient.setGender(request.getGender());
        patient.setMedNo(request.getMedNo());
        patient.setMedDate(request.getMedDate());
        patient.setMedFac(request.getMedFac());

        patientDiseaseRepo.deleteByPatient(patient); // Xóa các bản ghi cũ

        if (request.getUnderlyingDiseases() != null) {
            for (String diseaseName : request.getUnderlyingDiseases()) {
                Disease disease = diseaseRepo.findByName(diseaseName)
                        .orElseGet(() -> diseaseRepo.save(Disease.builder().name(diseaseName).build()));

                patientDiseaseRepo.save(PatientDisease.builder()
                        .patient(patient)
                        .disease(disease)
                        .build());
            }
        }

        patientRepo.save(patient);
        return patientMapper.toPatientDTO(patient);

//        patientMapper.updatePatient(patient, request);
//
//        patientRepo.save(patient);
//        return patientMapper.toPatientDTO(patient);
    }

    @Override
    public void deletePatient(String patientId) {
        Patient patient = patientRepo.findById(patientId)
                .orElseThrow(() -> new AppException(ErrorCode.PATIENT_NOT_FOUND));

        Account account = patient.getAccount();
        if (account == null) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }

        account.setRole(Role.BANNED);
        account.setIsDeleted(true);
        accountRepo.save(account);
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

    @Override
    public List<AppointmentDTO> getAppointmentsByPatient(String patientId) {
        Patient patient = patientRepo.findById(patientId)
                .orElseThrow(() -> new AppException(ErrorCode.PATIENT_NOT_FOUND));
        List<Appointment> appointments = appointmentRepo.findByPatient(patient);

        return appointments.stream().map(appt -> {
            AppointmentDTO dto = appointmentMapper.toAppointmentDTO(appt);

            // Gắn testItems cho MedicalService trong AppointmentDTO
            if (dto.getMedicalService() != null) {
                List<TestItemDTO> testItems = serviceTestItemRepo.findByMedicalService_ServiceID(
                                dto.getMedicalService().getServiceID()
                        ).stream()
                        .map(sti -> TestItemDTO.builder()
//                                .testID(sti.getTestItem().getTestID())
                                        .testName(sti.getTestItem().getTestName())
                                        .testDescription(sti.getTestItem().getTestDescription())
                                        .unit(sti.getTestItem().getUnit())
                                        .referenceRange(sti.getTestItem().getReferenceRange())
                                        .build()
                        ).toList();

                dto.getMedicalService().setTestItems(testItems);
            }
            return dto;
        }).toList();

//        if (appointments.isEmpty()) {
//            throw new AppException(ErrorCode.APPOINTMENT_NOT_FOUND);
//        }
//        return appointments;
//        return appointmentMapper.toAppointmentDTOs(appointments);

    }


}
