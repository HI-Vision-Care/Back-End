package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.ARV;
import com.hivision.hivision.pojo.ArvContraindication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArvContraindicationRepo extends JpaRepository<ArvContraindication, Integer> {
    ArvContraindication findArvContraindicationByArv(ARV arv);
//    List<String> findContraindicationByArv(ARV arv);
    @Query("SELECT c.contraindication FROM ArvContraindication c WHERE c.arv = :arv")
    List<String> findContraindicationsByArv(@Param("arv") ARV arv);

}
