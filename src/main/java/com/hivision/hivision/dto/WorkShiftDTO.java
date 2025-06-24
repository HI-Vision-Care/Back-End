package com.hivision.hivision.dto;

import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.hivision.hivision.pojo.WorkShift}
 */
@Value
public class WorkShiftDTO implements Serializable {
    Integer id;
    @Size(max = 50)
    String slot;
    Instant date;
    Instant startTime;
    Instant endTime;
    @Size(max = 50)
    String status;
    String note;
}