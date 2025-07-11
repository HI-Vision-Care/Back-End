package com.hivision.hivision.payload.request;


import com.hivision.hivision.enums.ConsultationStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConsultationPayload {

    String accountID;
    String name;
    @Builder.Default
    ConsultationStatus status = ConsultationStatus.REQUIRE;
    String note;
    @Builder.Default
    Instant createdAt = Instant.now();
}
