package com.hivision.hivision.service.cservice;

import com.hivision.hivision.dto.TreatmentDTO;
import com.hivision.hivision.mapper.ITreatmentMapper;
import com.hivision.hivision.payload.request.TreatmentRequest;
import com.hivision.hivision.pojo.Treatment;
import com.hivision.hivision.repository.IArvRepo;
import com.hivision.hivision.repository.IPatientRepo;
import com.hivision.hivision.repository.ITreatmentRepo;
import com.hivision.hivision.service.iservice.ITreatmentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TreatmentService implements ITreatmentService {
    ITreatmentMapper treatmentMapper;
    ITreatmentRepo treatmentRepo;
    IArvRepo arvRepo;
    IPatientRepo patientRepo;


    @Override
    public TreatmentDTO createTreatment(TreatmentRequest request,String patientId) {
        Treatment treatment = Treatment.builder()
                .patient(patientRepo.findPatientByPatientID(patientId))
                .arv(arvRepo.findByArvId(request.getArvID()))
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .prescribeBy(request.getPrescribeBy())
                .build();
        return treatmentMapper.toTreatmentDTO(treatmentRepo.save(treatment));
    }
}
