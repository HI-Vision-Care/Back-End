package com.hivision.hivision.repository;

import com.hivision.hivision.enums.ConsultationStatus;
import com.hivision.hivision.pojo.Account;
import com.hivision.hivision.pojo.ChatBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IChatBoxRepo extends JpaRepository<ChatBox, Integer> {
//    ChatBox findByPatient(Patient patient);
//    ChatBox findByStaffAndPatient(Staff staff,Patient patient);
    ChatBox findByAccPatient(Account accPatient);
    List<ChatBox> findByAccStaff(Account accStaff);
    ChatBox findByAccStaffAndAccPatient(Account staffID,Account patientID);
    List<ChatBox> findChatBoxByStatus(ConsultationStatus status);
    List<ChatBox> findChatBoxByStatusAndAccStaff(ConsultationStatus status,Account staff);
}