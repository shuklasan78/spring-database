package com.springoot;

import com.springoot.dbconnections.TestMYSQLConnection;
import com.springoot.dbconnections.TestPostGresQLConnection;
import com.springoot.mysql.sales.SalesRecordsEntity;
import com.springoot.readdata.CSVDataProcessor;
import com.springoot.readdata.SalesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
public class SpringMysqJpaApplication {
	//https://www.bezkoder.com/spring-boot-jpa-crud-rest-api/

	public static void main(String[] args) {

		SpringApplication.run(SpringMysqJpaApplication.class, args);
		testConnections();

	}

	private static void testConnections() {
		TestMYSQLConnection.createMYSQlCOnnection();
		try {
			TestPostGresQLConnection.connectToPostGresWithProperyFile();
			TestPostGresQLConnection.connectToPostGresWithProperyFile();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
