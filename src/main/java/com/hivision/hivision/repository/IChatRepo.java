package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IChatRepo extends JpaRepository<Chat, Integer> {
}