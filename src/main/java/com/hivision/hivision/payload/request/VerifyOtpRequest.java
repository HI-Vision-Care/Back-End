package com.hivision.hivision.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VerifyOtpRequest {
    @Email(message = "Email is not valid")
    @NotBlank(message = "Email is required")
    String email;

    @NotBlank(message = "OTP is required")
    String otp;
}
