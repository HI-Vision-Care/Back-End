package com.hivision.hivision.controller;

import com.hivision.hivision.dto.AccountDTO;
import com.hivision.hivision.payload.request.LoginRequest;
import com.hivision.hivision.payload.request.RegisterRequest;
import com.hivision.hivision.payload.response.ApiResponse;
import com.hivision.hivision.payload.response.LoginResponse;
import com.hivision.hivision.service.IAccountService;
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
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountController {
    IAccountService iAccountService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AccountDTO>> register(@RequestBody @Valid RegisterRequest request) {
        AccountDTO accountDTO = iAccountService.register(request);
        ApiResponse<AccountDTO> response = ApiResponse.<AccountDTO>builder().data(accountDTO).build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    LoginResponse login(@RequestBody LoginRequest request) {
        return iAccountService.login(request);
    }
}
