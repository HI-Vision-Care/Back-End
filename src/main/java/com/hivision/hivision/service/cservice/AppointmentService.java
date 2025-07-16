package com.hivision.hivision.service.cservice;

import com.hivision.hivision.dto.AppointmentDTO;
import com.hivision.hivision.dto.ConsultationNoteDTO;
import com.hivision.hivision.enums.AppointmentStatus;
import com.hivision.hivision.enums.ErrorCode;
import com.hivision.hivision.enums.PaymentStatus;
import com.hivision.hivision.exception.AppException;
import com.hivision.hivision.mapper.IAppointmentMapper;
import com.hivision.hivision.payload.request.AppointmentRequest;
import com.hivision.hivision.payload.request.ConsultationRequest;
import com.hivision.hivision.payload.request.UpdateAppointmentRequest;
import com.hivision.hivision.pojo.*;
import com.hivision.hivision.repository.*;
import com.hivision.hivision.service.iservice.IAppointmentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class AppointmentService implements IAppointmentService {
    IAccountRepo accountRepo;
    AccountService accountService;
    IAppointmentRepo appointmentRepo;
    IPatientRepo patientRepo;
    IDoctorRepo doctorRepo;
    IMedicalServiceRepo serviceRepo;
    IConsultationNoteRepo consultationNoteRepo;
    IWorkShiftRepo workShiftRepo;

    IAppointmentMapper mapper;


    @Override
    public AppointmentDTO bookAppointment(AppointmentRequest request, String patientId) {
        Patient patient = patientRepo.findById(patientId)
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
                .isRecordCreated(false)
                .isPrescriptionCreated(false)
                .note(request.getNote())
                .status(AppointmentStatus.SCHEDULED)
                .paymentStatus(PaymentStatus.UNPAID)
                .createAt(ZonedDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")).toInstant())
//                .createAt(Instant.now())
                .build();
        appointment = appointmentRepo.save(appointment);

        WorkShift workShift = workShiftRepo.findWorkShiftBySlotAndDoctorAndDate(request.getSlot(),doctor,request.getAppointmentDate());
        workShift.setStatus("SCHEDULED");
        workShiftRepo.save(workShift);

        return mapper.toAppointmentDTO(appointment);
    }

    @Override
    public List<AppointmentDTO> getAppointmentsByPatient(String patientID) {
        Patient patient = patientRepo.findById(patientID)
                .orElseThrow(() -> new AppException(ErrorCode.PATIENT_NOT_FOUND));
//        List<Appointment> appointments = appointmentRepo.findByPatient(patient);
//        if (appointments.isEmpty()) {
//            throw new AppException(ErrorCode.APPOINTMENT_NOT_FOUND);
//        }
//        return appointmentRepo.findByPatient(patient);
        return mapper.toAppointmentDTOs(appointmentRepo.findByPatient(patient));
    }

    @Override
    public List<AppointmentDTO> getAppointments() {
        List<Appointment> appointments = appointmentRepo.findAll();
        if (appointments.isEmpty()) {
            throw new AppException(ErrorCode.APPOINTMENT_NOT_FOUND);
        }
        return mapper.toAppointmentDTOs(appointments);
    }

    @Override
    public AppointmentDTO updateAppointment(String appointmentID, UpdateAppointmentRequest request) {
        Appointment appointment = appointmentRepo.findById(appointmentID)
                .orElseThrow(() -> new AppException(ErrorCode.APPOINTMENT_NOT_FOUND));

        appointment.setNote(request.getNote());
        appointment.setUrlLink(request.getUrlLink());
        return mapper.toAppointmentDTO(appointmentRepo.save(appointment));
    }

    @Override
    public AppointmentDTO updatePaymentStatus(String appointmentId, String staffID) {
        Appointment appointment = appointmentRepo.findById(appointmentId)
                .orElseThrow(() -> new AppException(ErrorCode.APPOINTMENT_NOT_FOUND));
//        if (appointment.getPaymentStatus() != PaymentStatus.UNPAID) {
//            throw new AppException(ErrorCode.APPOINTMENT_ALREADY_PAID);
//        }
        Account staff = accountRepo.findAccountById(staffID);
        if (staff == null) {
            throw new AppException(ErrorCode.STAFF_NOT_FOUND);
        }
        appointment.setPaymentStatus(PaymentStatus.PAID);

        return mapper.toAppointmentDTO(appointmentRepo.save(appointment));
    }

//    @Override
//    public AppointmentDTO createOnlineAppointment(String phone, ConsultationRequest request) {
//
//        if(phone == null || phone.isBlank()){
//            throw new AppException(ErrorCode.PHONE_REQUIRED);
//        }
//
//        Doctor doctor = doctorRepo.findById(request.getDoctorID())
//                .orElseThrow(() -> new AppException(ErrorCode.DOCTOR_NOT_FOUND));
//        MedicalService medicalService = serviceRepo.findById(request.getServiceID())
//                .orElseThrow(() -> new AppException(ErrorCode.SERVICE_NOT_FOUND));
//
//        Appointment appointment = Appointment.builder()
//                .doctor(doctor)
//                .medicalService(medicalService)
//                .appointmentDate(request.getAppointmentDate())
//                .isAnonymous(request.getIsAnonymous())
//                .note(request.getNote())
//                .status(AppointmentStatus.SCHEDULED)
//                .createAt(Instant.now())
//                .build();
//
//        if(Boolean.TRUE.equals(request.getIsAnonymous())){
//            // Nếu là lịch hẹn ẩn danh, không cần thiết lập bệnh nhân
//            appointment.setPatient(null);
//        }
//        else {
//            Account account = accountRepo.findByPhone(phone)
//                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
//            // Nếu ko tìm thấy tài khoản, tức là người dùng chưa đăng ký, ta cần lưu lại thông tin cơ bản của họ như tên, phone
////            if (account.getId() == null) {
////                account.setPhone(phone);
////                account.setRole(Role.GUEST);
////                account = accountRepo.save(account);
////            }
//            Patient patient = patientRepo.findPatientByAccount(account);
//            appointment.setPatient(patient);
//
////            Patient patient = patientRepo.findPatientByAccount(account);
////            appointment.setPatient(patient);
//        }
//        return mapper.toAppointmentDTO(appointmentRepo.save(appointment));
//    }

    @Override
    public void createOnlineAppointmentForLoggedInUser(ConsultationRequest request, String patientId) {

        ConsultationNote consultationNote = ConsultationNote.builder()
                .patient(patientRepo.findById(patientId)
                        .orElseThrow(() -> new AppException(ErrorCode.PATIENT_NOT_FOUND)))
                .name(request.getName())
                .phone(request.getPhone())
                .note(request.getNote())
                .createAt(Instant.now())
                .build();





        consultationNoteRepo.save(consultationNote);
    }

    @Override
    public void createOnlineAppointmentForGuest(ConsultationRequest request) {

        ConsultationNote consultationNote = ConsultationNote.builder()
                .name(request.getName())
                .phone(request.getPhone())
                .note(request.getNote())
                .createAt(Instant.now())
                .build();
        if (consultationNote.getPhone() == null || consultationNote.getPhone().isBlank()) {
            throw new AppException(ErrorCode.PHONE_REQUIRED);
        }
        consultationNoteRepo.save(consultationNote);

    }

    @Override
    public void cancelAppointment(String appointmentID,String patientID) {
        Appointment appointment = appointmentRepo.findById(appointmentID)
                .orElseThrow(() -> new AppException(ErrorCode.APPOINTMENT_NOT_FOUND));
        if (!appointment.getPatient().getPatientID().equals(patientID)) {
            throw new AppException(ErrorCode.APPOINTMENT_NOT_BELONG_TO_PATIENT);
        }
        appointment.setStatus(AppointmentStatus.CANCELLED);

        appointmentRepo.save(appointment);
    }

    @Override
    public List<ConsultationNote> getAllConsultationNote() {
        return consultationNoteRepo.findAll();
    }

    @Override
    public List<ConsultationNote> getConsultationNoteForPatient(String patientID) {
        return consultationNoteRepo.findByPatient(patientRepo.findById(patientID)
                .orElseThrow(() -> new AppException(ErrorCode.PATIENT_NOT_FOUND)));
    }


}
