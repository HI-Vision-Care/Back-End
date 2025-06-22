package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.Appointment;
import com.hivision.hivision.pojo.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IAppointmentRepo extends JpaRepository<Appointment, String> {
    List<Appointment> findByPatient(Patient patient);

}
