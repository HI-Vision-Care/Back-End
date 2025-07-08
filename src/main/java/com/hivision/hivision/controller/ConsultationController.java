package com.hivision.hivision.controller;

import com.hivision.hivision.dto.MessageDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@SecurityRequirement(name = "api")
@RequestMapping("/consultation")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConsultationController {
    SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/message/{roomId}") // Đường dẫn tương ứng /app/message/{roomId}
    public MessageDTO sendMessage(@DestinationVariable Long roomId, @Payload MessageDTO chatMessage) {
//        service.save(chatMessage,roomId);
        messagingTemplate.convertAndSend("/room/" + roomId, chatMessage);
        return chatMessage;
    }
}
