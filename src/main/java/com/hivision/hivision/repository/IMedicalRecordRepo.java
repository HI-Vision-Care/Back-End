package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMedicalRecordRepo extends JpaRepository<MedicalRecord, String> {

    List<MedicalRecord> findByAppointment_AppointmentIDIn(List<String> appointmentIds);
}
