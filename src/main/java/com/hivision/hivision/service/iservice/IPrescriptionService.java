package com.hivision.hivision.service.iservice;

import com.hivision.hivision.payload.request.ArvRequest;
import com.hivision.hivision.payload.request.PrescriptionRequest;
import com.hivision.hivision.payload.response.PrescriptionArvResponse;
import com.hivision.hivision.payload.response.PrescriptionResponse;
import com.hivision.hivision.pojo.Prescription;
import com.hivision.hivision.pojo.PrescriptionARV;

import java.util.List;

public interface IPrescriptionService {

    PrescriptionResponse createPrescription(PrescriptionRequest request, List<ArvRequest> ArvRequest, String patientId);
    PrescriptionArvResponse getAllPresArvByApppointment(String appointmentID);
    List<PrescriptionArvResponse> getAllPresArvByPatient(String patientId);
}
