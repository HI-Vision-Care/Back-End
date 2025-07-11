package com.hivision.hivision.controller;

import com.hivision.hivision.service.iservice.IRegimenARVService;
import com.hivision.hivision.service.iservice.IRegimenService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/regimen")
@RequiredArgsConstructor
@CrossOrigin("*")
@SecurityRequirement(name = "api")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RegimenController {
    IRegimenService regimenService;
    IRegimenARVService regimenARVService;


    // Lấy tất cả phác đồ điều trị
    @GetMapping("/get-all-regimens")
    public ResponseEntity<?> getAllRegimens() {
        return ResponseEntity.ok(regimenService.getAllRegimens());
    }

    @GetMapping("/get-all-regimens-arv")
    public ResponseEntity<?> getAllRegimensARV() {
        return ResponseEntity.ok(regimenARVService.getAllRegimenARVs());
    }
}
