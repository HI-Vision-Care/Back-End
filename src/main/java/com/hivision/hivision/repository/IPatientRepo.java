package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.Account;
import com.hivision.hivision.pojo.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface IPatientRepo extends JpaRepository<Patient, String> {
     Patient findPatientByAccount(Account account);

}
