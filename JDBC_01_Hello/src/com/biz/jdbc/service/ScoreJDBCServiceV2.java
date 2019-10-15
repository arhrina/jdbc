package com.biz.jdbc.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.jdbc.domain.ScoreVO;

public class ScoreJDBCServiceV2 {
	/*
	protected String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
	protected String url = "jdbc:oracle:thin:@localhost:1521:xe";
	protected String userName = "grade";
	protected String password = "grade";
	*/
	protected Connection dbConn = null;
	protected PreparedStatement pStr = null;
	protected List<ScoreVO> scoreList = null;
	
	public List<ScoreVO> getScoreList() {
		return scoreList;
	}
	
	protected void dbConnection() {
		try {
			Class.forName(DBConstract.DB_INFO.JdbcDriver);
			dbConn = DriverManager.getConnection(DBConstract.DB_INFO.URL, DBConstract.DB_INFO.USER, DBConstract.DB_INFO.PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<ScoreVO> selectAll() {
		String sql = " SELECT * FROM tbl_score ";
		select(sql);
		return scoreList;
	}
	
	public List<ScoreVO> findById(int s_id) {
		String sql = " SELECT * FROM tbl_score ";
		sql += " WHERE s_id = " + s_id; // 여러줄에 걸쳐 분리된 SQL문을 붙일 때 오류를 막기 위해 앞뒤에 공백
		select(sql);
		return scoreList;
	}
	
	public List<ScoreVO> findByName(String name) {
		String sql = " SELECT * FROM tbl_score ";
		sql += " WHERE s_std = " + name + "";
		select(sql);
		return scoreList;
	}
	
	protected void select(String sql) {
		dbConnection(); // db연결
		scoreList = new ArrayList<ScoreVO>();
		// String sql = " SELECT * FROM tbl_score ";
		try {
			pStr = dbConn.prepareStatement(sql);
			ResultSet rst = pStr.executeQuery();
			while(rst.next()) {
				ScoreVO vo = ScoreVO.builder().
						s_id(rst.getInt(DBConstract.SCORE.S_ID)).
						s_std(rst.getString(DBConstract.SCORE.S_STD)).
						s_score(rst.getInt(DBConstract.SCORE.S_SCORE)).
						s_rem(rst.getString(DBConstract.SCORE.S_REM)).
						build();
				/*
				vo.setS_id(rst.getInt(DBConstract.SCORE.S_ID));
				vo.setS_std(rst.getString(DBConstract.SCORE.S_STD));
				vo.setS_score(rst.getInt(DBConstract.SCORE.S_SCORE));
				vo.setS_rem(rst.getString(DBConstract.SCORE.S_REM));
				*/
				scoreList.add(vo);
			}
			pStr.close();
			dbConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
