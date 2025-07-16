package com.hivision.hivision.controller;

import com.hivision.hivision.dto.AppointmentDTO;
import com.hivision.hivision.dto.LabResultDTO;
import com.hivision.hivision.dto.PatientDTO;
import com.hivision.hivision.payload.request.PatientRequest;
import com.hivision.hivision.pojo.Patient;
import com.hivision.hivision.service.iservice.IAppointmentService;
import com.hivision.hivision.service.iservice.IPatientService;
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
    IAppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<List<PatientDTO>> getAllPatients() { return ResponseEntity.ok(patientService.getAllPatients()); }

    @GetMapping("/profile/{accountId}")
    public ResponseEntity<PatientDTO> getPatientByAccountID(@PathVariable String accountId) {
        return ResponseEntity.ok(patientService.getPatientByAccountID(accountId));
    }

    // chức năng tra cứu thông tin xét nghiệm (phác đồ ARV, CD4, tải lượng HIV)
    @GetMapping("/lab-results/{patientId}")
    public ResponseEntity<List<LabResultDTO>> getLabResults(@PathVariable String patientId) {
        return ResponseEntity.ok(patientService.getLabResults(patientId));
    }

    @GetMapping("/view-appointment/{patientID}")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentsByPatient(@PathVariable String patientID) {
        return ResponseEntity.ok(patientService.getAppointmentsByPatient(patientID));
    }


//    @PutMapping("/update-profile/{accountId}")
//    public ResponseEntity<PatientDTO> updatePatient(@PathVariable String accountId, @RequestBody @Valid PatientRequest request) {
//        return ResponseEntity.ok(patientService.updatePatient(accountId, request));
//    }

    @PutMapping("/update-profile/{patientId}")
    public ResponseEntity<Void> updatePatient(@PathVariable String patientId, @RequestBody @Valid PatientRequest request) {
        patientService.updatePatient(patientId, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{patientId}")
    public ResponseEntity<Void> deletePatient(@PathVariable String patientId) {
        patientService.deletePatient(patientId);
        return ResponseEntity.noContent().build();
    }

    // Cancel appointment by patient
    @PutMapping("/cancel-appointment/{appointmentId}")
    public ResponseEntity<Void> cancelAppointment(@PathVariable String appointmentId, @RequestParam String patientId) {
        appointmentService.cancelAppointment(appointmentId, patientId);
        return ResponseEntity.noContent().build();
    }
}
