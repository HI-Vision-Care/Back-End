package com.hivision.hivision.service.cservice;

import com.hivision.hivision.enums.ConsultationStatus;
import com.hivision.hivision.enums.ErrorCode;
import com.hivision.hivision.exception.AppException;
import com.hivision.hivision.mapper.IMapperChatBox;
import com.hivision.hivision.payload.request.ConsultationPayload;
import com.hivision.hivision.pojo.Account;
import com.hivision.hivision.pojo.ChatBox;
import com.hivision.hivision.pojo.Patient;
import com.hivision.hivision.pojo.Staff;
import com.hivision.hivision.repository.IAccountRepo;
import com.hivision.hivision.repository.IChatBoxRepo;
import com.hivision.hivision.repository.IPatientRepo;
import com.hivision.hivision.repository.IStaffRepo;
import com.hivision.hivision.service.iservice.IChatBoxService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class ChatBoxService implements IChatBoxService {
    IChatBoxRepo chatBoxRepo;
    IPatientRepo patientRepo;
    IStaffRepo staffRepo;
    IMapperChatBox mapper;
    IAccountRepo accountRepo;

    @Override
    public ConsultationPayload requireConsultation(String patientID, ConsultationPayload payload) {
//        Patient patient = patientRepo.findById(patientID)
//                .orElseThrow(() -> new AppException(ErrorCode.PATIENT_NOT_FOUND));

        Account account = accountRepo.findAccountById(patientID);
        ChatBox chatBox = chatBoxRepo.findByAccPatient(account);
        chatBox.setStatus(ConsultationStatus.REQUIRE);
        chatBox.setNote(payload.getNote());
        chatBox.setCreatedAt(payload.getCreatedAt());
        chatBoxRepo.save(chatBox);


//        return ConsultationPayload.builder()
//                .patientID(patientID)
//                .name(patient.getName())
//                .note(payload.getNote())
//                .build();
        return mapper.toConsultationPayload(chatBox);
    }

    @Override
    public ConsultationPayload confirmConsultation(String staffID, ConsultationPayload payload) {

        Account accountStaff = accountRepo.findAccountById(staffID);
        Account accountPatient = accountRepo.findAccountById(payload.getAccountID());
//        ChatBox chatBox = chatBoxRepo.findByPatient(patientRepo.findPatientByName(payload.getName()));
        ChatBox chatBox = chatBoxRepo.findByAccPatient(accountPatient);
//        chatBox.setStaff(staff);
        chatBox.setAccStaff(accountStaff);
        chatBox.setStatus(ConsultationStatus.ONGOING);
        chatBoxRepo.save(chatBox);


//        List<ConsultationPayload> consultations = new ArrayList<>();

        return mapper.toConsultationPayload(chatBox);
    }

    @Override
    public void completeConsultation(String staffID,String patientID) {
//        Staff staff = staffRepo.findStaffByStaffId(staffID);
//        Patient patient = patientRepo.findPatientByPatientID(patientID);
        Account accountStaff = accountRepo.findAccountById(staffID);
        Account accountPatient = accountRepo.findAccountById(patientID);
        ChatBox chatBox = chatBoxRepo.findByAccStaffAndAccPatient(accountStaff, accountPatient  );
        if(chatBox == null){
            throw new AppException(ErrorCode.CHATBOX_NOT_FOUND);
        }
        chatBox.setAccStaff(null);
        chatBox.setStatus(ConsultationStatus.COMPLETE);
        chatBoxRepo.save(chatBox);
    }

    @Override
    public ConsultationPayload requireAgainConsultation(String patientID, ConsultationPayload payload) {
//        Patient patient = patientRepo.findById(patientID)
//                .orElseThrow(() -> new AppException(ErrorCode.PATIENT_NOT_FOUND));
        Account accountPatient = accountRepo.findAccountById(patientID);
        ChatBox chatBox = chatBoxRepo.findByAccPatient(accountPatient);
        chatBox.setStatus(ConsultationStatus.REQUIRE);
        chatBoxRepo.save(chatBox);

//        return ConsultationPayload.builder()
//                .patientID(patientID)
//                .name(patient.getName())
//                .note(payload.getNote())
//                .build();
        return mapper.toConsultationPayload(chatBox);

    }

    @Override
    public List<ConsultationPayload> getRequireConsultation() {
        List<ChatBox> chatBoxes = chatBoxRepo.findChatBoxByStatus(ConsultationStatus.REQUIRE);
        return mapper.toConsultationPayloads(chatBoxes);
    }

    @Override
    public ConsultationPayload getRequireConsultation(String patientID) {
        Account account = accountRepo.findById(patientID)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        ChatBox chatBox = chatBoxRepo.findByAccPatient(account);
        return mapper.toConsultationPayload(chatBox);
    }

    @Override
    public List<ChatBox> getChatBox(String accStaffID) {
        Account account = accountRepo.findById(accStaffID)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return chatBoxRepo.findChatBoxByStatusAndAccStaff(ConsultationStatus.ONGOING,account);
    }


}
