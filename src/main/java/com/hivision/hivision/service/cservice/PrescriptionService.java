package com.hivision.hivision.service.cservice;

import com.hivision.hivision.enums.ErrorCode;
import com.hivision.hivision.enums.PresStatus;
import com.hivision.hivision.exception.AppException;
import com.hivision.hivision.payload.request.ArvRequest;
import com.hivision.hivision.payload.request.PrescriptionRequest;
import com.hivision.hivision.payload.response.PreArvResponse;
import com.hivision.hivision.payload.response.PrescriptionResponse;
import com.hivision.hivision.pojo.ARV;
import com.hivision.hivision.pojo.Patient;
import com.hivision.hivision.pojo.Prescription;
import com.hivision.hivision.pojo.PrescriptionARV;
import com.hivision.hivision.repository.IArvRepo;
import com.hivision.hivision.repository.IPatientRepo;
import com.hivision.hivision.repository.PreARVRepo;
import com.hivision.hivision.repository.PrescriptionRepo;
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


//    @Override
//    public Prescription createPrescription(PrescriptionRequest request) {
//        Patient patient = patientRepo.findById(request.getPatientId())
//                .orElseThrow(() -> new AppException(ErrorCode.PATIENT_NOT_FOUND));
//        Prescription prescription = Prescription.builder()
//                .patient(patient)
//                .date(Instant.now())
//                .prescribeBy(request.getPrescribeBy())
//                .status(PresStatus.CREATED)
//                .build();
//        return prescriptionRepo.save(prescription);
//    }

    @Override
    public PrescriptionResponse createPrescription(PrescriptionRequest request, List<ArvRequest> arvRequests, String patientId) {
        Patient patient = patientRepo.findById(patientId)
                .orElseThrow(() -> new AppException(ErrorCode.PATIENT_NOT_FOUND));
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

        return PrescriptionResponse.builder()
                .patient(patient)
                .prescribeBy(request.getPrescribeBy())
                .preArvResponses(preArvResponses)
                .build();
    }

    @Override
    public List<PrescriptionARV> getAllPresArvByPatientId(String patientId) {
        Patient patient = patientRepo.findById(patientId)
                .orElseThrow(() -> new AppException(ErrorCode.PATIENT_NOT_FOUND));

        Prescription prescription = prescriptionRepo.findByPatientAndStatus(patient, PresStatus.CREATED);

        return preARVRepo.findByPrescription(prescription); // trả về danh sách PrescriptionARV liên kết với Prescription
    }

}
