package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.Transactions;
import com.hivision.hivision.pojo.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITransactionsRepo extends JpaRepository<Transactions, String> {
    List<Transactions> findByWallet(Wallet wallet);
    Optional<Transactions> findByPayosOrderCode(Long payosOrderCode);
}
