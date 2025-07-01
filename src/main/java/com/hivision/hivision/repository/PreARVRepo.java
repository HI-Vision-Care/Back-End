package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.PrescriptionARV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreARVRepo extends JpaRepository<PrescriptionARV, Integer> {
}