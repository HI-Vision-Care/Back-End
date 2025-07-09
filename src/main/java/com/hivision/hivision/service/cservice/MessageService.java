package com.hivision.hivision.service.cservice;


import com.hivision.hivision.dto.MessageDTO;
import com.hivision.hivision.enums.ErrorCode;
import com.hivision.hivision.exception.AppException;
import com.hivision.hivision.pojo.Chat;
import com.hivision.hivision.pojo.ChatBox;
import com.hivision.hivision.pojo.Patient;
import com.hivision.hivision.repository.IChatBoxRepo;
import com.hivision.hivision.repository.IChatRepo;
import com.hivision.hivision.repository.IPatientRepo;
import com.hivision.hivision.service.iservice.IMessageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageService implements IMessageService {

    IChatBoxRepo chatBoxRepo;
    IChatRepo chatRepo;
    IPatientRepo patientRepo;
//    IMessageMapper mapper;

    @Override
    public MessageDTO save(MessageDTO request, String patientID) {
        Patient patient = patientRepo.findById(patientID)
                .orElseThrow(() -> new AppException(ErrorCode.PATIENT_NOT_FOUND));




        chatRepo.save(Chat.builder()
                .account(patient.getAccount())
                .message(request.getMessage())
                .date(Instant.now())
                .build());

        return MessageDTO.builder()
                .senderName(request.getSenderName())
                .message(request.getMessage())
                .date(LocalDateTime.now())
                .build();
    }

//    @Override
//    public List<MessageDTO> getMessage(Long roomId) {
//        List<Chat> chats = chatRepo.findByRoom_RoomId(roomId);
//        List<MessageDTO> dtos = mapper.toMessagesDTO(chats);
//        return dtos;
//    }
}
