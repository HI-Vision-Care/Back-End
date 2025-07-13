package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.ARV;
import com.hivision.hivision.pojo.Prescription;
import com.hivision.hivision.pojo.PrescriptionARV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreARVRepo extends JpaRepository<PrescriptionARV, Integer> {
    List<PrescriptionARV> findByPrescription(Prescription prescription);
    @Query("SELECT pa.arv FROM PrescriptionARV pa WHERE pa.prescription = :prescription")
    List<ARV> findArvsByPrescription(@Param("prescription") Prescription prescription);
}