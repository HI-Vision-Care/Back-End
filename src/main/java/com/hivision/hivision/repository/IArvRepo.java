package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.Arv;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArvRepo extends JpaRepository<Arv, String> {
  Arv findByArvId(String id);
}