package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.Chat;
import com.hivision.hivision.pojo.ChatBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IChatRepo extends JpaRepository<Chat, Integer> {
    Chat findByChatBox(ChatBox chatBox);
    List<Chat> findChatsByChatBox(ChatBox chatBox);
}