package com.hivision.hivision.pojo;

import com.hivision.hivision.enums.ConsultationStatus;
import jakarta.persistence.*;
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
@Access(AccessType.FIELD)
public class ChatBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ChatID", nullable = false)
    Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    ConsultationStatus status;

//    @OneToOne
//    @JoinColumn(name = "PatientID")
//    Patient patient;
//
//    @OneToOne
//    @JoinColumn(name = "StaffID")
//    Staff staff;

    @Column(name = "AccPatientID")
    String accPatientID;

    @Column(name = "AccStaffID")
    String accStaffID;

    @Column(name = "Note")
    String note;

    @Column(name = "CreatedAt")
    Instant createdAt;
}