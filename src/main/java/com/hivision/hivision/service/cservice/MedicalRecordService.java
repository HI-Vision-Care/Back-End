package com.hivision.hivision.service.cservice;

import com.hivision.hivision.dto.LabResultDTO;
import com.hivision.hivision.dto.MedicalRecordDTO;
import com.hivision.hivision.enums.ErrorCode;
import com.hivision.hivision.exception.AppException;
import com.hivision.hivision.mapper.ILabResultMapper;
import com.hivision.hivision.mapper.IMedicalRecordMapper;
import com.hivision.hivision.pojo.Appointment;
import com.hivision.hivision.pojo.LabResult;
import com.hivision.hivision.pojo.MedicalRecord;
import com.hivision.hivision.repository.IAppointmentRepo;
import com.hivision.hivision.repository.ILabResultRepo;
import com.hivision.hivision.repository.IMedicalRecordRepo;
import com.hivision.hivision.service.iservice.IMedicalRecordService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class MedicalRecordService implements IMedicalRecordService {
    IAppointmentRepo appointmentRepo;
    IMedicalRecordRepo medicalRecordRepo;
    IMedicalRecordMapper medicalRecordMapper;
    ILabResultRepo labResultRepo;
    ILabResultMapper labResultMapper;


    @Override
    public MedicalRecordDTO getMedicalRecordByAppointmentId(String appointmentId) {
        Appointment appointment = appointmentRepo.findById(appointmentId)
                .orElseThrow(() -> new AppException(ErrorCode.APPOINTMENT_NOT_FOUND));

        MedicalRecord medicalRecord = medicalRecordRepo.findByAppointment(appointment);

        List<LabResult> labResults = labResultRepo.findByMedicalRecord(medicalRecord);
        List<LabResultDTO> labResultDTOs = labResultMapper.toLabResultDTO(labResults);

        MedicalRecordDTO medicalRecordDTO = medicalRecordMapper.toMedicalRecordDTO(medicalRecord);
        medicalRecordDTO.setLabResults(labResultDTOs);
        return medicalRecordDTO;

//        if (medicalRecord == null) {
//            throw new AppException(ErrorCode.MEDICAL_RECORD_NOT_FOUND);
//        }
//        return medicalRecordMapper.toMedicalRecordDTO(medicalRecord);

    }
}
