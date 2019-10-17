package com.biz.grade.exec;

import java.util.List;

import com.biz.grade.persistence.ScoreVO;
import com.biz.grade.service.ScoreServiceV2;
import com.biz.grade.service.extend.ScoreServiceV2Ext;

public class ScoreEx_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScoreServiceV2 sc = new ScoreServiceV2Ext();
		List<ScoreVO> scoreList;
		scoreList = sc.selectAll();
		if(scoreList == null || scoreList.size() < 1) {
			System.out.println("데이터가 없습니다");
			// 메인에서 return을 실행하면 프로젝트 종료
			return;
		}
		for(ScoreVO v : scoreList)
			System.out.println(v.toString());
	}

}
