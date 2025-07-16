package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.Account;
import com.hivision.hivision.pojo.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStaffRepo extends JpaRepository<Staff, String> {
    Staff findStaffByStaffId(String staffID);
    Staff findStaffByAccount(Account account);
}
