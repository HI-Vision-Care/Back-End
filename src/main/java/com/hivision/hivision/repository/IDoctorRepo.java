package com.hivision.hivision.repository;

import com.hivision.hivision.dto.DoctorDTO;
import com.hivision.hivision.pojo.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDoctorRepo extends JpaRepository<Doctor, String> {
    // Tìm bác sĩ theo chuyên khoa
    List<Doctor> findBySpecialty(String specialty);
    Doctor findDoctorByDoctorID(String doctorID);
    boolean existsByDoctorID(String doctorID);
}
