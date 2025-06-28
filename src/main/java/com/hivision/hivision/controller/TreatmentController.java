package com.hivision.hivision.controller;

import com.hivision.hivision.dto.TreatmentDTO;
import com.hivision.hivision.payload.request.TreatmentRequest;
import com.hivision.hivision.service.iservice.ITreatmentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/treatment")
@RequiredArgsConstructor
@CrossOrigin("*")
@SecurityRequirement(name = "api")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TreatmentController {
    ITreatmentService treatmentService;

    @PostMapping("/{patentID}")
    public ResponseEntity<TreatmentDTO> createTreatment(@RequestBody TreatmentRequest request,@PathVariable String patentID) {
        return new ResponseEntity<>(treatmentService.createTreatment(request,patentID), HttpStatus.CREATED);
    }
}
