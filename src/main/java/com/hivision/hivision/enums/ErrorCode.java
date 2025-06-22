package com.hivision.hivision.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
    USER_EXISTED(422, "Username already existed!"),
    USER_NOT_FOUND(404,"User not found"),
    DOCTOR_NOT_FOUND(404,"Doctor not found"),
    PATIENT_NOT_FOUND(404,"Patient not found"),
    SERVICE_NOT_FOUND(404,"Service not found"),
    APPOINTMENT_NOT_FOUND(404,"Appointment not found"),
    EMAIL_EXISTED(422, "Email already existed!"),
    INVALID_EMAIL(401,"Invalid email address!"),
    PHONE_EXISTED(422, "Phone already existed!"),
    PHONE_REQUIRED(401, "Phone number is required!"),
    USERNAME_INVALID(400,"Username must be between 8 and 16 characters"),
    PHONE_INVALID(400,"Phone number must be 10 characters!!!"),
    UNAUTHENTICATED_USERNAME(400,"Invalid username!"),
    UNAUTHENTICATED_PASSWORD(401,"Invalid password!"),
    TOKEN_EXPIRED(401, "Token has expired"),
    TOKEN_ERROR(401, "Invalid token"),
    EXPIRE_TOKEN(401, "Token has expired"),
    ERROR_TOKEN(401,"Invalid Token"),
    EMPTY_TOKEN(400, "Empty token!"),
    ;

    int code;
    String message;
    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
