package com.hivision.hivision.controller;

import com.hivision.hivision.dto.AccountDTO;
import com.hivision.hivision.payload.request.LoginRequest;
import com.hivision.hivision.payload.request.RegisterRequest;
import com.hivision.hivision.payload.response.LoginResponse;
import com.hivision.hivision.service.IAccountService;
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

    @DeleteMapping("/{accountId}")
    public ResponseEntity<ApiResponse<String>> deleteAccount(@PathVariable String accountId) {
        iAccountService.deleteAccount(accountId);
        ApiResponse<String> response = ApiResponse.<String>builder().data("Account deleted successfully!").build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
