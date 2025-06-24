package com.hivision.hivision.controller;

import com.hivision.hivision.dto.WorkShiftDTO;
import com.hivision.hivision.payload.request.WorkShiftRequest;
import com.hivision.hivision.service.WorkShiftService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/work-shift")
@RequiredArgsConstructor
@CrossOrigin("*")
@SecurityRequirement(name = "api")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WorkShiftController {
    WorkShiftService wsService;

    @GetMapping
    public ResponseEntity<List<WorkShiftDTO>> getAllWorkShifts() {
        return ResponseEntity.ok(wsService.getAll());
    }

    @PostMapping("/regis")
    public ResponseEntity<String> createWorkShift(@RequestBody List<WorkShiftRequest> requests) {
        return new ResponseEntity<>(wsService.regisWS(requests), HttpStatus.CREATED);
    }
}
