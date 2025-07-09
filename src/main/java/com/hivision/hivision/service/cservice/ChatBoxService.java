package com.hivision.hivision.service.cservice;

import com.hivision.hivision.enums.ConsultationStatus;
import com.hivision.hivision.enums.ErrorCode;
import com.hivision.hivision.exception.AppException;
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

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class ChatBoxService implements IChatBoxService {
    IChatBoxRepo chatBoxRepo;
    IPatientRepo patientRepo;
    IStaffRepo staffRepo;

    @Override
    public ConsultationPayload requireConsultation(String patientID, ConsultationPayload payload) {
        Patient patient = patientRepo.findById(patientID)
                .orElseThrow(() -> new AppException(ErrorCode.PATIENT_NOT_FOUND));

        chatBoxRepo.save(ChatBox.builder()
                        .patient(patient)
                        .status(ConsultationStatus.REQUIRE)
                        .note(payload.getNote())
                        .createdAt(payload.getCreatedAt())
                        .build());

        return ConsultationPayload.builder()
                .name(patient.getName())
                .note(payload.getNote())
                .build();
    }

    @Override
    public void confirmConsultation(String staffID,String patientID) {
        Staff staff = staffRepo.findById(staffID)
                .orElseThrow(() -> new AppException(ErrorCode.STAFF_NOT_FOUND));
        ChatBox chatBox = chatBoxRepo.findByPatient(patientRepo.findPatientByPatientID(patientID));
        chatBox.setStaff(staff);
        chatBox.setStatus(ConsultationStatus.ONGOING);
        chatBoxRepo.save(chatBox);
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

        return ConsultationPayload.builder()
                .name(patient.getName())
                .note(payload.getNote())
                .build();
    }


}
