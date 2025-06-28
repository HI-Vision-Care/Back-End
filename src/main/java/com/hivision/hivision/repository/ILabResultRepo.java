package com.hivision.hivision.repository;

import com.hivision.hivision.dto.LabResultDTO;
import com.hivision.hivision.pojo.LabResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILabResultRepo extends JpaRepository<LabResult, String> {
    List<LabResult> findByMedicalRecord_RecordId(String recordId);
}
