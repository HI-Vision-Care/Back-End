package com.hivision.hivision.controller;

import com.hivision.hivision.dto.MedicalRecordDTO;
import com.hivision.hivision.service.iservice.IMedicalRecordService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/medical-record")
@RequiredArgsConstructor
@CrossOrigin("*")
@SecurityRequirement(name = "api")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MedicalRecordController {
    IMedicalRecordService medicalRecordService;

    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity<MedicalRecordDTO> getMedicalRecordByAppointmentId(@PathVariable String appointmentId) {
        MedicalRecordDTO medicalRecordDTO = medicalRecordService.getMedicalRecordByAppointmentId(appointmentId);
        return ResponseEntity.ok(medicalRecordDTO);
    }

}
