package com.hivision.hivision.controller;

import com.hivision.hivision.dto.LabResultDTO;
import com.hivision.hivision.service.iservice.ILabResultService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lab-result")
@RequiredArgsConstructor
@CrossOrigin("*")
@SecurityRequirement(name = "api")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LabResultController {
    ILabResultService labResultService;

    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity<List<LabResultDTO>> getLabResultsByAppointmentId(@PathVariable  String appointmentId) {
        return ResponseEntity.ok(labResultService.getLabResultsByAppointmentId(appointmentId));
    }
}
