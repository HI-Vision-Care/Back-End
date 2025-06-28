package com.hivision.hivision.pojo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Entity
@Table(name = "Appointment")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Appointment {
    @Id
    @Size(max = 255)
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "AppointmentID", nullable = false)
    String appointmentID;

    @ManyToOne
    @JoinColumn(name = "PatientID", referencedColumnName = "PatientID")
    Patient patient;

    @ManyToOne
    @JoinColumn(name = "DoctorID", referencedColumnName = "DoctorID")
    Doctor doctor;

    @OneToOne
    @JoinColumn(name = "ServiceID", referencedColumnName = "ServiceID")
    MedicalService medicalService;

    @Column(name = "AppointmentDate")
    Instant appointmentDate;

    @Size(max = 50)
    @Column(name = "Status", length = 50)
    String status;

    @Column(name = "IsAnonymous")
    Boolean isAnonymous;

    @Size(max = 255)
    @Column(name = "urlLink")
    String urlLink;

    @Lob
    @Column(name = "Note")
    String note;

    @Column(name = "CreateAt")
    Instant createAt;

}