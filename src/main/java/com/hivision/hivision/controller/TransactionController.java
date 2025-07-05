package com.hivision.hivision.controller;

import com.hivision.hivision.dto.TransactionsDTO;
import com.hivision.hivision.service.iservice.ITransactionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
@SecurityRequirement(name = "api")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransactionController {
    ITransactionService transactionService;



    @GetMapping("/view/{accountId}")
    public ResponseEntity<List<TransactionsDTO>> getTransaction(@PathVariable String accountId) {
        return ResponseEntity.ok(transactionService.getTransactions(accountId));
    }

    @GetMapping
    public ResponseEntity<List<TransactionsDTO>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @PutMapping("/transferToAppointment/{appointmentId}/{accountId}")
    public ResponseEntity<Void> transferToAppointment(@PathVariable String appointmentId, @PathVariable String accountId) {
        transactionService.transferToAppointment(appointmentId, accountId);
        return ResponseEntity.noContent().build();
    }
}
