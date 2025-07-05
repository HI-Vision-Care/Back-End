package com.hivision.hivision.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequest {
    String password;
    @Email(message = "INVALID_EMAIL")
    String email;
    @Size(min = 10,max = 10,message = "PHONE_INVALID")
    String phone;
}
