package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.Prescription;
import com.hivision.hivision.pojo.PrescriptionARV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreARVRepo extends JpaRepository<PrescriptionARV, Integer> {
    List<PrescriptionARV> findByPrescription(Prescription prescription);

}