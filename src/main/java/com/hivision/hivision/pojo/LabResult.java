package com.hivision.hivision.pojo;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LabResult {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "LabResultID")
    String id;

    @ManyToOne
    @JoinColumn(name = "RecordID", referencedColumnName = "RecordID")
    MedicalRecord medicalRecord;

    @Column(name = "TestType")
    String testType;

    @Column(name = "ResultValue")
    String resultValue;

    @Column(name = "TestDate")
    Instant testDate;
}
