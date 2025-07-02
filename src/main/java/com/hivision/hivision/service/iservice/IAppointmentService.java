package com.hivision.hivision.service.iservice;

import com.hivision.hivision.dto.AppointmentDTO;
import com.hivision.hivision.dto.ConsultationNoteDTO;
import com.hivision.hivision.payload.request.AppointmentRequest;
import com.hivision.hivision.payload.request.ConsultationRequest;
import com.hivision.hivision.pojo.Account;
import com.hivision.hivision.pojo.Appointment;
import com.hivision.hivision.pojo.ConsultationNote;

import java.util.List;

public interface IAppointmentService {
    // Người dùng đăng ký lịch khám & điều trị HIV
     AppointmentDTO bookAppointment(AppointmentRequest request, String patientId);

    // Lấy danh sách lịch hẹn của người dùng
     List<Appointment> getAppointmentsByPatient(String patientID);
     List<AppointmentDTO> getAppointments();

    // Lấy danh sách lịch hẹn của bác sĩ
    // List<AppointmentDTO> getAppointmentsByDoctor(String doctorID);

    // Cập nhật thông tin lịch hẹn
     AppointmentDTO updateAppointment(String appointmentID, AppointmentDTO appointmentDTO);

     // đặt lịch hẹn trực tuyến với bác sĩ
//    AppointmentDTO createOnlineAppointment(String phone, ConsultationRequest request);

    void createOnlineAppointmentForLoggedInUser(ConsultationRequest request, String patientId);

    // đặt tư vấo online với bác sĩ cho người dùng chưa đăng nhập
    void createOnlineAppointmentForGuest(ConsultationRequest consultationNote);
    void cancelAppointment(String appointmentID,String patientID);

    List<ConsultationNote> getAllConsultationNote();
    List<ConsultationNote> getConsultationNoteForPatient(String patientID);
}
