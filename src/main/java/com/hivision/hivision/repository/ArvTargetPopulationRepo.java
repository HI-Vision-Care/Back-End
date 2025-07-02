package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.ARV;
import com.hivision.hivision.pojo.ArvTargetPopulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArvTargetPopulationRepo extends JpaRepository<ArvTargetPopulation, Integer> {
    ArvTargetPopulation findArvContraindicationByArv(ARV arv);
}