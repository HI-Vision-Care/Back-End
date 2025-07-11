package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.RegimenARV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRegimenARVRepo extends JpaRepository<RegimenARV, Long> {
}
