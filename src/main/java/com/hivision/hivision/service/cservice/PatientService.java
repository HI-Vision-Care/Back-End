package com.hivision.hivision.service.cservice;

import com.hivision.hivision.dto.PatientDTO;
import com.hivision.hivision.enums.ErrorCode;
import com.hivision.hivision.exception.AppException;
import com.hivision.hivision.mapper.IPatientMapper;
import com.hivision.hivision.pojo.Account;
import com.hivision.hivision.pojo.Patient;
import com.hivision.hivision.repository.IAccountRepo;
import com.hivision.hivision.repository.IPatientRepo;
import com.hivision.hivision.service.iservice.IPatientService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PatientService implements IPatientService {

    IAccountRepo accountRepo;
    IPatientRepo patientRepo;
    IPatientMapper patientMapper;

//    @Override
//    public List<Patient> getAllPatients() { return patientRepo.findAll(); }

    @Override
    public List<PatientDTO> getAllPatients() {
        List<Patient> patients = patientRepo.findAll();
        return patientMapper.toPatientDTO(patients);
    }

    @Override
    public Patient getPatientByAccountID(String accountId) {
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return patientRepo.findPatientByAccount(account);
    }

}
