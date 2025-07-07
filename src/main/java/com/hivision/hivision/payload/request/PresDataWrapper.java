package com.hivision.hivision.payload.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PresDataWrapper {
    PrescriptionRequest prescriptionRequest;
    List<ArvRequest> arvRequests;


}
