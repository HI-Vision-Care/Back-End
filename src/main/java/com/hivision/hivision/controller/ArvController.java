package com.hivision.hivision.controller;

import com.hivision.hivision.dto.AppointmentDTO;
import com.hivision.hivision.payload.response.ArvResponse;
import com.hivision.hivision.repository.IArvRepo;
import com.hivision.hivision.service.iservice.IArvService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/arv")
@RequiredArgsConstructor
@CrossOrigin("*")
@SecurityRequirement(name = "api")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ArvController {
    IArvService arvService;

    @GetMapping
    public ResponseEntity<List<ArvResponse>> getARVs() {
        return ResponseEntity.ok(arvService.getARVs());
    }
}
