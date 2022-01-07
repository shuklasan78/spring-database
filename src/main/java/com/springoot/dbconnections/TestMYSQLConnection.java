package com.springoot.dbconnections;

import java.sql.*;

public class TestMYSQLConnection {

    public static void createMYSQlCOnnection() {

        String sqlSelectAllPersons = "SELECT * FROM SalesRecords where OrderId=185941302";
        String connectionUrl = "jdbc:mysql://localhost:3306/sandeepmysqldb";

        try (Connection conn = DriverManager.getConnection(connectionUrl, "root", "password");
             PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                long id = rs.getLong("OrderID");
                String country = rs.getString("Country");
                String unitSold = rs.getString("UnitsSold");
                System.out.println("id  " + id + "   :country   " + country + "   unitSold   " + unitSold);
                // do something with the extracted data...
            }
        } catch (SQLException e) {
            // handle the exception
        }
    }
}
