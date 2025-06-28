package com.hivision.hivision.controller;

import com.hivision.hivision.dto.AppointmentDTO;
import com.hivision.hivision.payload.request.AppointmentRequest;
import com.hivision.hivision.payload.request.ConsultationRequest;
import com.hivision.hivision.pojo.Account;
import com.hivision.hivision.pojo.Appointment;
import com.hivision.hivision.pojo.ConsultationNote;
import com.hivision.hivision.service.IAccountService;
import com.hivision.hivision.service.IAppointmentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
@CrossOrigin("*")
@SecurityRequirement(name = "api")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppointmentController {

    IAppointmentService appointmentService;

    @PostMapping("/book-appointment")
    public ResponseEntity<AppointmentDTO> bookAppointment(@RequestBody AppointmentRequest request) {
        return ResponseEntity.ok(appointmentService.bookAppointment(request));
    }

    @PostMapping("/book-consultation")
    public ResponseEntity<AppointmentDTO> bookConsultation(@RequestParam String phone, @RequestBody ConsultationRequest request) {
        return ResponseEntity.ok(appointmentService.createOnlineAppointment(phone, request));
    }

    // đặt lịch hẹn với tài khoản đã đăng nhập
    @PostMapping("/book-consultation-with-account")
    public ResponseEntity<AppointmentDTO> bookConsultationWithAccount(@RequestBody ConsultationRequest request) {
        return ResponseEntity.ok(appointmentService.createOnlineAppointmentForLoggedInUser(request));
    }

    // đặt tư vấn online với người dùng chưa đăng nhập
    @PostMapping("/book-consultation-guest")
    public ResponseEntity<String> bookConsultationForGuest(@RequestBody ConsultationNote request) {
        appointmentService.createOnlineAppointmentForGuest(request);
        return ResponseEntity.ok("Gửi yêu cầu thành công. Chúng tôi sẽ liên hệ lại.");
    }


    @GetMapping("/get-appointment/{patientId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByPatient(@PathVariable String patientId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByPatient(patientId));
    }

    @PutMapping("/update-appointment/{appointmentId}")
    public ResponseEntity<AppointmentDTO> updateAppointment(@PathVariable String appointmentId, @RequestBody AppointmentDTO appointmentDTO) {
        return ResponseEntity.ok(appointmentService.updateAppointment(appointmentId, appointmentDTO));
    }

}
