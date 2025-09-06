package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFacilityRepo extends JpaRepository<Facility, String> {
}