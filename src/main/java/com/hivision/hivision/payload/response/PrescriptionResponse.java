package com.hivision.hivision.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hivision.hivision.payload.request.ArvRequest;
import com.hivision.hivision.pojo.Patient;
import com.hivision.hivision.pojo.PrescriptionARV;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PrescriptionResponse {
    Patient patient;
    List<PreArvResponse> preArvResponses;
    String prescribeBy;
}
