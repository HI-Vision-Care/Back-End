package com.hivision.hivision.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FacilityDTO {
    String name;
    String address;
    String phone;
    String description;
    String image;
    String time;
    String rating;
    String latitude;
    String longitude;
}
