package com.hivision.hivision.repository.Inventory;

import com.hivision.hivision.pojo.Inventory.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepo extends JpaRepository<Product, Integer> {

}