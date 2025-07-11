package com.hivision.hivision.mapper;

import com.hivision.hivision.payload.request.ConsultationPayload;
import com.hivision.hivision.pojo.ChatBox;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface IMapperChatBox {

//    @Mapping(source = "patient.name", target = "name")
//    @Mapping(source = "patient.patientID", target = "patientID")
    @Mapping(source = "accPatient.id", target = "accountID")
    @Mapping(source = "accPatient.username", target = "name")
    ConsultationPayload toConsultationPayload(ChatBox chatBox);
    List<ConsultationPayload> toConsultationPayloads(List<ChatBox> chatBoxes);
}
