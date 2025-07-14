package com.hivision.hivision.controller;

import com.hivision.hivision.payload.request.ArvRequest;
import com.hivision.hivision.payload.request.PresDataWrapper;
import com.hivision.hivision.payload.request.PrescriptionRequest;
import com.hivision.hivision.payload.response.PrescriptionArvResponse;
import com.hivision.hivision.payload.response.PrescriptionResponse;
import com.hivision.hivision.pojo.Prescription;
import com.hivision.hivision.pojo.PrescriptionARV;
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

//    @PostMapping("/create")
//    public ResponseEntity<Prescription> createPrescriptions(@RequestBody PrescriptionRequest request) {
//            Prescription createdPrescriptions = prescriptionService.createPrescription(request);
//            return new ResponseEntity<>(createdPrescriptions, HttpStatus.CREATED);
//
//    }

    @PostMapping("/create")
    public ResponseEntity<PrescriptionResponse> createPrescription(@RequestBody PresDataWrapper dataWrapper, @RequestParam("patientId") String patientId) {
////        try {
//            List<PrescriptionARV> addPreArv = preArvService.addPreArv(request);
        PrescriptionRequest prescriptionRequest = dataWrapper.getPrescriptionRequest();
        List<ArvRequest> arvRequests = dataWrapper.getArvRequests();
            return new ResponseEntity<>(prescriptionService.createPrescription(prescriptionRequest,arvRequests,patientId), HttpStatus.CREATED);
//        } catch (Exception e) {
//            // Có thể tạo một lớp xử lý lỗi toàn cục (GlobalExceptionHandler) để code sạch hơn
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        }
    }

    @GetMapping("/pre-arv/{appointmentID}")
    public ResponseEntity<PrescriptionArvResponse> getAllPresArvByAppointment(@PathVariable String appointmentID) {
        PrescriptionArvResponse prescriptionARVs = prescriptionService.getPresArvByApppointment(appointmentID);
        return new ResponseEntity<>(prescriptionARVs, HttpStatus.OK);
//        return null;
    }

    @GetMapping("/all-pre-arv/{patientId}")
    public ResponseEntity<List<PrescriptionArvResponse>> getAllPresArvByPatient(@PathVariable("patientId") String patientId) {
        List<PrescriptionArvResponse> prescriptionARVs = prescriptionService.getAllPresArvByPatient(patientId);
        return new ResponseEntity<>(prescriptionARVs, HttpStatus.OK);
//        return null;
    }
}
