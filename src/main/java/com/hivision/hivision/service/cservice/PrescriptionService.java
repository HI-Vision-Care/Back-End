package com.hivision.hivision.service.cservice;

import com.hivision.hivision.enums.ErrorCode;
import com.hivision.hivision.exception.AppException;
import com.hivision.hivision.payload.request.PreArvRequest;
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
                .build();

//        List<String> arvIds = Arrays.asList(request.getRegimenString().split("\\s*\\+\\s*"));
//
//        List<ARV> arvs = arvRepo.findAllById(arvIds);
//
//        if (arvs.size() != arvIds.size()) {
//            List<String> foundIds = arvs.stream().map(ARV::getArvId).toList();
//            arvIds.removeAll(foundIds);
//            throw new AppException(ErrorCode.ARV_NOT_FOUND);
//        }
//        List<Prescription> prescriptionsToSave = new ArrayList<>();
//        for(ARV arv : arvs) {
//            Prescription prescription = Prescription.builder()
//                    .patient(patient)
//                    .dosage(request.getDosage())
//                    .duration(request.getDuration())
//                    .prescribeBy(request.getPrescribeBy())
//                    .date(Instant.now())
//                    .build();
//            prescriptionsToSave.add(prescription);
//        }
        return prescriptionRepo.save(prescription);
    }

    @Override
    public List<PrescriptionARV> addPreArv(PreArvRequest request) {
        Prescription prescription = prescriptionRepo.findById(request.getPrescriptionId())
                .orElseThrow(() -> new AppException(ErrorCode.PRE_NOT_FOUND));
        List<String> arvIds = Arrays.asList(request.getRegimenString().split("\\s*\\+\\s*"));

        List<ARV> arvs = arvRepo.findAllById(arvIds);

        if (arvs.size() != arvIds.size()) {
            List<String> foundIds = arvs.stream().map(ARV::getArvId).toList();
            arvIds.removeAll(foundIds);
            throw new AppException(ErrorCode.ARV_NOT_FOUND);
        }
        List<PrescriptionARV> preARVsToSave = new ArrayList<>();
        for(ARV arv : arvs) {
            PrescriptionARV preArv = PrescriptionARV.builder()
                    .arv(arv)
                    .prescription(prescription)
                    .build();
            preARVsToSave.add(preArv);
        }
        return preARVRepo.saveAll(preARVsToSave);
    }

}
