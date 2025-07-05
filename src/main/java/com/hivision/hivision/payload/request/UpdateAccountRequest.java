package com.hivision.hivision.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateAccountRequest {
    String username;
    @Email(message = "INVALID_EMAIL")
    String email;
    @Size(min = 10, max = 10, message = "PHONE_INVALID")
    @Pattern(regexp = "^0[0-9]{9}$", message = "PHONE_INVALID")
    String phone;
    String avatar;
}
