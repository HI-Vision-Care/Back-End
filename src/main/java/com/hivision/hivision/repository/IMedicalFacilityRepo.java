package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.MedicalFacility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMedicalFacilityRepo extends JpaRepository<MedicalFacility, String> {
}