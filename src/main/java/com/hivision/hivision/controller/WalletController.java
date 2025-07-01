package com.hivision.hivision.controller;

import com.hivision.hivision.payload.request.WalletRequest;
import com.hivision.hivision.service.iservice.IWalletService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/wallet")
@RequiredArgsConstructor
@CrossOrigin("*")
@SecurityRequirement(name = "api")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WalletController {
    IWalletService walletService;

    @PostMapping("/{accountId}")
    public ResponseEntity<String> createWallet(@RequestBody WalletRequest request, @PathVariable String accountId) throws Exception {
        String vnPayURL = walletService.createUrl(request, accountId);
        return ResponseEntity.ok(vnPayURL);
    }

    // hàm callback từ VNPay
    @GetMapping("/vnpay-callback")
    public ResponseEntity<?> handleVnPayCallback(@RequestParam(name = "vnp_TxnRef") String vnpTxnRef,
                                                 @RequestParam(name = "vnp_ResponseCode") String responseCode,
                                                 @RequestParam(name = "vnp_Amount") String vnpAmount) {

        try {
//            UUID transactionId = UUID.fromString(vnpTxnRef); // Chuyển mã giao dịch từ String sang UUID
            double amount = Double.parseDouble(vnpAmount);  // Chuyển số tiền từ chuỗi
            walletService.handleVnPayCallback(vnpTxnRef, responseCode, amount);

            return ResponseEntity.ok("Transaction processed successfully.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/deposit/{accountId}")
    public ResponseEntity<Void> deposit(@PathVariable("accountId") String accountId, @RequestBody WalletRequest request) {
        walletService.deposit(accountId, request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/view/{accountId}")
    public ResponseEntity<?> getWallet(@PathVariable String accountId) {
        return ResponseEntity.ok(walletService.getWallet(accountId));
    }

}
