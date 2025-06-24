package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.WorkShift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWorkShiftRepository extends JpaRepository<WorkShift, Integer> {
}