package com.hivision.hivision.controller;

import com.hivision.hivision.payload.request.CheckoutRequest;
import com.hivision.hivision.payload.request.OrderRequest;
import com.hivision.hivision.pojo.Inventory.Order;
import com.hivision.hivision.service.iservice.iInventory.IOrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@CrossOrigin("*")
@SecurityRequirement(name = "api")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderController {
    IOrderService orderService;

    @PostMapping("/{patientID}")
    public ResponseEntity<Order> AddToOrder(@PathVariable String patientID, @RequestBody OrderRequest request) {
        return new ResponseEntity<>(orderService.addItemToOrder(request,patientID), HttpStatus.CREATED);
    }
}
