package com.hivision.hivision.pojo;

import com.hivision.hivision.enums.ConsultationStatus;
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
@Access(AccessType.FIELD)
public class ChatBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ChatID", nullable = false)
    Integer id;

    @OneToOne
    @JoinColumn(name = "AccPatientID")
    Account accPatient;

    @ManyToOne
    @JoinColumn(name = "AccStaffID")
    Account accStaff;


    @Enumerated(EnumType.STRING)
    ConsultationStatus status;

    @Size(max = 255)
    @Column(name = "Note")
    String note;

    @Column(name = "CreatedAt")
    Instant createdAt;

}