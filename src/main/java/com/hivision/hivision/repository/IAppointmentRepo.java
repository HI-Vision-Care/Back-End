package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.Appointment;
import com.hivision.hivision.pojo.Doctor;
import com.hivision.hivision.pojo.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface IAppointmentRepo extends JpaRepository<Appointment, String> {
    List<Appointment> findByPatient(Patient patient);
    List<Appointment> findByDoctor(Doctor doctor);

}
