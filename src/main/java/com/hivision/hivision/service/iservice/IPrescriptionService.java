package com.hivision.hivision.service.iservice;

import com.hivision.hivision.payload.request.ArvRequest;
import com.hivision.hivision.payload.request.PrescriptionRequest;
import com.hivision.hivision.pojo.Prescription;
import com.hivision.hivision.pojo.PrescriptionARV;

import java.util.List;

public interface IPrescriptionService {
    Prescription createPrescription(PrescriptionRequest request);
    List<PrescriptionARV> addArvToPres(List<ArvRequest> request,String patientId);
    List<PrescriptionARV> getAllPresArvByPatientId(String patientId);
}
