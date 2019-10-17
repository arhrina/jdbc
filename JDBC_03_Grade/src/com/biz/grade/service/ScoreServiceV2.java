package com.biz.grade.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.biz.grade.config.DBConnection;
import com.biz.grade.config.DBContract;
import com.biz.grade.persistence.ScoreDTO;
import com.biz.grade.persistence.ScoreVO;

public abstract class ScoreServiceV2 {
	protected Connection dbConn;
	
	public ScoreServiceV2() {
		// TODO Auto-generated constructor stub
		dbConn = DBConnection.getDBConnection(); 
	}
	
	public abstract List<ScoreVO> selectAll();
	public abstract ScoreVO findById(long id);
	public abstract List<ScoreVO> findBySTName(String stName); // 이름으로 검색
	
	public abstract int insert(ScoreDTO sDTO);
	public abstract int update(ScoreDTO sDTO);
	public abstract int delete(long id);

	public abstract List<ScoreVO> findByStNum(String strStNum);

	public abstract List<ScoreVO> findBySubject(String strSbNum);
}
