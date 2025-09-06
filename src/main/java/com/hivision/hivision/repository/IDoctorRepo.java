package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.Account;
import com.hivision.hivision.pojo.Doctor;
import com.hivision.hivision.pojo.Facility;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDoctorRepo extends JpaRepository<Doctor, String> {
    List<Doctor> findBySpecialty(String specialty);
    Doctor findDoctorByAccount(Account account);
    Doctor findDoctorByDoctorID(String doctorID);
    boolean existsByDoctorID(String doctorID);
    List<Doctor> findByFacility(Facility facility);
}
