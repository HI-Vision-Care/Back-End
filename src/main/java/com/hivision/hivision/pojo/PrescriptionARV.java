package com.hivision.hivision.pojo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Access(AccessType.FIELD)
public class PrescriptionARV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PreArvID", nullable = false)
    Integer id;

    @Size(max = 25)
    @Column(name = "Dosage")
    String dosage;

    @Size(max = 25)
    @Column(name = "Duration")
    String duration;

    @ManyToOne
    @JoinColumn(name = "PrescriptionID")
    Prescription prescription;

    @ManyToOne
    @JoinColumn(name = "ARV_ID")
    ARV arv;

}