package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.ARV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IArvRepo extends JpaRepository<ARV, String> {
  ARV findByArvId(String id);
}