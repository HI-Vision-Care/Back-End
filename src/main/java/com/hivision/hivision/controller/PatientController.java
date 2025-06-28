package com.hivision.hivision.controller;

import com.hivision.hivision.dto.LabResultDTO;
import com.hivision.hivision.dto.PatientDTO;
import com.hivision.hivision.payload.request.PatientRequest;
import com.hivision.hivision.pojo.Patient;
import com.hivision.hivision.service.IPatientService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
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
    public List<PatientDTO> getAllPatients() { return patientService.getAllPatients(); }

    @GetMapping("/profile/{accountId}")
    public ResponseEntity<Patient> getPatientByAccountID(@PathVariable String accountId) {
        return ResponseEntity.ok(patientService.getPatientByAccountID(accountId));
    }

    // chức năng tra cứu thông tin xét nghiệm (phác đồ ARV, CD4, tải lượng HIV)
    @GetMapping("/lab-results/{patientId}")
    public ResponseEntity<List<LabResultDTO>> getLabResults(@PathVariable String patientId) {
        return ResponseEntity.ok(patientService.getLabResults(patientId));
    }


//    @PutMapping("/update-profile/{accountId}")
//    public ResponseEntity<PatientDTO> updatePatient(@PathVariable String accountId, @RequestBody @Valid PatientRequest request) {
//        return ResponseEntity.ok(patientService.updatePatient(accountId, request));
//    }

    @PutMapping("/update-profile/{accountId}")
    public ResponseEntity<Void> updatePatient(@PathVariable String accountId, @RequestBody @Valid PatientRequest request) {
        patientService.updatePatient(accountId, request);
        return ResponseEntity.noContent().build();
    }
}
