package com.hivision.hivision.service;

import com.hivision.hivision.dto.LabResultDTO;
import com.hivision.hivision.dto.PatientDTO;
import com.hivision.hivision.payload.request.PatientRequest;
import com.hivision.hivision.pojo.MedicalRecord;
import com.hivision.hivision.pojo.Patient;

import java.util.List;

public interface IPatientService {
//    List<Patient> getAllPatients();
    List<PatientDTO> getAllPatients();
    Patient getPatientByAccountID(String accountId);
    PatientDTO updatePatient(String accountId, PatientRequest patientRequest);

    // chức năng tra cứu thông tin xét nghiệm (phác đồ ARV, CD4, tải lượng HIV),
    List<LabResultDTO> getLabResults(String patientId);
}
