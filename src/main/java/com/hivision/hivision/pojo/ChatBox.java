package com.hivision.hivision.pojo;

import jakarta.persistence.*;
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
public class ChatBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ChatID", nullable = false)
    Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PatientID")
    Patient patient;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "StaffID")
    Staff staff;

}