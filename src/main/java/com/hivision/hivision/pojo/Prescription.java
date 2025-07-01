package com.hivision.hivision.pojo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Prescription {
    @Id
    @Size(max = 255)
    @Column(name = "PrescriptionID", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    String prescriptionID;

    @ManyToOne
    @JoinColumn(name = "PatientID")
    Patient patient;

    @Size(max = 255)
    @Column(name = "Dosage")
    String dosage;

    @Column(name = "Duration")
    String duration;

    @Column(name = "\"Date\"")
    Instant date;

    @Size(max = 255)
    @Column(name = "PrescribeBy")
    String prescribeBy;

}