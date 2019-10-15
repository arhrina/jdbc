package com.biz.jdbc.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.jdbc.domain.ScoreVO;

public class ScoreJDBCServiceV3 {
	protected Connection dbConn = null;
	protected PreparedStatement pStr = null;
	protected List<ScoreVO> scoreList = null;

	public List<ScoreVO> getScoreList() {
		return scoreList;
	}

	protected void dbConnection() {
		try {
			Class.forName(DBConstract.DB_INFO.JdbcDriver);
			dbConn = DriverManager.getConnection(DBConstract.DB_INFO.URL, DBConstract.DB_INFO.USER,
					DBConstract.DB_INFO.PASSWORD);
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
		dbConnection();
		try {
			pStr = dbConn.prepareStatement(sql);
			setScoreList(pStr);
			dbConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return scoreList;
	}

	public List<ScoreVO> findById(int s_id) {
		String sql = " SELECT * FROM tbl_score ";
		sql += " WHERE s_id = ? "; // 물음표는 대체문자열
		dbConnection();
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setInt(1, s_id);
			// 쿼리문에 있는 1번째 물음표에 s_id를 넣는다
			setScoreList(pStr);
			dbConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return scoreList;
	}
	
	public List<ScoreVO> findById(int s_id, int e_id) { // 2개의 id를 입력받아서 범위검색
		String sql = " SELECT * FROM tbl_score ";
		sql += " WHERE s_id BETWEEN ? AND ? ";
		dbConnection();
		try {
			pStr = dbConn.prepareStatement(sql); // Statement는 SQL Injection공격에 취약점이 있어 쓰지않음
			pStr.setInt(1, s_id);
			// prepareStatement의 전달인자에 있는 1번째 물음표에 s_id를 넣는다
			pStr.setInt(2, e_id);
			// prepareStatement의 전달인자에 있는 2번째 물음표에 e_id를 넣는다
			setScoreList(pStr);
			dbConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return scoreList;
	}

	public List<ScoreVO> findByName(String name) {
		String sql = " SELECT * FROM tbl_score ";
		sql += " WHERE s_std = ? ";
		dbConnection();
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, name);
			setScoreList(pStr);
			dbConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return scoreList;
	}

	protected void setScoreList(PreparedStatement pStr) throws SQLException {
		// ResultSet으로 데이터를 빼내서 리스트로 만듦
		scoreList = new ArrayList<ScoreVO>();
		ResultSet rst = pStr.executeQuery();
		while (rst.next()) {
			ScoreVO vo = ScoreVO.builder().s_id(rst.getInt(DBConstract.SCORE.S_ID))
					.s_std(rst.getString(DBConstract.SCORE.S_STD)).s_score(rst.getInt(DBConstract.SCORE.S_SCORE))
					.s_rem(rst.getString(DBConstract.SCORE.S_REM)).build();
			scoreList.add(vo);
		}
		rst.close();
	}
	
	public int insert(ScoreVO sVO) {
		// INSERT를 수행하는 SQL 문자열
		String sql = " INSERT INTO tbl_score ( ";
		sql += " s_id, ";
		sql += " s_std, ";
		sql += " s_subject, ";
		sql += " s_score, ";
		sql += " s_rem ) ";
		sql += " VALUES(?, ?, 001, ?, ?) "; // VALUES 값은 ? 대치문자로
		dbConnection();
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setLong(1, sVO.getS_id());
			pStr.setString(2, sVO.getS_std());
			pStr.setInt(3, sVO.getS_score());
			pStr.setString(4, sVO.getS_rem());
			// 대치문자 위치 1 2 3 4번째에 각 요소를 get해서 setting
			int ret = pStr.executeUpdate(); // 업데이트 메소드 실행
			return ret;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
