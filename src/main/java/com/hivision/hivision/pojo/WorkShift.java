package com.hivision.hivision.pojo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Entity
@Table(name = "WorkShift")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkShift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ShiftID", nullable = false)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "DoctorID")
    Doctor doctorID;

    @Size(max = 50)
    @Column(name = "Slot", length = 50)
    String slot;

    @Column(name = "Date")
    String date;

    @Column(name = "StartTime")
    Instant startTime;

    @Column(name = "EndTime")
    Instant endTime;

    @Size(max = 50)
    @Column(name = "Status", length = 50)
    String status;

    @Lob
    @Column(name = "Note")
    String note;

}