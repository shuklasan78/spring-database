package com.springoot.jdbctemplate;

import com.springoot.mysql.sales.SalesRecordsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JDBCTemplateServiceRepository implements SalesRepositoryJDBCTemplate {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(SalesRecordsEntity salesRecords) {
        Object[] obj = getObject(salesRecords);
        var sqlQuery = "INSERT INTO sales_records (region, country, itemType,salesChannel,orderPriority,orderDate,orderID, shipDate, unitsSold, unitPrice, unitCost, totalRevenue, totalCost, totalProfit) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
        int retrn = jdbcTemplate.update(sqlQuery,obj);
        return retrn;
    }

    @Override
    public int update(SalesRecordsEntity salesRecords) {
        Object[] obj = getObject(salesRecords);
        var sqlQuery =  "UPDATE sales_records SET region =?, country=?, itemType=?,salesChannel=?,orderPriority=?,orderDate=?,orderID=?, shipDate=?, unitsSold=?, unitPrice=?, unitCost=?, totalRevenue=?, totalCost=?, totalProfit=? WHERE id=?";
        int retrn = jdbcTemplate.update(sqlQuery,obj);
        return retrn;
    }

    @Override
    public SalesRecordsEntity findById(Integer id) {
        try {
            SalesRecordsEntity salesObj = jdbcTemplate.queryForObject("SELECT * FROM sales_records WHERE id=?",
                    BeanPropertyRowMapper.newInstance(SalesRecordsEntity.class), id);

            return salesObj;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteById(Long id) {
        var sql = "DELETE FROM sales_records WHERE id=?";
        int retrn =  jdbcTemplate.update(sql, id);
        return retrn;
    }

    @Override
    public List<SalesRecordsEntity> findAll() {
        var sql = "SELECT * from sales_records";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(SalesRecordsEntity.class));
    }

    @Override
    public List<SalesRecordsEntity> findByPublished(boolean published) {
        return null;
    }

    @Override
    public List<SalesRecordsEntity> findByTitleContaining(String title) {
        return null;
    }

    @Override
    public int deleteAll() {
        return 0;
    }

    private static Object[] getObject(SalesRecordsEntity salesRecords) {
        Object[] obj = { salesRecords.getRegion(),
                salesRecords.getCountry(),
                salesRecords.getItemtype(),
                salesRecords.getSaleschannel(),
                salesRecords.getOrderpriority(),
                salesRecords.getOrderdate(),
                salesRecords.getOrderid(),
                salesRecords.getShipdate(),
                salesRecords.getUnitssold(),
                salesRecords.getUnitprice(),
                salesRecords.getUnitcost(),
                salesRecords.getTotalrevenue(),
                salesRecords.getTotalcost(),
                salesRecords.getTotalprofit()
        };
        return obj;
    }
}
