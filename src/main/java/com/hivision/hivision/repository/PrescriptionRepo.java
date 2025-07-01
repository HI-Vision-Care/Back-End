package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepo extends JpaRepository<Prescription, String> {
}