package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.Account;
import com.hivision.hivision.pojo.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWalletRepo extends JpaRepository<Wallet, Long> {
    Wallet findWalletByAccount(Account account);
}
