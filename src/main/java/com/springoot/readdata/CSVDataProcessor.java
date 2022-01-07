package com.springoot.readdata;
        import com.springoot.mysql.sales.SalesRecordsEntity;
        import org.springframework.stereotype.Component;

        import java.io.BufferedReader;
        import java.io.FileNotFoundException;
        import java.io.FileReader;
        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.function.Function;
        import java.util.stream.Collectors;
@Component
public class CSVDataProcessor {

    static String filePath = "/Users/sandeepkumarshukla/Applications/Technical/spring-database/src/main/resources/SalesRecords1000.csv";



    public static List<SalesRecordsEntity> readSalesCSVFile() {
        List<SalesRecordsEntity> inputList = new ArrayList<SalesRecordsEntity>();
        //var filePath = System.getProperty("user.dir") + "/resources/SalesRecord100.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            inputList = br.lines().skip(1).map(mapToSalesItem).collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return inputList;
    }
    private static Function<String, SalesRecordsEntity> mapToSalesItem = (line) -> {
        String[] p = line.split(",");// a CSV has comma separated lines
        SalesRecordsEntity item = new SalesRecordsEntity();
        item.setRegion(p[0]);
        item.setCountry(p[1]);
        item.setItemtype(p[2]);
        item.setSaleschannel(p[3]);
        item.setOrderpriority(p[4]);
        item.setOrderdate(p[5]);
        item.setOrderid(Integer.valueOf(p[6]));
        item.setShipdate(p[7]);
        item.setUnitssold(Integer.valueOf(p[8]));
        item.setUnitprice(Double.valueOf(p[9]));
        item.setUnitcost(Double.valueOf(p[10]));
        item.setTotalrevenue(Double.valueOf(p[11]));
        item.setTotalcost(Double.valueOf(p[12]));
        item.setTotalprofit(Double.valueOf(p[13]));
        return item;
    };


}
