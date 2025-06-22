package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.MedicalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMedicalServiceRepo extends JpaRepository<MedicalService, Long> {

}
