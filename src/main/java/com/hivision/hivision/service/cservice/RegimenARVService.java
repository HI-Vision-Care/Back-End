package com.hivision.hivision.service.cservice;

import com.hivision.hivision.pojo.RegimenARV;
import com.hivision.hivision.repository.IRegimenARVRepo;
import com.hivision.hivision.service.iservice.IRegimenARVService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class RegimenARVService implements IRegimenARVService {
    IRegimenARVRepo regimenARVRepo;

    @Override
    public List<RegimenARV> getAllRegimenARVs() {
        return regimenARVRepo.findAll();
    }
}
