package com.hivision.hivision.dto;

import com.hivision.hivision.pojo.Patient;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.hivision.hivision.pojo.ConsultationNote}
 */
@Value
public class ConsultationNoteDTO implements Serializable {
    Integer id;
    String phone;
    String name;
    String note;
    Instant createAt;
    Patient patient;
}