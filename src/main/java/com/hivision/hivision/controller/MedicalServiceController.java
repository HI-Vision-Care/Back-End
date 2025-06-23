package com.hivision.hivision.controller;

import com.hivision.hivision.pojo.MedicalService;
import com.hivision.hivision.service.IMedicalService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<MedicalService> getAllMedicalServices() {
        return medicalServiceService.getAllMedicalServices();
    }
}
