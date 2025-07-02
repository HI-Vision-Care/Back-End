package com.hivision.hivision.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.hivision.hivision.pojo.ConsultationNote}
 */
@Value
public class ConsultationNoteDTO implements Serializable {
    String phone;
    String name;
    String note;
}