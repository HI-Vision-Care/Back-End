package com.hivision.hivision.service.cservice;

import com.hivision.hivision.dto.ArvDTO;
import com.hivision.hivision.mapper.IArvMapper;
import com.hivision.hivision.payload.response.ArvResponse;
import com.hivision.hivision.pojo.ARV;
import com.hivision.hivision.repository.*;
import com.hivision.hivision.service.iservice.IArvService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class ArvService implements IArvService {
    IArvRepo arvRepo;
    IArvMapper arvMapper;
    ArvContraindicationRepo arvContraindicationRepo;
    ArvIndicationRepo arvIndicationRepo;
    ArvSideEffectRepo arvSideeffectRepo;
    ArvDrugInteractionRepo arvDruginteractionRepo;
    ArvTargetPopulationRepo arvTargetpopulationRepo;


    @Override
    public List<ArvResponse> getARVs() {
        List<ARV> arvs = arvRepo.findAll();
        List<ArvResponse> arvResponses =  new ArrayList<>();

        for (ARV arv : arvs) {
            ArvResponse response = ArvResponse.builder()
                .arvId(arv.getArvId())
                .genericName(arv.getGenericName())
                .drugClass(arv.getDrugClass())
                .dosageStrength(arv.getDosageStrength())
                .admRoute(arv.getAdmRoute())
                .rcmDosage(arv.getRcmDosage())
                .shelfLife(arv.getShelfLife())
                .fundingSource(arv.getFundingSource())
                .regimenLevel(arv.getRegimenLevel())
                .lastUpdated(arv.getLastUpdated())
                .contraindication(arvContraindicationRepo.findContraindicationsByArv(arv))
                .indication(arvIndicationRepo.findIndicationsByArv(arv))
                .sideEffect(arvSideeffectRepo.findSideEffectsByArv(arv))
                .drugInteraction(arvDruginteractionRepo.findDrugInteractionsByArv(arv))
                .targetPopulation(arvTargetpopulationRepo.findTargetPopulationsByArv(arv))
                .build();
            arvResponses.add(response);
        }

//
        return arvResponses;
    }
}
