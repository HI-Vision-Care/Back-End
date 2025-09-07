package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.Facility;
import com.hivision.hivision.pojo.Inventory;
import com.hivision.hivision.pojo.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory,Integer> {
    Optional<Inventory> findByProductAndFacility(Product product, Facility facility);
    List<Inventory> findByFacility(Facility facility);
}
