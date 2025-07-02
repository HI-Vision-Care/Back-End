package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.ARV;
import com.hivision.hivision.pojo.ArvIndication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArvIndicationRepo extends JpaRepository<ArvIndication, Integer> {
    ArvIndication findArvContraindicationByArv(ARV arv);
}