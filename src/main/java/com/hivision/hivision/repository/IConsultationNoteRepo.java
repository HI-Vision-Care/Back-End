package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.ConsultationNote;
import com.hivision.hivision.pojo.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IConsultationNoteRepo extends JpaRepository<ConsultationNote, String> {
    List<ConsultationNote> findByPatient(Patient patient);
}
