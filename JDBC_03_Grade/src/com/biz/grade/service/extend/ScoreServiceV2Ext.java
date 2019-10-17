package com.biz.grade.service.extend;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.grade.config.DBContract;
import com.biz.grade.persistence.ScoreDTO;
import com.biz.grade.persistence.ScoreVO;
import com.biz.grade.service.ScoreServiceV2;

public class ScoreServiceV2Ext extends ScoreServiceV2 {

	@Override
	public List<ScoreVO> selectAll() {
		// TODO Auto-generated method stub
		PreparedStatement pStr;
		String sql = DBContract.SQL.SELECT_VIEW_SCORE;
		try {
			pStr = dbConn.prepareStatement(sql);
			ResultSet rst = pStr.executeQuery();
			List<ScoreVO> scoreList = new ArrayList<ScoreVO>();
			while(rst.next()) {
				scoreList.add(rstTOScoreVO(rst));
			}
			rst.close();
			pStr.close();
			return scoreList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	private ScoreVO rstTOScoreVO(ResultSet rst) throws SQLException {
		ScoreVO scoreVO = ScoreVO.builder().
		s_id(rst.getLong("S_ID")).
		s_score(rst.getShort("S_SCORE")).
		s_std(rst.getString("S_STD")).
		s_subject(rst.getString("S_SUBJECT")).
		sb_name(rst.getString("SB_NAME")).
		st_dept(rst.getString("ST_DEPT")).
		st_grade(rst.getShort("ST_GRADE")).
		st_name(rst.getString("ST_NAME")).
		d_name(rst.getString("D_NAME")).
		d_tel(rst.getString("D_TEL")).
		build();
		return scoreVO;
	}
	@Override
	public ScoreVO findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(ScoreDTO sDTO) {
		// TODO Auto-generated method stub
		PreparedStatement pStr;
		String sql = " INSERT INTO tbl_score (S_ID, " +
				" S_STD, " + // 학번 
				" S_SUBJECT, " + // 과목코드
				" S_SCORE, " + // 점수
				" S_REM) " + // 비고
				" VALUES(?, ?, ?, ?, ?) ";
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setLong(1, sDTO.getS_id());
			pStr.setString(2, sDTO.getS_std());
			pStr.setString(3, sDTO.getS_subject());
			pStr.setInt(4, sDTO.getS_score());
			pStr.setString(5, sDTO.getS_rem());
			
			int ret = pStr.executeUpdate();
			// CURD 중 CUD는 executeUpdate를 사용하고 결과값으로 정수값을 리턴한다
			// 정상적으로 수행되면 ret는 0보다 큰 값을 가진다
			pStr.close();
			return ret;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(ScoreDTO sDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		PreparedStatement pStr = null;
		String sql = " DELETE FROM tbl_score ";
		sql += " WHERE s_id = ? ";
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setLong(1, id);
			int ret = pStr.executeUpdate();
			pStr.close();
			return ret;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	//stName 변수를 매개변수로 학생이름을 검색한 후 List return
	
	@Override
	public List<ScoreVO> findBySTName(String stName) {
		// TODO Auto-generated method stub
		PreparedStatement pStr;
		String sql = DBContract.SQL.SELECT_VIEW_SCORE;
		sql += " WHERE ST_NAME = ? ";
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, stName); // 첫번째 물음표(치환문자)에 stName 매개변수에 받아온 전달인자 삽입
			ResultSet rst = pStr.executeQuery();
			List<ScoreVO> scoreList = new ArrayList<ScoreVO>();
			while(rst.next()) {
				scoreList.add(rstTOScoreVO(rst));
			}
			rst.close();
			pStr.close();
			return scoreList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override // 학번을 매개변수로 받아서 성적리스트 리턴
	public List<ScoreVO> findByStNum(String strStNum) {
		// TODO Auto-generated method stub
		PreparedStatement pStr;
		String sql = DBContract.SQL.SELECT_VIEW_SCORE;
		sql += " WHERE s_std = ? ";
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, strStNum);
			ResultSet rst = pStr.executeQuery();
			List<ScoreVO> scList = new ArrayList<ScoreVO>();
			while(rst.next()) {
				scList.add(rstTOScoreVO(rst));
			}
			rst.close();
			pStr.close();
			return scList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	@Override
	public List<ScoreVO> findBySubject(String strSbNum) {
		// TODO Auto-generated method stub
		PreparedStatement pStr;
		String sql = DBContract.SQL.SELECT_VIEW_SCORE;
		sql += " WHERE s_subject = ? ";
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, strSbNum);
			ResultSet rst = pStr.executeQuery();
			List<ScoreVO> scList = new ArrayList<ScoreVO>();
			while(rst.next()) {
				scList.add(rstTOScoreVO(rst));
			}
			rst.close();
			pStr.close();
			return scList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
