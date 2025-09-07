package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.StockTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStockTransactionRepo extends JpaRepository<StockTransaction,Integer> {
}
