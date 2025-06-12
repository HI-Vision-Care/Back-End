package com.hivision.hivision.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hivision.hivision.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginResponse {
    String token;
    String username;
    Role role;
}
