package com.hivision.hivision.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hivision.hivision.dto.MessageDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageResponse {
    int chatNo;
    String accountID;
    String sender;
    List<MessageDTO> messages;
}
