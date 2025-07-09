package com.hivision.hivision.service.iservice;

import com.hivision.hivision.payload.request.ConsultationPayload;

import java.util.List;

public interface IChatBoxService {
    ConsultationPayload requireConsultation(String patientID, ConsultationPayload payload);
    List<ConsultationPayload> confirmConsultation(String staffID, ConsultationPayload payload);
    void completeConsultation(String staffID);
    ConsultationPayload requireAgainConsultation(String patientID, ConsultationPayload payload);
    List<ConsultationPayload> getRequireConsultation();
}
