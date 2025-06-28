package com.hivision.hivision.pojo;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "DoctorID")
    String doctorID;

    @OneToOne
    @JoinColumn(name = "AccountID", referencedColumnName = "AccountID")
    Account account;

    @Column(name = "FullName")
    String name;

    @Column(name = "Gender")
    String gender;

    @Column(name = "Specialty")
    String specialty;

    @Column(name = "Degrees")
    String degrees;

    @Column(name = "Img")
    String img;
}
