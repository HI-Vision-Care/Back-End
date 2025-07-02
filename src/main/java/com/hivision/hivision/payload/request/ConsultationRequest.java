package com.hivision.hivision.payload.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConsultationRequest {
    @Pattern(regexp="(^$|[0-9]{10})",message = "PHONE_INVALID")
    @Size(min=10,max=11,message = "PHONE_INVALID")
    String phone;
    String name;
    String note;
}
