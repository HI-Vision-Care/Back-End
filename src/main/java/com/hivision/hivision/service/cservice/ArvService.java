package com.hivision.hivision.service.cservice;

import com.hivision.hivision.dto.ArvDTO;
import com.hivision.hivision.mapper.IArvMapper;
import com.hivision.hivision.pojo.ARV;
import com.hivision.hivision.repository.*;
import com.hivision.hivision.service.iservice.IArvService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

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
    public List<ArvDTO> getARVs() {
        List<ARV> arvs = arvRepo.findAll();
        List<ArvDTO> arvDTOList =  arvMapper.toArvDTOs(arvs);
//        for (ARV arv : arvs) {
//            ArvDTO arvdto = ArvDTO.builder()
//                    .contraindication(arvContraindicationRepo.findArvContraindicationByArv(arv))
//                    .indication(arvIndicationRepo.findArvContraindicationByArv(arv))
//                    .sideEffect(arvSideeffectRepo.findArvContraindicationByArv(arv))
//                    .drugInteraction(arvDruginteractionRepo.findArvContraindicationByArv(arv))
//                    .targetPopulation(arvTargetpopulationRepo.findArvContraindicationByArv(arv))
//                    .build();
//            arvDTOList.add(arvdto);
//        }
        return arvDTOList;
    }
}
