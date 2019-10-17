package com.biz.grade.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static Connection dbConn;

	/*
	 * static 생성자
	 * 프로젝트가 시작됨과 동시에 JVM에 의해 자동으로 실행되는 클래스와 무관한 전역(전체에서 접근가능한) 생성자 메소드
	 * 싱글톤으로 만드는것과 동일 
	 */
	static {
		// DB 연결을 해서 dbConn을 생성
		String jdbcDriver = DBContract.DBConn.JdbcDriver;
		String url = DBContract.DBConn.URL;
		String user = DBContract.DBConn.USER;
		String password = DBContract.DBConn.PASSWORD;

		try { // 서버와 연결을 열어서 연결시도를 하는데 네트워크에 문제가 있다면 상당한 지연이 발생
				// dbConnection을 매번 연결하고 닫으면 비용이 발생하므로 프로젝트 하나에 공유해버리기
			Class.forName(jdbcDriver);
			dbConn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getDBConnection() {
		// 프로젝트가 시작하면서 생성된 dbConn을 필요할때 꺼내가는 메소드
		// dbConn 변수를 직접 접근하지 않고 getter를 호출해서 가져간다. Single Tone기법과 유사
		return dbConn;
	}
}
