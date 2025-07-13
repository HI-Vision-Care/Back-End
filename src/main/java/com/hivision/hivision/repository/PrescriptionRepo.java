package com.hivision.hivision.repository;

import com.hivision.hivision.enums.PresStatus;
import com.hivision.hivision.pojo.ARV;
import com.hivision.hivision.pojo.Patient;
import com.hivision.hivision.pojo.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepo extends JpaRepository<Prescription, String> {
    Prescription findByPatientAndStatus(Patient patient,PresStatus status);
    boolean existsByPatientAndStatus(Patient patient,PresStatus status);

}