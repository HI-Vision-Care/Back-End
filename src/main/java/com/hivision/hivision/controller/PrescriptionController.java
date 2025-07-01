package com.hivision.hivision.controller;

import com.hivision.hivision.payload.request.PreArvRequest;
import com.hivision.hivision.payload.request.PrescriptionRequest;
import com.hivision.hivision.pojo.Prescription;
import com.hivision.hivision.pojo.PrescriptionARV;
import com.hivision.hivision.service.iservice.IPreArvService;
import com.hivision.hivision.service.iservice.IPrescriptionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prescription")
@RequiredArgsConstructor
@CrossOrigin("*")
@SecurityRequirement(name = "api")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PrescriptionController {
    IPrescriptionService prescriptionService;
    IPreArvService preArvService;

    @PostMapping("/create")
    public ResponseEntity<Prescription> createPrescriptions(@RequestBody PrescriptionRequest request) {
            Prescription createdPrescriptions = prescriptionService.createPrescription(request);
            return new ResponseEntity<>(createdPrescriptions, HttpStatus.CREATED);

    }

    @PostMapping("/add")
    public ResponseEntity<List<PrescriptionARV>> addARVtoPre(@RequestBody PreArvRequest request) {
////        try {
//            List<PrescriptionARV> addPreArv = preArvService.addPreArv(request);
            return new ResponseEntity<>(preArvService.addPreArv(request), HttpStatus.CREATED);
//        } catch (Exception e) {
//            // Có thể tạo một lớp xử lý lỗi toàn cục (GlobalExceptionHandler) để code sạch hơn
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        }
    }
}
