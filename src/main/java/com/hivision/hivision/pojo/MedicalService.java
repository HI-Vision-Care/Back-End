package com.hivision.hivision.pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MedicalService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ServiceID", nullable = false)
    Long serviceID;

    @Column(name = "Name")
    String name;

    @Column(name = "Description")
    String description;

    @Column(name = "Price")
    Double price;

    @Column(name = "Img")
    String img;

    @Column(name = "Type")
    String type;

    @Column(name = "Specialty")
    String specialty;

    @Column(name = "IsActive")
    Boolean isActive;

    @Column(name = "IsRequireDoctor")
    Boolean isRequireDoctor;

    @Column(name = "IsOnline")
    Boolean isOnline;

    @Column(name = "CreateAt")
    Instant createAt;

}
