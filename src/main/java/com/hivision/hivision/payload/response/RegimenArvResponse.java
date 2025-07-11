package com.hivision.hivision.payload.response;

import com.hivision.hivision.pojo.ARV;
import com.hivision.hivision.pojo.Regimen;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegimenArvResponse {
    Regimen regiment;
    List<ARV> arvs;
}
