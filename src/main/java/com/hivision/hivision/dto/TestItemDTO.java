package com.hivision.hivision.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TestItemDTO {
//    Long testID;
    String testName;
    String testDescription;
}
