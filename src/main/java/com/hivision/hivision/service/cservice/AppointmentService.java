package com.hivision.hivision.service.cservice;

import com.hivision.hivision.dto.AppointmentDTO;
import com.hivision.hivision.enums.AppointmentStatus;
import com.hivision.hivision.enums.ErrorCode;
import com.hivision.hivision.exception.AppException;
import com.hivision.hivision.mapper.IAppointmentMapper;
import com.hivision.hivision.payload.request.AppointmentRequest;
import com.hivision.hivision.payload.request.ConsultationRequest;
import com.hivision.hivision.pojo.*;
import com.hivision.hivision.repository.*;
import com.hivision.hivision.service.iservice.IAppointmentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class AppointmentService implements IAppointmentService {
    IAccountRepo accountRepo;
    IAppointmentRepo appointmentRepo;
    IPatientRepo patientRepo;
    IDoctorRepo doctorRepo;
    IMedicalServiceRepo serviceRepo;

    IAppointmentMapper mapper;


    @Override
    public AppointmentDTO bookAppointment(AppointmentRequest request) {
        Patient patient = patientRepo.findById(request.getPatientID())
                .orElseThrow(() -> new AppException(ErrorCode.PATIENT_NOT_FOUND));
        Doctor doctor = doctorRepo.findById(request.getDoctorID())
                .orElseThrow(() -> new AppException(ErrorCode.DOCTOR_NOT_FOUND));
        MedicalService medicalService = serviceRepo.findById(request.getServiceID())
                .orElseThrow(() -> new AppException(ErrorCode.SERVICE_NOT_FOUND));

        Appointment appointment = Appointment.builder()
                .patient(patient)
                .doctor(doctor)
                .medicalService(medicalService)
                .appointmentDate(request.getAppointmentDate())
                .isAnonymous(request.getIsAnonymous())
                .note(request.getNote())
                .status(String.valueOf(AppointmentStatus.PENDING))
                .createAt(Instant.now())
                .build();
        appointment = appointmentRepo.save(appointment);

        return mapper.toAppointmentDTO(appointment);
    }

    @Override
    public List<Appointment> getAppointmentsByPatient(String patientID) {
        Patient patient = patientRepo.findById(patientID)
                .orElseThrow(() -> new AppException(ErrorCode.PATIENT_NOT_FOUND));
//        List<Appointment> appointments = appointmentRepo.findByPatient(patient);
//        if (appointments.isEmpty()) {
//            throw new AppException(ErrorCode.APPOINTMENT_NOT_FOUND);
//        }
        return appointmentRepo.findByPatient(patient);
    }

    @Override
    public AppointmentDTO updateAppointment(String appointmentID, AppointmentDTO appointmentDTO) {
        Appointment appointment = appointmentRepo.findById(appointmentID)
                .orElseThrow(() -> new AppException(ErrorCode.APPOINTMENT_NOT_FOUND));

        appointment.setNote(appointmentDTO.getNote());
        return mapper.toAppointmentDTO(appointmentRepo.save(appointment));
    }

    @Override
    public AppointmentDTO createOnlineAppointment(String phone, ConsultationRequest request) {

        if(phone == null || phone.isBlank()){
            throw new AppException(ErrorCode.PHONE_REQUIRED);
        }

        Doctor doctor = doctorRepo.findById(request.getDoctorID())
                .orElseThrow(() -> new AppException(ErrorCode.DOCTOR_NOT_FOUND));
        MedicalService medicalService = serviceRepo.findById(request.getServiceID())
                .orElseThrow(() -> new AppException(ErrorCode.SERVICE_NOT_FOUND));

        Appointment appointment = Appointment.builder()
                .doctor(doctor)
                .medicalService(medicalService)
                .appointmentDate(request.getAppointmentDate())
                .isAnonymous(request.getIsAnonymous())
                .note(request.getNote())
                .status(String.valueOf(AppointmentStatus.PENDING))
                .createAt(Instant.now())
                .build();

        if(Boolean.TRUE.equals(request.getIsAnonymous())){
            // Nếu là lịch hẹn ẩn danh, không cần thiết lập bệnh nhân
            appointment.setPatient(null);
        }
        else {
            Account account = accountRepo.findByPhone(phone)
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
            // Nếu ko tìm thấy tài khoản, tức là người dùng chưa đăng ký, ta cần lưu lại thông tin cơ bản của họ như tên, phone
//            if (account.getId() == null) {
//                account.setPhone(phone);
//                account.setRole(Role.GUEST);
//                account = accountRepo.save(account);
//            }
            Patient patient = patientRepo.findPatientByAccount(account);
            appointment.setPatient(patient);

//            Patient patient = patientRepo.findPatientByAccount(account);
//            appointment.setPatient(patient);
        }
        return mapper.toAppointmentDTO(appointmentRepo.save(appointment));
    }
}
