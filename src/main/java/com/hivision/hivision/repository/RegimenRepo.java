package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.Regimen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegimenRepo extends JpaRepository<Regimen, Integer> {

}