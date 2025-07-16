package com.hivision.hivision.repository;

import com.hivision.hivision.pojo.ServiceTestItem;
import com.hivision.hivision.pojo.TestItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IServiceTestItemRepo extends JpaRepository<ServiceTestItem, Long> {
    @Query("SELECT sti.testItem FROM ServiceTestItem sti WHERE sti.medicalService.serviceID = :serviceId")
    List<TestItem> findTestsByServiceId(@Param("serviceId") Long serviceId);

    List<ServiceTestItem> findByMedicalService_ServiceID(Long serviceId);
}
