package com.springoot.mysql.sales;

import com.springoot.mysql.basic.Tutorial;
import com.springoot.mysql.basic.TutorialRepository;
import com.springoot.readdata.CSVDataProcessor;
import com.springoot.readdata.SalesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@SuppressWarnings("AmbiguousMethodReference")
@RequestMapping("/api")
@RestController
public class SalesController {

    @Autowired
    SalesRecordsRepository salesRepository;

    @GetMapping("/salesrecords")
    public ResponseEntity<List<SalesRecordsEntity>> getAllTutorials(@RequestParam(required = false) String title) {

        List<SalesRecordsEntity> salesList = new ArrayList<SalesRecordsEntity>();

        salesRepository.findAll().forEach(salesList::add);

        if (salesList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(salesList, HttpStatus.OK);
    }

    @PostMapping("/salesrecords")
    public ResponseEntity<String> insertRecords() {

        List<SalesRecordsEntity> entityList = CSVDataProcessor.readSalesCSVFile();
        salesRepository.saveAll((entityList));
        return new ResponseEntity<>("Okay", HttpStatus.OK);
    }

    private List<SalesRecordsEntity> getEntity(List<SalesVO> list) {
        List<SalesRecordsEntity> entityList = new ArrayList<>();
        for(SalesVO vo : list) {
            String region= vo.getRegion() ;
            String country = vo.getCountry();
            String itemtype = vo.getItemType();
            String saleschannel = vo.getSalesChannel();
            String orderpriority = vo.getOrderPriority();
            String orderdate = vo.getOrderDate();
            Integer orderid = vo.getOrderID();
            String shipdate = vo.getShipDate();
            Integer unitssold = vo.getUnitsSold();
            Double unitprice = vo.getUnitPrice();
            Double unitcost = vo.getUnitCost() ;
            Double totalrevenue = vo.getTotalRevenue();
            Double totalcost = vo.getTotalCost();
            Double totalprofit = vo.getTotalProfit();
            System.out.println("The value si :"+vo.getCountry());
            //SalesRecordsEntity entity = new SalesRecordsEntity(region, country, itemtype, saleschannel, orderpriority, orderdate, orderid, shipdate, unitssold, unitprice, unitcost, totalrevenue, totalcost, totalprofit);
            SalesRecordsEntity entity = new SalesRecordsEntity();
            entity.setRegion(region);
            entity.setCountry(country);
            entity.setItemtype(itemtype);
            entity.setSaleschannel(saleschannel);
            entity.setOrderpriority(orderpriority);
            entity.setOrderdate(orderdate);
            entity.setOrderid(orderid);
            entity.setShipdate(shipdate);
            entity.setUnitssold(unitssold);
            entity.setUnitprice(unitprice);
            entity.setUnitcost(unitcost);
            entity.setTotalrevenue(totalrevenue);
            entity.setTotalcost(totalcost);
            entity.setTotalprofit(totalprofit);
            entityList.add(entity);
        }

        return entityList;
    }
}
