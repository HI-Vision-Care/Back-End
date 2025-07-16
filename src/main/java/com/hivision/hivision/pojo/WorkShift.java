package com.hivision.hivision.pojo;

import com.hivision.hivision.enums.WorkShiftStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    Doctor doctor;

    @Size(max = 50)
    @Column(name = "Slot", length = 50)
    String slot;

    @Column(name = "Date")
    Instant  date;

    @Column(name = "StartTime")
    LocalDateTime startTime;

    @Column(name = "EndTime")
    LocalDateTime endTime;

    @Size(max = 50)
    @Column(name = "Status", length = 50)
    WorkShiftStatus status;

    @Lob
    @Column(name = "Note")
    String note;

}