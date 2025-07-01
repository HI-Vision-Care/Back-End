package com.hivision.hivision.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum AppointmentStatus {
    SCHEDULED,
    ONGOING,
    CONFIRMED,
    CANCELLED,
    COMPLETED
    ;


}
