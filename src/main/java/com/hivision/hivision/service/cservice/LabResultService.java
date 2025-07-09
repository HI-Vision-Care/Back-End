package com.hivision.hivision.service.cservice;

import com.hivision.hivision.dto.LabResultDTO;
import com.hivision.hivision.enums.ErrorCode;
import com.hivision.hivision.exception.AppException;
import com.hivision.hivision.mapper.ILabResultMapper;
import com.hivision.hivision.pojo.Appointment;
import com.hivision.hivision.pojo.LabResult;
import com.hivision.hivision.pojo.MedicalRecord;
import com.hivision.hivision.repository.IAppointmentRepo;
import com.hivision.hivision.repository.ILabResultRepo;
import com.hivision.hivision.repository.IMedicalRecordRepo;
import com.hivision.hivision.service.iservice.ILabResultService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class LabResultService implements ILabResultService {
    IAppointmentRepo appointmentRepo;
    IMedicalRecordRepo medicalRecordRepo;
    ILabResultRepo labResultRepo;
    ILabResultMapper labResultMapper;



    @Override
    public List<LabResultDTO> getLabResultsByAppointmentId(String appointmentId) {
        Appointment appointment = appointmentRepo.findById(appointmentId)
                .orElseThrow(() -> new AppException(ErrorCode.APPOINTMENT_NOT_FOUND));

        MedicalRecord medicalRecord = medicalRecordRepo.findByAppointment(appointment);
//        if (medicalRecord == null) {
//            throw new AppException(ErrorCode.MEDICAL_RECORD_NOT_FOUND);
//        }
        List<LabResult> labResults = labResultRepo.findByMedicalRecord(medicalRecord);

        return labResultMapper.toLabResultDTO(labResults);
    }
}
