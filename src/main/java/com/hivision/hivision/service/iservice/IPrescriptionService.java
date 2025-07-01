package com.hivision.hivision.service.iservice;

import com.hivision.hivision.payload.request.PreArvRequest;
import com.hivision.hivision.payload.request.PrescriptionRequest;
import com.hivision.hivision.pojo.Prescription;
import com.hivision.hivision.pojo.PrescriptionARV;

import java.util.List;

public interface IPrescriptionService {
    Prescription createPrescription(PrescriptionRequest request);
    List<PrescriptionARV> addPreArv(PreArvRequest request);
}
