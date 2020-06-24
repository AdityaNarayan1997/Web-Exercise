package com.jspiders.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Driver;

public class DB {
	public static Connection getCon() {
		Connection con = null;
		try {
			Driver driveref = new Driver();
			DriverManager.registerDriver(driveref);
			String dburl = "jdbc:mysql://localhost:3306/web-excercise?user=root&password=root";
			con = DriverManager.getConnection(dburl);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
