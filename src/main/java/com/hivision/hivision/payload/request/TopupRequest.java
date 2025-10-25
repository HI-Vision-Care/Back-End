package com.hivision.hivision.payload.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TopupRequest {
    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "1", message = "Amount must be greater than 0")
    long amount;
}
