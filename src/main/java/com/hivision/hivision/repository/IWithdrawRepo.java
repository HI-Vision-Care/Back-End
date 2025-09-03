package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.Account;
import com.hivision.hivision.pojo.Withdraw;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IWithdrawRepo extends JpaRepository<Withdraw, Long> {
    Optional<Withdraw> findWithdrawByAccount(Account account);
}
