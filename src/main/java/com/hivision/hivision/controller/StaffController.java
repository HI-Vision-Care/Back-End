package com.hivision.hivision.controller;

import com.hivision.hivision.pojo.Staff;
import com.hivision.hivision.service.iservice.IStaffService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/staff")
@RequiredArgsConstructor
@CrossOrigin("*")
@SecurityRequirement(name = "api")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StaffController {
    IStaffService staffService;

    @GetMapping("/profile/{accountId}")
    public ResponseEntity<Staff> getStaffByAccountID(@PathVariable String accountId) {
        return ResponseEntity.ok(staffService.getStaffByAccountID(accountId));
    }
}
