package com.hivision.hivision.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DoctorDTO {
    String doctorID;
    String name;
    String gender;
    String specialty;
    String degrees;
    String avatar;
}
