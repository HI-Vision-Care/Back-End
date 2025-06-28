package com.hivision.hivision.controller;

import com.hivision.hivision.dto.PatientDTO;
import com.hivision.hivision.pojo.Patient;
import com.hivision.hivision.service.iservice.IPatientService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
@CrossOrigin("*")
@SecurityRequirement(name = "api")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PatientController {
    IPatientService patientService;

    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAllPatients() { return ResponseEntity.ok(patientService.getAllPatients()); }

    @GetMapping("/profile/{accountId}")
    public ResponseEntity<Patient> getPatientByAccountID(@PathVariable String accountId) {
        return ResponseEntity.ok(patientService.getPatientByAccountID(accountId));
    }
}
