package com.hivision.hivision.service.iservice;

import com.hivision.hivision.payload.request.ConsultationPayload;
import com.hivision.hivision.pojo.ChatBox;

import java.util.List;

public interface IChatBoxService {
    ConsultationPayload requireConsultation(String patientID, ConsultationPayload payload);
    ConsultationPayload confirmConsultation(String staffID, ConsultationPayload payload);
    void completeConsultation(String staffID,String patientID);
    ConsultationPayload requireAgainConsultation(String patientID, ConsultationPayload payload);
    List<ConsultationPayload> getRequireConsultation();
    ConsultationPayload getRequireConsultation(String patientID);
    List<ChatBox> getChatBox(String accStaffID);
}
