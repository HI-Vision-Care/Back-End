package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITreatmentRepo extends JpaRepository<Treatment, String> {
    Treatment findTreatmentByRegimentID(String id);
}