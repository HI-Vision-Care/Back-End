package com.hivision.hivision.service.iservice;

import com.hivision.hivision.dto.PatientDTO;
import com.hivision.hivision.pojo.Patient;

import java.util.List;

public interface IPatientService {
//    List<Patient> getAllPatients();
    List<PatientDTO> getAllPatients();
    Patient getPatientByAccountID(String accountId);
}
