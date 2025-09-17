package com.hivision.hivision.repository.Inventory;

import com.hivision.hivision.pojo.Inventory.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISupplierRepo extends JpaRepository<Supplier, Integer> {
}