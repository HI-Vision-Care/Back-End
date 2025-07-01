package com.hivision.hivision.controller;

import com.hivision.hivision.dto.MedicalServiceDTO;
import com.hivision.hivision.pojo.MedicalService;
import com.hivision.hivision.service.iservice.IMedicalService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medical-service")
@RequiredArgsConstructor
@CrossOrigin("*")
@SecurityRequirement(name = "api")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MedicalServiceController {
    IMedicalService medicalServiceService;


    @GetMapping
    public ResponseEntity<List<MedicalService>> getAllMedicalServices() {
        return ResponseEntity.ok(medicalServiceService.getAllMedicalServices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalServiceDTO> getMedicalServicesById(@PathVariable Long id) {
        return ResponseEntity.ok(medicalServiceService.getMedicalServiceById(id));
    }
}
