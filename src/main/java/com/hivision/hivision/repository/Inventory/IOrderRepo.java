package com.hivision.hivision.repository.Inventory;

import com.hivision.hivision.pojo.Inventory.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepo extends JpaRepository<Order, Integer> {
}