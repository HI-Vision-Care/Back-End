package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.ARV;
import com.hivision.hivision.pojo.ArvSideEffect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArvSideEffectRepo extends JpaRepository<ArvSideEffect, Integer> {
    ArvSideEffect findArvContraindicationByArv(ARV arv);
}