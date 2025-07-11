package com.hivision.hivision.mapper;

import com.hivision.hivision.dto.MessageDTO;
import com.hivision.hivision.pojo.Chat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface IMessageMapper {
    @Mapping(source = "chat.sender" , target = "senderName")
    MessageDTO toDTO(Chat chat);
    List<MessageDTO> toDTO(List<Chat> chats);
}
