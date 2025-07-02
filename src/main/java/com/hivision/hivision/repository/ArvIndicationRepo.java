package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.ARV;
import com.hivision.hivision.pojo.ArvIndication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArvIndicationRepo extends JpaRepository<ArvIndication, Integer> {
    ArvIndication findArvContraindicationByArv(ARV arv);
    @Query("SELECT a.indication FROM ArvIndication a WHERE a.arv = :arv")
    List<String> findIndicationsByArv(@Param("arv") ARV arv);
}