package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.ARV;
import com.hivision.hivision.pojo.ArvTargetPopulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArvTargetPopulationRepo extends JpaRepository<ArvTargetPopulation, Integer> {
    ArvTargetPopulation findArvContraindicationByArv(ARV arv);
    @Query("SELECT a.targetPopulation FROM ArvTargetPopulation a WHERE a.arv = :arv")
    List<String> findTargetPopulationsByArv(@Param("arv") ARV arv);
}