package com.hivision.hivision.service.iservice;

import com.hivision.hivision.dto.MessageDTO;
import com.hivision.hivision.payload.response.MessageResponse;

import java.util.List;

public interface IMessageService {
    MessageDTO save(MessageDTO message, String patientID);
    List<MessageResponse> getMessageByStaff(String staffID);
    List<MessageDTO> getMessageByPatient(String patientID);

}
