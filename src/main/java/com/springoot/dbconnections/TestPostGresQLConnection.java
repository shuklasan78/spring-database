package com.springoot.dbconnections;

import java.sql.*;
import java.util.Properties;

public class TestPostGresQLConnection {

    public static void connectToPostGresWithProperyFile() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/sandeeppostgres";
        Properties props = new Properties();
        props.setProperty("user","sandeep");
        props.setProperty("password","password");
        props.setProperty("ssl","false");
        Connection conn = DriverManager.getConnection(url, props);
        var sql = "select * from sales_records";
        Connection connection = DriverManager.getConnection(url,props);
        System.out.println("Connected to PostgreSQL database!");
        Statement statement = connection.createStatement();
        System.out.println("Reading car records...");
        System.out.printf("Is Conection good ? :"+connection.isClosed());
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            System.out.printf("%-30.30s  %-30.30s%n", resultSet.getString("Region"), resultSet.getString("Country"));
        }

    }

    public static void connectToPostGresWithTryCatch() {

        var sql = "select * from sales_records";
        String connectionUrl = "jdbc:postgresql://localhost:5432/sandeeppostgres";
        try (Connection conn = DriverManager.getConnection(connectionUrl, "sandeep", "password");
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                long id = rs.getLong("OrderID");
                String country = rs.getString("Country");
                String unitSold = rs.getString("UnitsSold");
                System.out.println("id  "+id +"   :country   " +country +"   unitSold   "+unitSold);
                // do something with the extracted data...
            }
        } catch (SQLException e) {
            // handle the exception
        }
    }
}
