package com.hivision.hivision.dto;

import com.hivision.hivision.pojo.Patient;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.hivision.hivision.pojo.Treatment}
 */
@Value
public class TreatmentDTO implements Serializable {
    Patient patient;
    //Arv arv;
    Instant startDate;
    Instant endDate;
    @Size(max = 255)
    String prescribeBy;
    @Size(max = 255)
    String status;
    String note;
}