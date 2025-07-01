package com.hivision.hivision.service.iservice;

import com.hivision.hivision.payload.request.PrescriptionRequest;
import com.hivision.hivision.pojo.Prescription;

public interface IPrescriptionService {
    Prescription createPrescription(PrescriptionRequest request);
}
