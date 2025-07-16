package com.hivision.hivision.controller;

import com.hivision.hivision.pojo.ConsultationNote;
import com.hivision.hivision.service.iservice.IAppointmentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultation-note")
@RequiredArgsConstructor
@CrossOrigin("*")
@SecurityRequirement(name = "api")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConsultationNoteController {
    IAppointmentService appointmentService;

    @GetMapping()
    public ResponseEntity<List<ConsultationNote>> getConsultationNote() {
        return ResponseEntity.ok(appointmentService.getAllConsultationNote());
    }

    @GetMapping("/{patientID}")
    public ResponseEntity<List<ConsultationNote>> getConsultationNoteByPatient(@PathVariable String patientID) {
        return ResponseEntity.ok(appointmentService.getConsultationNoteForPatient(patientID));
    }
}
