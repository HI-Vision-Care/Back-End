package com.hivision.hivision.controller;

import com.hivision.hivision.dto.MessageDTO;
import com.hivision.hivision.service.iservice.IChatBoxService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@SecurityRequirement(name = "api")
@RequestMapping("/consultation")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConsultationController {
    SimpMessagingTemplate messagingTemplate;
    IChatBoxService chatBoxService;

    @MessageMapping("/message/{chatID}") // Đường dẫn tương ứng /app/message/{roomId}
    public MessageDTO sendMessage(@DestinationVariable Long chatID, @Payload MessageDTO chatMessage) {
//        service.save(chatMessage,roomId);
        messagingTemplate.convertAndSend("/box/" + chatID, chatMessage);
        return chatMessage;
    }

    @PostMapping("/require/{patientID}")
    public ResponseEntity<Void> requireConsultation(@PathVariable String patientID) {
        chatBoxService.requireConsultation(patientID);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/confirm/{staffID}")
    public ResponseEntity<Void> confirmConsultation(@PathVariable String staffID, @RequestParam(name = "patientID") String patientID) {
        chatBoxService.confirmConsultation(staffID,patientID);
        return ResponseEntity.ok().build();
    }
}
