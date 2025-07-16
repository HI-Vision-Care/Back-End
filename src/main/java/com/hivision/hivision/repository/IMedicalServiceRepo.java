package com.hivision.hivision.repository;

import com.hivision.hivision.dto.MedicalServiceDTO;
import com.hivision.hivision.pojo.MedicalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IMedicalServiceRepo extends JpaRepository<MedicalService, Long> {
//    MedicalServiceDTO findMedicalServiceByServiceID(Long id);
    Optional<MedicalService> findByServiceID(Long id);
    List<MedicalService> findByIsActiveTrue();
}
