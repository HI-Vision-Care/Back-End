package com.hivision.hivision.dto;

import com.hivision.hivision.enums.WorkShiftStatus;
import com.hivision.hivision.pojo.Doctor;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.hivision.hivision.pojo.WorkShift}
 */
@Value
public class WorkShiftDTO implements Serializable {
    Integer id;
//    Doctor doctor;
    @Size(max = 50)
    String slot;
    LocalDate date;
    LocalDateTime startTime;
    LocalDateTime endTime;
    @Size(max = 50)
    WorkShiftStatus status;
    String note;
}