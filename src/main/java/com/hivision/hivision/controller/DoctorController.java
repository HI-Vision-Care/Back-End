package com.hivision.hivision.controller;

import com.hivision.hivision.dto.DoctorDTO;
import com.hivision.hivision.payload.request.DoctorRequest;
import com.hivision.hivision.payload.response.AppointmentResponse;
import com.hivision.hivision.pojo.Doctor;
import com.hivision.hivision.service.iservice.IDoctorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
@CrossOrigin("*")
@SecurityRequirement(name = "api")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DoctorController {

    IDoctorService doctorService;

    @GetMapping
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() { return ResponseEntity.ok(doctorService.getAllDoctors()); }

    @GetMapping("/profile/{accountId}")
    public ResponseEntity<Doctor> getDoctorByAccountID(@PathVariable  String accountId) {
        return ResponseEntity.ok(doctorService.getDoctorByAccountID(accountId));
    }

    @GetMapping("/specialty/{serviceId}")
    public ResponseEntity<List<DoctorDTO>> findDoctorsBySpecialty(@PathVariable Long serviceId) {
        return ResponseEntity.ok(doctorService.findDoctorsBySpecialty(serviceId));
    }
    @GetMapping("/view-appointment/{doctorID}")
    public ResponseEntity<List<AppointmentResponse>> getAppointmentsByDoctor(@PathVariable String doctorID) {
        return ResponseEntity.ok(doctorService.getAppointmentsByDoctor(doctorID));
    }

    @PutMapping("/update-profile/{accountId}")
    public ResponseEntity<Void> updateDoctor(@PathVariable String accountId, @RequestBody @Valid DoctorRequest request) {
        doctorService.updateDoctor(accountId, request);
        return ResponseEntity.noContent().build();
    }

}
