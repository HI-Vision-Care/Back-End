package com.hivision.hivision.controller;

import com.hivision.hivision.dto.AppointmentDTO;
import com.hivision.hivision.dto.DoctorDTO;
import com.hivision.hivision.dto.LabResultDTO;
import com.hivision.hivision.dto.MedicalRecordDTO;
import com.hivision.hivision.payload.request.CreateMedicalRecordRequest;
import com.hivision.hivision.payload.request.DoctorRequest;
import com.hivision.hivision.payload.request.MedicalRecordRequest;
import com.hivision.hivision.payload.response.AppointmentResponse;
import com.hivision.hivision.pojo.Appointment;
import com.hivision.hivision.pojo.Doctor;
import com.hivision.hivision.pojo.LabResult;
import com.hivision.hivision.pojo.MedicalRecord;
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

    @GetMapping("/specialty/{specialty}")
    public ResponseEntity<List<DoctorDTO>> findDoctorsBySpecialty(@PathVariable String specialty) {
        return ResponseEntity.ok(doctorService.findDoctorsBySpecialty(specialty));
    }

    @GetMapping("/view-appointment/{doctorID}")
    public ResponseEntity<List<AppointmentDTO>> getAppointmentsByDoctor(@PathVariable String doctorID) {
        return ResponseEntity.ok(doctorService.getAppointmentsByDoctor(doctorID));
    }

    @PutMapping("/update-profile/{doctorId}")
    public ResponseEntity<Void> updateDoctor(@PathVariable String doctorId, @RequestBody @Valid DoctorRequest request) {
        doctorService.updateDoctor(doctorId, request);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/create-medical-record/{appointmentId}")
    public ResponseEntity<MedicalRecordDTO> createMedicalRecord(@PathVariable String appointmentId, @RequestBody @Valid CreateMedicalRecordRequest request) {
        return ResponseEntity.ok(doctorService.createMedicalRecord(appointmentId, request));
    }

//    @PostMapping("/create-medical-record/{appointmentId}")
//    public ResponseEntity<MedicalRecordDTO> createMedicalRecord(@PathVariable String appointmentId, @RequestBody @Valid MedicalRecordRequest request) {
//        return ResponseEntity.ok(doctorService.createMedicalRecord(appointmentId, request));
//    }

    @GetMapping("/get-all-medical-records")
    public ResponseEntity<List<MedicalRecordDTO>> getAllMedicalRecords() {
        return ResponseEntity.ok(doctorService.getAllMedicalRecord());
    }


    @PostMapping("/create-lab-result")
    public ResponseEntity<LabResultDTO> createLabResult(@RequestBody @Valid LabResultDTO labResultDTO) {
        return ResponseEntity.ok(doctorService.createLabResult(labResultDTO));
    }

    @GetMapping("/get-all-lab-results")
    public ResponseEntity<List<LabResultDTO>> getAllLabResults() {
        return ResponseEntity.ok(doctorService.getAllLabResults());
    }

    @DeleteMapping("/delete/{doctorId}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable String doctorId) {
        doctorService.deleteDoctor(doctorId);
        return ResponseEntity.noContent().build();
    }
}
