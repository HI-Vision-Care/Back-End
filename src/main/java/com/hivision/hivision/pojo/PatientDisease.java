package com.hivision.hivision.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PatientDisease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PatientDiseaseID", nullable = false)
    Long patientDiseaseID;


    @ManyToOne
    @JoinColumn(name = "PatientID", referencedColumnName = "PatientID")
    @JsonBackReference
    Patient patient;

    @ManyToOne
    @JoinColumn(name = "DiseaseID", referencedColumnName = "DiseaseID")
    Disease disease;
}
