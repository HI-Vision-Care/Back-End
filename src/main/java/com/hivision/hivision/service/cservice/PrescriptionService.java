package com.hivision.hivision.service.cservice;

import com.hivision.hivision.enums.ErrorCode;
import com.hivision.hivision.enums.PresStatus;
import com.hivision.hivision.exception.AppException;
import com.hivision.hivision.mapper.IPrescriptionMapper;
import com.hivision.hivision.payload.request.ArvRequest;
import com.hivision.hivision.payload.request.PrescriptionRequest;
import com.hivision.hivision.payload.response.PreArvResponse;
import com.hivision.hivision.payload.response.PrescriptionArvResponse;
import com.hivision.hivision.payload.response.PrescriptionResponse;
import com.hivision.hivision.pojo.*;
import com.hivision.hivision.repository.*;
import com.hivision.hivision.service.iservice.IPrescriptionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PrescriptionService implements IPrescriptionService {

    PrescriptionRepo prescriptionRepo;
    IPatientRepo patientRepo;
    IArvRepo arvRepo;
    PreARVRepo preARVRepo;

    IAppointmentRepo appointmentRepo;

    IPrescriptionMapper prescriptionMapper;




    @Override
    public PrescriptionResponse createPrescription(PrescriptionRequest request, List<ArvRequest> arvRequests, String patientId) {
        Patient patient = patientRepo.findById(patientId)
                .orElseThrow(() -> new AppException(ErrorCode.PATIENT_NOT_FOUND));
        if(prescriptionRepo.existsByPatientAndStatus(patient,PresStatus.CREATED)){
            throw new AppException(ErrorCode.PRES_ALREADY_EXISTS);
        }
        Prescription prescription = Prescription.builder()
                .patient(patient)
                .date(Instant.now())
                .prescribeBy(request.getPrescribeBy())
                .status(PresStatus.CREATED)
                .build();
        prescriptionRepo.save(prescription);
        List<PrescriptionARV> preARVsToSave = new ArrayList<>();
        List<PreArvResponse> preArvResponses = new ArrayList<>();
        for (ArvRequest arvRequest : arvRequests) {
            Patient patients = patientRepo.findById(patientId)
                    .orElseThrow(() -> new AppException(ErrorCode.PATIENT_NOT_FOUND));

            prescription = prescriptionRepo.findByPatientAndStatus(patient, PresStatus.CREATED);

            // kiểm tra xem ARV có tồn tại không
//            ARV arv = arvRepo.findByArvId(arvRequest.getArvID());
//            if (arv == null) {
//                throw new AppException(ErrorCode.ARV_NOT_FOUND);
//            }

            PrescriptionARV preArv = PrescriptionARV.builder()
                    .arv(arvRepo.findByArvId(arvRequest.getArvID()))
                    .dosage(arvRequest.getDosage())
                    .duration(arvRequest.getDuration())
                    .prescription(prescription)
                    .build();
            preARVsToSave.add(preArv);

            PreArvResponse preArvResponse = PreArvResponse.builder()
                    .arvID(arvRequest.getArvID())
                    .dosage(arvRequest.getDosage())
                    .duration(arvRequest.getDuration())
                    .build();
            preArvResponses.add(preArvResponse);
        }
        preARVRepo.saveAll(preARVsToSave);

        // Update lại isPrescriptionCreated trong Appointment
        appointmentRepo.findByPatientAndIsPrescriptionCreated(patient, false)
                .ifPresent(appointment -> {
                    appointment.setIsPrescriptionCreated(true);
                    appointmentRepo.save(appointment);
                });

        return PrescriptionResponse.builder()
                .patient(patient)
                .prescribeBy(request.getPrescribeBy())
                .preArvResponses(preArvResponses)
                .build();
    }

    @Override
    public PrescriptionArvResponse getAllPresArvByApppointment(String appointmentID) {
        Appointment appointment = appointmentRepo.findById(appointmentID)
                .orElseThrow(() -> new AppException(ErrorCode.APPOINTMENT_NOT_FOUND));
        Patient patient = appointment.getPatient();
        Prescription prescription = prescriptionRepo.findByPatientAndStatus(patient, PresStatus.CREATED);

        List<ARV> arvList = preARVRepo.findArvsByPrescription(prescription);


        return PrescriptionArvResponse.builder()
                .prescription(prescription)
                .arvList(arvList)
                .build(); // trả về danh sách PrescriptionARV liên kết với Prescription
    }

    @Override
    public List<PrescriptionArvResponse> getAllPresArvByPatient(String patientId) {
        Patient patient = patientRepo.findById(patientId)
                .orElseThrow(() -> new AppException(ErrorCode.PATIENT_NOT_FOUND));
        List<Prescription> prescriptions = prescriptionRepo.findByPatient(patient);
        List<PrescriptionArvResponse> preArvResponses = new ArrayList<>();
        for (Prescription pre: prescriptions) {
            List<ARV> arvList = preARVRepo.findArvsByPrescription(pre);
            PrescriptionArvResponse prescriptionArvResponse = PrescriptionArvResponse.builder()
                    .prescription(pre)
                    .arvList(arvList)
                    .build();
            preArvResponses.add(prescriptionArvResponse);
        }

        return preArvResponses;
    }

}
