package com.biz.jdbc.exec;

import java.util.List;

import com.biz.jdbc.domain.ScoreVO;
import com.biz.jdbc.service.ScoreJDBCServiceV3;

public class JdbcEx_07 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScoreJDBCServiceV3 sc = new ScoreJDBCServiceV3();
		ScoreVO sVO = ScoreVO.builder().s_id(601).s_std("이몽룡").s_score(100).s_rem("연습").build();
		int ret = sc.insert(sVO);
		System.out.println(ret);
		List<ScoreVO> scoreList;
		scoreList = sc.findByName("이몽룡");
		for(ScoreVO s : scoreList)
			System.out.println(s);
	}

}
