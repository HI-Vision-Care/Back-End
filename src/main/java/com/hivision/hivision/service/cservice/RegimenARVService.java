package com.hivision.hivision.service.cservice;

import com.hivision.hivision.payload.response.RegimenArvResponse;
import com.hivision.hivision.pojo.ARV;
import com.hivision.hivision.pojo.Regimen;
import com.hivision.hivision.repository.IRegimenARVRepo;
import com.hivision.hivision.repository.IRegimenRepo;
import com.hivision.hivision.service.iservice.IRegimenARVService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class RegimenARVService implements IRegimenARVService {
    IRegimenARVRepo regimenARVRepo;
    IRegimenRepo regimenRepo;

    @Override
    public List<RegimenArvResponse> getAllRegimenARVs() {
        List<RegimenArvResponse> regimenARVResponses = new ArrayList<>();
        List<Regimen> regimens = regimenRepo.findAll();

        for (Regimen regimen : regimens) {
            List<ARV> arvs = regimenARVRepo.findArvByRegimen(regimen);
            RegimenArvResponse response = RegimenArvResponse.builder()
                    .regiment(regimen)
                    .arvs(arvs)
                    .build();
            regimenARVResponses.add(response);
        }

        return regimenARVResponses;
    }
}
