package com.hivision.hivision.repository;

import com.hivision.hivision.enums.ConsultationStatus;
import com.hivision.hivision.pojo.ChatBox;
import com.hivision.hivision.pojo.Patient;
import com.hivision.hivision.pojo.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IChatBoxRepo extends JpaRepository<ChatBox, Integer> {
    ChatBox findByPatient(Patient patient);
    ChatBox findByStaffAndPatient(Staff staff,Patient patient);
    List<ChatBox> findChatBoxByStatus(ConsultationStatus status);
}