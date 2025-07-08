package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.LabResult;
import com.hivision.hivision.pojo.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILabResultRepo extends JpaRepository<LabResult, String> {
    List<LabResult> findByMedicalRecord_RecordIdIn(List<String> recordIds);
    List<LabResult> findByMedicalRecord(MedicalRecord medicalRecord);
}
