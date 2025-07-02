package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.ARV;
import com.hivision.hivision.pojo.ArvDrugInteraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArvDrugInteractionRepo extends JpaRepository<ArvDrugInteraction, Integer> {
    ArvDrugInteraction findArvContraindicationByArv(ARV arv);
    @Query("SELECT a.drugInteraction FROM ArvDrugInteraction a WHERE a.arv = :arv")
    List<String> findDrugInteractionsByArv(@Param("arv") ARV arv);
}