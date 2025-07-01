package com.hivision.hivision.controller;

import com.hivision.hivision.payload.request.GoogleLoginRequest;
import com.hivision.hivision.payload.response.ApiResponse;
import com.hivision.hivision.payload.response.GoogleLoginResponse;
import com.hivision.hivision.service.cservice.AuthenticationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
@SecurityRequirement(name = "api")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/google/login")
    public ApiResponse<GoogleLoginResponse> googleLogin(@RequestBody GoogleLoginRequest request) {
        var result = authenticationService.authenticateWithGoogle(request);
        return ApiResponse.<GoogleLoginResponse>builder()
                .code(1000)
                .data(result)
                .build();

    }
}
