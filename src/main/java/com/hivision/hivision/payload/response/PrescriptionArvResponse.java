package com.hivision.hivision.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hivision.hivision.pojo.ARV;
import com.hivision.hivision.pojo.Prescription;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PrescriptionArvResponse {
    Prescription prescription;
    List<ARV> arvList;
}
