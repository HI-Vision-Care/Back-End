package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.ChatBox;
import com.hivision.hivision.pojo.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IChatBoxRepo extends JpaRepository<ChatBox, Integer> {
    ChatBox findByPatient(Patient patient);
}