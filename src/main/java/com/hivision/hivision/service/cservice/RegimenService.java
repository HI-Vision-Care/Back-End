package com.hivision.hivision.service.cservice;

import com.hivision.hivision.pojo.Regimen;
import com.hivision.hivision.repository.IRegimenRepo;
import com.hivision.hivision.service.iservice.IRegimenService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class RegimenService implements IRegimenService {
    IRegimenRepo regimenRepo;

    @Override
    public List<Regimen> getAllRegimens() {
        return regimenRepo.findAll();

    }
}
