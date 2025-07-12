package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDiseaseRepo extends JpaRepository<Disease, Long> {
    Optional<Disease> findByName(String name);
}
