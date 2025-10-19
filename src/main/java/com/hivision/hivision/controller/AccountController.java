package com.hivision.hivision.controller;

import com.hivision.hivision.dto.AccountDTO;
import com.hivision.hivision.payload.request.*;
import com.hivision.hivision.payload.response.ApiResponse;
import com.hivision.hivision.payload.response.LoginResponse;
import com.hivision.hivision.service.iservice.IAccountService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@CrossOrigin("*")
@SecurityRequirement(name = "api")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountController {
    IAccountService iAccountService;

    @PostMapping("/register")
    public ResponseEntity<AccountDTO> register(@RequestBody @Valid RegisterRequest request) {

        return new ResponseEntity<>(iAccountService.register(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(iAccountService.login(request));
    }

    @GetMapping
    public ResponseEntity<?> getAllAccounts() {
        return ResponseEntity.ok(iAccountService.getAllAccounts());
    }

    @PostMapping("/creation")
    public ResponseEntity<AccountDTO> createAccount(@Valid @RequestBody AccountCreationRequest request) {
        return new ResponseEntity<>(iAccountService.createAccount(request), HttpStatus.CREATED);
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<ApiResponse<String>> updateAccount(@PathVariable String accountId, @RequestBody @Valid UpdateAccountRequest request) {
        iAccountService.updateAccount(accountId, request);
        ApiResponse<String> response = ApiResponse.<String>builder().data("Account updated successfully!").build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<ApiResponse<String>> deleteAccount(@PathVariable String accountId) {
        iAccountService.deleteAccount(accountId);
        ApiResponse<String> response = ApiResponse.<String>builder().data("Account deleted successfully!").build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 6.1. Yêu cầu gửi OTP
    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse<String>> forgotPassword(@RequestBody @Valid ForgotPasswordRequest request) {
        iAccountService.forgotPassword(request);
        ApiResponse<String> response = ApiResponse.<String>builder()
                .data("Mã OTP đã được gửi đến email của bạn.")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 6.2. Xác thực OTP
    @PostMapping("/verify-otp")
    public ResponseEntity<ApiResponse<String>> verifyOtp(@RequestBody @Valid VerifyOtpRequest request) {
        iAccountService.verifyOtp(request);
        ApiResponse<String> response = ApiResponse.<String>builder()
                .data("Xác thực OTP thành công. Bạn có thể đặt lại mật khẩu.")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 6.3. Đặt lại mật khẩu
    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse<String>> resetPassword(@RequestBody @Valid ResetPasswordRequest request) {
        iAccountService.resetPassword(request);
        ApiResponse<String> response = ApiResponse.<String>builder()
                .data("Đặt lại mật khẩu thành công.")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
