package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.ARV;
import com.hivision.hivision.pojo.ArvContraindication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArvContraindicationRepo extends JpaRepository<ArvContraindication, Integer> {
    ArvContraindication findArvContraindicationByArv(ARV arv);
}