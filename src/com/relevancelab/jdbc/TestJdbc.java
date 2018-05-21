package com.relevancelab.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://localhost:3306/hb-demo?useSSL=false";
		String user = "hbuser";	
		String pass = "hbuser";
		
		try {
		    Class.forName("com.mysql.cj.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} 

		
		try {
			System.out.println("Connecting to database : "+jdbcUrl);
			
			Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
			
			System.out.println("Connection successful!!!");
			
		} catch (Exception exc) {
			exc.printStackTrace();
		}

	}

}
