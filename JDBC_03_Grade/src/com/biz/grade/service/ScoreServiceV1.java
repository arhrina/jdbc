package com.biz.grade.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.biz.grade.config.DBContract;
import com.biz.grade.persistence.ScoreDTO;
import com.biz.grade.persistence.ScoreVO;

public abstract class ScoreServiceV1 {
	protected Connection dbConn;
	
	protected void dbConnection() {
		// DB 연결을 해서 dbConn을 생성
		String jdbcDriver = DBContract.DBConn.JdbcDriver;
		String url = DBContract.DBConn.URL;
		String user = DBContract.DBConn.USER;
		String password = DBContract.DBConn.PASSWORD;
		
		try { // 서버와 연결을 열어서 연결시도를 하는데 네트워크에 문제가 있다면 상당한 지연이 발생
			// dbConnection을 매번 연결하고 닫으면 비용이 발생하므로 프로젝트 하나에 공유 - > DBConnection클래스
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
	
	public abstract List<ScoreVO> selectAll();
	public abstract ScoreVO findById(long id);
	
	public abstract int insert(ScoreDTO sDTO);
	public abstract int update(ScoreDTO sDTO);
	public abstract int delete(long id);
}
