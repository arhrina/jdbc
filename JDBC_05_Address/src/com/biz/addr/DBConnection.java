package com.biz.addr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static Connection dbConn;
	
	static { // 전역으로 JVM 실행시 램에 올라간다
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			dbConn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "user4", "user4");
			System.out.println("DB 연결");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("연결실패");
			e.printStackTrace();
		}
	}
	
	public static Connection getDBConnection() { // 접속을 가져오는 메소드
		return dbConn;
	}
}
