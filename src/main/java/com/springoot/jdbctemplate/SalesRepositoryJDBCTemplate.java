package com.springoot.jdbctemplate;

import com.springoot.mysql.sales.SalesRecordsEntity;

import java.util.List;

public interface SalesRepositoryJDBCTemplate {

    int save(SalesRecordsEntity salesRecords);

    int update(SalesRecordsEntity salesRecords);

    SalesRecordsEntity findById(Integer id);

    int deleteById(Long id);

    List<SalesRecordsEntity> findAll();

    List<SalesRecordsEntity> findByPublished(boolean published);

    List<SalesRecordsEntity> findByTitleContaining(String title);

    int deleteAll();
}
