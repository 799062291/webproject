package org.lanqiao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	static Connection conn = null;
	static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	static final String USER = "scott";
	static final String PASSWORD = "tiger";
	public static Connection getConnection(){
    	try {
			Class.forName(DRIVER);
			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return conn;
    }
}
