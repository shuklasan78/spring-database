package com.springoot.mysql.sales;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "SalesRecords")
public class SalesRecordsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String region ;
    private String country;
    private String itemtype;
    private String saleschannel;
    private String orderpriority;
    private String orderdate;
    private Integer orderid;
    private String shipdate;
    private Integer unitssold;
    private Double unitprice;
    private Double unitcost;
    private Double totalrevenue;
    private Double totalcost;
    private Double totalprofit;

    public SalesRecordsEntity() {
    }

    public SalesRecordsEntity(String region, String country, String itemtype, String saleschannel, String orderpriority, String orderdate, Integer orderid, String shipdate, Integer unitssold, Double unitprice, Double unitcost, Double totalrevenue, Double totalcost, Double totalprofit) {
        region = this.region;
        country = this.country;
        itemtype = this.itemtype;
        saleschannel = this.saleschannel;
        orderpriority = this.orderpriority;
        orderdate = this.orderdate;
        orderid = this.orderid;
        shipdate = this.shipdate;
        unitssold = this.unitssold;
        unitprice = this.unitprice;
        unitcost = this.unitcost;
        totalrevenue =  this.totalrevenue;
        totalcost = this.totalcost;
        totalprofit =  this.totalprofit;
    }
}
