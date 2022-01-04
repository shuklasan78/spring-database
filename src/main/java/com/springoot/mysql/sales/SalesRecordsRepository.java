package com.springoot.mysql.sales;

import com.springoot.mysql.basic.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalesRecordsRepository extends JpaRepository<SalesRecordsEntity, Long> {
    List<SalesRecordsEntity> findAll();
   // List<SalesRecordsEntity> findByTitleContaining(String columnName);
}
