package com.hivision.hivision.controller;

import com.hivision.hivision.pojo.Inventory.Supplier;
import com.hivision.hivision.service.iservice.iInventory.ISupplierService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@SecurityRequirement(name = "api")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/supplier")
class SupplierController {
    ISupplierService supplierService;

    @GetMapping()
    public ResponseEntity<List<Supplier>> findAllSupplier() {
        return ResponseEntity.ok(supplierService.findAllSupplier());
    }
}
