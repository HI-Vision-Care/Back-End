package com.hivision.hivision.controller;

import com.hivision.hivision.payload.response.FacilityResponse;
import com.hivision.hivision.pojo.Facility;
import com.hivision.hivision.service.iservice.IFacilityService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/facility")
@RequiredArgsConstructor
@CrossOrigin("*")
@SecurityRequirement(name = "api")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FacilityController {
    IFacilityService facilityService;

    @GetMapping("/{facilityID}")
    ResponseEntity<FacilityResponse> facility(@PathVariable String facilityID) {
        return ResponseEntity.ok(facilityService.getByFacility(facilityID));
    }

    @GetMapping()
    ResponseEntity<List<Facility>> getAllFacility() {
        return ResponseEntity.ok(facilityService.getAll());
    }

}
