package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.ARV;
import com.hivision.hivision.pojo.ArvSideEffect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArvSideEffectRepo extends JpaRepository<ArvSideEffect, Integer> {
    ArvSideEffect findArvContraindicationByArv(ARV arv);
    @Query("SELECT a.sideEffect FROM ArvSideEffect a WHERE a.arv = :arv")
    List<String> findSideEffectsByArv(@Param("arv") ARV arv);
}