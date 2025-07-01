package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.ConsultationNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IConsultationNoteRepo extends JpaRepository<ConsultationNote, String> {
}
