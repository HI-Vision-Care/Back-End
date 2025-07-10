package com.hivision.hivision.controller;

import com.hivision.hivision.dto.MessageDTO;
import com.hivision.hivision.payload.request.ConsultationPayload;
import com.hivision.hivision.service.cservice.MessageService;
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
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@SecurityRequirement(name = "api")
@RequestMapping("/consultation")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConsultationController {
    SimpMessagingTemplate messagingTemplate;
    IChatBoxService chatBoxService;
    MessageService messageService;

    @MessageMapping("/message/{patientID}") // Đường dẫn tương ứng /app/message/{roomId}
    public MessageDTO sendMessage(@DestinationVariable String patientID, @Payload MessageDTO chatMessage) {
        messageService.save(chatMessage,patientID);
        messagingTemplate.convertAndSend("/box/" + patientID, chatMessage);
        return chatMessage;
    }

    @MessageMapping("/requirement/{patientID}")
    @SendTo("/consultation/require")
    public ConsultationPayload requireConsultation(@DestinationVariable String patientID, @Payload ConsultationPayload consultationPayload ) {
        chatBoxService.requireConsultation(patientID,consultationPayload);
//        messagingTemplate.convertAndSend("/consultation/require", consultationPayload);
//        return chatBoxService.requireConsultation(patientID,consultationPayload);
        return consultationPayload;
    }

    @MessageMapping("/require-again/{patientID}")
    @SendTo("/consultation/require")
    public ConsultationPayload requireAgainConsultation(@DestinationVariable String patientID, @Payload ConsultationPayload consultationPayload ) {
        chatBoxService.requireAgainConsultation(patientID,consultationPayload);
//        messagingTemplate.convertAndSend("/consultation", consultationPayload);
        return consultationPayload;
    }

    @MessageMapping("/confirmation/{staffID}")
    @SendTo("/consultation/on-going")
    public List<ConsultationPayload> confirmConsultation(@DestinationVariable String staffID, @Payload ConsultationPayload consultationPayload) {

        return chatBoxService.confirmConsultation(staffID,consultationPayload);
    }

    @PatchMapping("/complete/{staffID}")
    public ResponseEntity<Void> confirmConsultation(@PathVariable String staffID, @RequestParam String patientID) {
        chatBoxService.completeConsultation(staffID,patientID);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/require")
    public ResponseEntity<List<ConsultationPayload>> getRequireConsultation() {
        return ResponseEntity.ok(chatBoxService.getRequireConsultation());
    }

    @GetMapping("/require/{patientID}")
    public ResponseEntity<ConsultationPayload> getRequireConsultation(@PathVariable String patientID) {
        ConsultationPayload consultationPayload = chatBoxService.getRequireConsultation(patientID);
        return ResponseEntity.ok(consultationPayload);
    }


}
