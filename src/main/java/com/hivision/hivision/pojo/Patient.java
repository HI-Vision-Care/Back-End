package com.hivision.hivision.pojo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import net.minidev.json.annotate.JsonIgnore;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "PatientID", nullable = false)
    String patientID;

    @OneToOne
    @JoinColumn(name = "AccountID", referencedColumnName = "AccountID")
    Account account;

    @Size(max = 255)
    @Column(name = "Full_Name")
    String name;

    @Column(name = "DOB")
    String dob;
//    LocalDate dob;

    @Size(max = 10)
    @Column(name = "Gender", length = 10)
    String gender;

    @Size(max = 50)
    @Column(name = "Med_No", length = 50)
    String medNo;

    @Column(name = "Med_Date")
    String medDate;
//    LocalDate medDate;

    @Size(max = 255)
    @Column(name = "Med_Facility")
    String medFac;


    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    List<PatientDisease> patientDiseases;

}