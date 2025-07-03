package com.hivision.hivision.repository;

import com.hivision.hivision.enums.Role;
import com.hivision.hivision.pojo.Account;
import com.hivision.hivision.pojo.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAccountRepo extends JpaRepository<Account,String> {
    Optional<Account> findByUsername(String username);
    Optional<Account> findByEmail(String email);
    Optional<Account> findByPhone(String phone);
    Account findAccountByUsername(String username);
    Account findAccountById(String id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    List<Account> findByRoleIsNot(Role role);
}
