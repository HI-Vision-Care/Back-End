package com.hivision.hivision.service.iservice;

import com.hivision.hivision.payload.request.ConsultationPayload;

public interface IChatBoxService {
    ConsultationPayload requireConsultation(String patientID, ConsultationPayload payload);
    void confirmConsultation(String staffID,String patientID);
    void completeConsultation(String staffID);
    ConsultationPayload requireAgainConsultation(String patientID, ConsultationPayload payload);
}
