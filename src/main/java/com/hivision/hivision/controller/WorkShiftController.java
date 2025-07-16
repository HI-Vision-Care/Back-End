package com.hivision.hivision.controller;

import com.hivision.hivision.dto.WorkShiftDTO;
import com.hivision.hivision.payload.request.WorkShiftRequest;
import com.hivision.hivision.pojo.WorkShift;
import com.hivision.hivision.service.cservice.WorkShiftService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
        List<WorkShiftDTO> workShiftDTOList = wsService.getAll();
        return ResponseEntity.ok(workShiftDTOList);
    }

    @PostMapping("/regis/{doctorID}")
    public ResponseEntity<String> createWorkShift(@RequestBody List<WorkShiftRequest> requests, @PathVariable String doctorID) {
        return new ResponseEntity<>(wsService.regisWS(requests,doctorID), HttpStatus.CREATED);
    }

    @GetMapping("/week")
    public ResponseEntity<List<WorkShiftDTO>> getWorkShiftsByWeek(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(name = "doctorId", required = false) String doctorId) {

        List<WorkShiftDTO> shifts = wsService.getShiftsForWeek(date, doctorId);
        return ResponseEntity.ok(shifts);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<WorkShiftDTO>> getWorkShiftsByDoctor(@PathVariable String doctorId) {
        List<WorkShiftDTO> shifts = wsService.getShiftsByDoctorId(doctorId);
        return ResponseEntity.ok(shifts);
    }

    @PatchMapping("/{wsID}")
    public ResponseEntity<List<WorkShiftDTO>> getWorkShiftsByDoctor(@PathVariable int wsID) {
        wsService.finishWorkShift(wsID);
        return ResponseEntity.noContent().build();
    }
}
