package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.ARV;
import com.hivision.hivision.pojo.ArvDrugInteraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArvDrugInteractionRepo extends JpaRepository<ArvDrugInteraction, Integer> {
    ArvDrugInteraction findArvContraindicationByArv(ARV arv);
}