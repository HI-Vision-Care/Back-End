package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.ARV;
import com.hivision.hivision.pojo.Regimen;
import com.hivision.hivision.pojo.RegimenARV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRegimenARVRepo extends JpaRepository<RegimenARV, Long> {
    @Query("SELECT ra.arv FROM RegimenARV ra WHERE ra.regimen = :regimen")
    List<ARV> findArvByRegimen(@Param("regimen") Regimen regimen);
}
