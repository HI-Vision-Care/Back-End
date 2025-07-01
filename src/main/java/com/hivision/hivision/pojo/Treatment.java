package com.hivision.hivision.pojo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Entity
@Table(name = "Treatment")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Treatment {
    @Id
    @Column(name = "TreatmentID", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    String treatmentID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PatientID")
    Patient patient;


    @Column(name = "StartDate")
    Instant startDate;

    @Column(name = "EndDate")
    Instant endDate;

    @Size(max = 255)
    @Column(name = "Status")
    String status;

    @Lob
    @Column(name = "Note")
    String note;

}