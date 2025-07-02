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
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "RecordID")
    String recordId;

    @OneToOne
    @JoinColumn(name = "AppointmentID", referencedColumnName = "AppointmentID")
    Appointment appointment;

    @Column(name = "Diagnosis")
    String diagnosis;

    @Column(name = "CreateDate")
    Instant createDate;

    @Column(name = "Note")
    String note;
}
