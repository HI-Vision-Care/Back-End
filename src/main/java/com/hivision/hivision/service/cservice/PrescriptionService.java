package com.hivision.hivision.service.cservice;

import com.hivision.hivision.enums.ErrorCode;
import com.hivision.hivision.enums.PresStatus;
import com.hivision.hivision.exception.AppException;
import com.hivision.hivision.payload.request.ArvRequest;
import com.hivision.hivision.payload.request.PrescriptionRequest;
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


    @Override
    public Prescription createPrescription(PrescriptionRequest request) {
        Patient patient = patientRepo.findById(request.getPatientId())
                .orElseThrow(() -> new AppException(ErrorCode.PATIENT_NOT_FOUND));
        Prescription prescription = Prescription.builder()
                .patient(patient)
                .date(Instant.now())
                .prescribeBy(request.getPrescribeBy())
                .status(PresStatus.CREATED)
                .build();
        return prescriptionRepo.save(prescription);
    }

    @Override
    public List<PrescriptionARV> addArvToPres(List<ArvRequest> requests,String patientId) {
        List<PrescriptionARV> preARVsToSave = new ArrayList<>();
        for (ArvRequest request : requests) {
            Patient patient = patientRepo.findById(patientId)
                    .orElseThrow(() -> new AppException(ErrorCode.PATIENT_NOT_FOUND));

            Prescription prescription = prescriptionRepo.findByPatientAndStatus(patient, PresStatus.CREATED);


            PrescriptionARV preArv = PrescriptionARV.builder()
                    .arv(arvRepo.findByArvId(request.getArvID()))
                    .prescription(prescription)
                    .build();
            preARVsToSave.add(preArv);
        }



        return preARVRepo.saveAll(preARVsToSave);
    }

}
