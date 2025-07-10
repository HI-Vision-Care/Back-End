package com.hivision.hivision.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountDTO {
    String token;
    String id;
    String password;
    String email;
    String phone;
}
