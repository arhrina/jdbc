package com.biz.grade.exec;

import java.util.List;
import java.util.Scanner;

import com.biz.grade.persistence.ScoreVO;
import com.biz.grade.service.ScoreServiceV2;
import com.biz.grade.service.extend.ScoreServiceV2Ext;

public class ScoreEx_02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScoreServiceV2 sc = new ScoreServiceV2Ext();
		Scanner s = new Scanner(System.in);
		
		while(true) {
			System.out.println("이름으로 성적표 검색");
			System.out.print("이름(-0 : quit) >> ");
			String strName = s.nextLine();
			if(strName.equalsIgnoreCase("-Q")) break;
			List<ScoreVO> scoreList;
			scoreList = sc.findBySTName(strName);
			if(scoreList == null || scoreList.size() < 1)
			{
				System.out.println("데이터가 없음");
				System.out.println("학생 이름을 다시 입력");
				continue; // while의 시작으로
			}
			for(ScoreVO w : scoreList)
				System.out.println(w.toString());
		}
		System.out.println("System Terminate. Good Bye");
		s.close();
	}
}
