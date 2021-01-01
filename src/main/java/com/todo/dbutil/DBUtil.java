package com.todo.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	private static String url = "jdbc:mysql://localhost:3306/connectdb?serverTimezone=Asia/Seoul&useSSL=false";
	private static String user = "root";
	private static String pwd = "root";
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}// static initializer

	public static Connection getCon() throws SQLException {
		Connection con = DriverManager.getConnection(url, user, pwd);
		return con;
	}

}
