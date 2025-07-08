package com.hivision.hivision.service.iservice;

import com.hivision.hivision.dto.MessageDTO;

import java.util.List;

public interface IMessageService {
    MessageDTO save(MessageDTO message, int chatID);
    //List<MessageDTO> getMessage(Long roomId);
}
