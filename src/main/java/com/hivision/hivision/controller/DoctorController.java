package com.hivision.hivision.controller;

import com.hivision.hivision.dto.DoctorDTO;
import com.hivision.hivision.pojo.Doctor;
import com.hivision.hivision.service.iservice.IDoctorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @GetMapping("/specialty/{specialty}")
    public ResponseEntity<List<Doctor>> findDoctorsBySpecialty(String specialty) {
        return ResponseEntity.ok(doctorService.findDoctorsBySpecialty(specialty));
    }

    @PatchMapping("/confirm/{appointmentID}")
    public ResponseEntity<Void> confirm(@PathVariable String appointmentID) {
        doctorService.confirm(appointmentID);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/complete/{appointmentID}")
    public ResponseEntity<Void> complete(@PathVariable String appointmentID) {
        doctorService.complete(appointmentID);
        return ResponseEntity.noContent().build();
    }
}
