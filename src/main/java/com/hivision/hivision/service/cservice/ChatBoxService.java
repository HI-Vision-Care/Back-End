package com.hivision.hivision.service.cservice;

import com.hivision.hivision.enums.ConsultationStatus;
import com.hivision.hivision.enums.ErrorCode;
import com.hivision.hivision.exception.AppException;
import com.hivision.hivision.mapper.IMapperChatBox;
import com.hivision.hivision.payload.request.ConsultationPayload;
import com.hivision.hivision.pojo.ChatBox;
import com.hivision.hivision.pojo.Patient;
import com.hivision.hivision.pojo.Staff;
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

    @Override
    public ConsultationPayload requireConsultation(String patientID, ConsultationPayload payload) {
        Patient patient = patientRepo.findById(patientID)
                .orElseThrow(() -> new AppException(ErrorCode.PATIENT_NOT_FOUND));

        ChatBox chatBox = ChatBox.builder()
                        .patient(patient)
                        .status(ConsultationStatus.REQUIRE)
                        .note(payload.getNote())
                        .createdAt(payload.getCreatedAt())
                        .build();
        chatBoxRepo.save(chatBox);

//        return ConsultationPayload.builder()
//                .patientID(patientID)
//                .name(patient.getName())
//                .note(payload.getNote())
//                .build();
        return mapper.toConsultationPayload(chatBox);
    }

    @Override
    public List<ConsultationPayload> confirmConsultation(String staffID, ConsultationPayload payload) {
        Staff staff = staffRepo.findById(staffID)
                .orElseThrow(() -> new AppException(ErrorCode.STAFF_NOT_FOUND));
        ChatBox chatBox = chatBoxRepo.findByPatient(patientRepo.findPatientByName(payload.getName()));
        chatBox.setStaff(staff);
        chatBox.setStatus(ConsultationStatus.ONGOING);
        chatBoxRepo.save(chatBox);


//        List<ConsultationPayload> consultations = new ArrayList<>();
        List<ChatBox> chatBoxes = chatBoxRepo.findChatBoxByStatus(ConsultationStatus.REQUIRE);

        return mapper.toConsultationPayloads(chatBoxes);
    }

    @Override
    public void completeConsultation(String staffID) {
        ChatBox chatBox = chatBoxRepo.findByStaff(staffRepo.findStaffByStaffId(staffID));
        chatBox.setStaff(null);
        chatBox.setStatus(ConsultationStatus.COMPLETE);
        chatBoxRepo.save(chatBox);
    }

    @Override
    public ConsultationPayload requireAgainConsultation(String patientID, ConsultationPayload payload) {
        Patient patient = patientRepo.findById(patientID)
                .orElseThrow(() -> new AppException(ErrorCode.PATIENT_NOT_FOUND));
        ChatBox chatBox = chatBoxRepo.findByPatient(patient);
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
        ChatBox chatBox = chatBoxRepo.findByPatient(patientRepo.findPatientByName(patientID));
        return mapper.toConsultationPayload(chatBox);
    }


}
