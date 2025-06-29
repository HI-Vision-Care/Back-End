package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.WorkShift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDateTime;
import java.util.List;

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


}