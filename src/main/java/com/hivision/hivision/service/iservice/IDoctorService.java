package com.hivision.hivision.service.iservice;

import com.hivision.hivision.dto.AppointmentDTO;
import com.hivision.hivision.dto.DoctorDTO;
import com.hivision.hivision.dto.LabResultDTO;
import com.hivision.hivision.dto.MedicalRecordDTO;
import com.hivision.hivision.payload.request.DoctorRequest;
import com.hivision.hivision.payload.request.MedicalRecordRequest;
import com.hivision.hivision.payload.response.AppointmentResponse;
import com.hivision.hivision.pojo.Doctor;
import com.hivision.hivision.pojo.LabResult;
import com.hivision.hivision.pojo.MedicalRecord;

import java.util.List;

public interface IDoctorService {
    List<DoctorDTO> getAllDoctors();

    void confirm(String appointmentId);
    void complete(String appointmentId);

    List<DoctorDTO> findDoctorsBySpecialty(String specialty);
    Doctor getDoctorByAccountID(String accountId);
    List<AppointmentResponse> getAppointmentsByDoctor(String doctorID);

    void updateDoctor(String accountId, DoctorRequest request);

    //tạo medical record
    MedicalRecordDTO createMedicalRecord(String appointmentId, MedicalRecordRequest request);

    LabResultDTO createLabResult(LabResultDTO labResultDTO);
}
