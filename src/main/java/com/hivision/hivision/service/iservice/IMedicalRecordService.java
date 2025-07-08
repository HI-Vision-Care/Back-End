package com.hivision.hivision.service.iservice;

import com.hivision.hivision.dto.MedicalRecordDTO;

import java.util.List;

public interface IMedicalRecordService {
    MedicalRecordDTO getMedicalRecordByAppointmentId(String appointmentId);
}
