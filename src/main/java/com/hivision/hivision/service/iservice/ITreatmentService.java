package com.hivision.hivision.service.iservice;

import com.hivision.hivision.dto.TreatmentDTO;
import com.hivision.hivision.payload.request.TreatmentRequest;

public interface ITreatmentService {
    TreatmentDTO createTreatment(TreatmentRequest request,String patientID);
}
