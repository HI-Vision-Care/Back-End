package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.Patient;
import com.hivision.hivision.pojo.PatientDisease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPatientDiseaseRepo extends JpaRepository<PatientDisease, Long> {
    void deleteByPatient(Patient patient);
    List<PatientDisease> findByPatient(Patient patient);
}
