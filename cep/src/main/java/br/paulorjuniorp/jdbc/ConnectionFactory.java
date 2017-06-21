package br.paulorjuniorp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public static Connection getConnection(){
		 try {
			 DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			 return DriverManager.getConnection(
					   "jdbc:mysql://localhost/cep_db", "root", "");
					
		} catch (SQLException e) {
		   throw new RuntimeException(e);
		 }

	 }
	 
}
