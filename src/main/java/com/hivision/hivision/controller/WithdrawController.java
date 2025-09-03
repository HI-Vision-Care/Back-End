package com.hivision.hivision.controller;

import com.hivision.hivision.dto.WithdrawDTO;
import com.hivision.hivision.payload.request.RejectWithdrawRequest;
import com.hivision.hivision.payload.request.WithdrawRequest;
import com.hivision.hivision.service.iservice.IWithdrawService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
@RequiredArgsConstructor
@CrossOrigin("*")
@SecurityRequirement(name = "api")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WithdrawController {
    IWithdrawService withdrawService;

    @PostMapping("/request-withdraw/{accountId}")
    public ResponseEntity<WithdrawDTO> createWithdraw(@RequestBody WithdrawRequest request, @PathVariable String accountId) {
        WithdrawDTO withdraw = withdrawService.createWithdraw(request, accountId);
        return ResponseEntity.ok(withdraw);
    }

    @PutMapping("/approve-withdraw/{withdrawId}/{staffId}")
    public ResponseEntity<WithdrawDTO> approveWithdraw(@PathVariable Long withdrawId, @PathVariable String staffId) {
        WithdrawDTO withdraw = withdrawService.approveWithdraw(withdrawId, staffId);
        return ResponseEntity.ok(withdraw);
    }

    @PutMapping("/reject-withdraw/{withdrawId}/{staffId}")
    public ResponseEntity<WithdrawDTO> rejectWithdraw(@PathVariable Long withdrawId, @PathVariable String staffId, @RequestBody RejectWithdrawRequest request) {
        WithdrawDTO withdraw = withdrawService.rejectWithdraw(withdrawId, staffId, request);
        return ResponseEntity.ok(withdraw);
    }

    @GetMapping("/get-withdraw/{accountId}")
    public ResponseEntity<WithdrawDTO> getWithdrawByAccountId(@PathVariable String accountId) {
        WithdrawDTO withdraw = withdrawService.getWithdrawByAccountId(accountId);
        return ResponseEntity.ok(withdraw);
    }

    @GetMapping("/get-all-withdraw")
    public ResponseEntity<?> getAllWithdraw() {
        return ResponseEntity.ok(withdrawService.getAllWithdraw());
    }
}
