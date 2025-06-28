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
    @Size(max = 255)
    @Column(name = "RegimentID", nullable = false)
    private String regimentID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PatientID")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ARV_ID")
    private Arv arv;

    @Column(name = "StartDate")
    private Instant startDate;

    @Column(name = "EndDate")
    private Instant endDate;

    @Size(max = 255)
    @Column(name = "PrescribeBy")
    private String prescribeBy;

    @Size(max = 255)
    @Column(name = "Status")
    private String status;

    @Lob
    @Column(name = "Note")
    private String note;

}