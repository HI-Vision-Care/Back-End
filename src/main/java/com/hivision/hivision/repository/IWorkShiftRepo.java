package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.Doctor;
import com.hivision.hivision.pojo.WorkShift;
import org.aspectj.weaver.patterns.ConcreteCflowPointcut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface IWorkShiftRepo extends JpaRepository<WorkShift, Integer> {
    @Query("SELECT ws FROM WorkShift ws WHERE ws.doctor.doctorID = :doctorID AND ws.date >= :startDate AND ws.date < :endDate ORDER BY ws.startTime ASC")
    List<WorkShift> findShiftsBetweenDatesForDoctor(
            @Param("doctorID") String doctorID,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime  endDate
    );

    @Query("SELECT ws FROM WorkShift ws WHERE ws.date >= :startDate AND ws.date < :endDate ORDER BY ws.startTime ASC")
    List<WorkShift> findShiftsBetweenDates(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    @Query("SELECT ws FROM WorkShift ws WHERE ws.doctor.doctorID = :doctorID ORDER BY ws.date ASC, ws.startTime ASC")
    List<WorkShift> findShiftsByDoctorId(@Param("doctorID") String doctorID);

    WorkShift findWorkShiftBySlotAndDoctorAndDate(String slot, Doctor doctor, Instant date);

}