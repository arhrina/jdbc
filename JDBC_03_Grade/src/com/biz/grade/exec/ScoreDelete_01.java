package com.biz.grade.exec;

import java.util.List;
import java.util.Scanner;

import com.biz.grade.persistence.ScoreVO;
import com.biz.grade.service.ScoreServiceV2;
import com.biz.grade.service.extend.ScoreServiceV2Ext;

/*
 * 삭제
 * 1.학생이름을 입력받고 있으면 성적리스트를 보여주고
 * 2.리스트를 보고 id를 입력받아서
 * 3.해당id에 있는 score값 삭제
 */
public class ScoreDelete_01 {
	public static void main(String[] args) {
		ScoreServiceV2 sc = new ScoreServiceV2Ext();
		Scanner s = new Scanner(System.in);
		String strStNum = null;
		List<ScoreVO> scoreList = null;

		// 학번을 입력받아서 학번이 학생테이블에 있는지 조회하고 있으면 다음으로 진행, 없으면 다시 입력
		while (true) {
			System.out.print("학번(Q : QUIT) >> ");
			strStNum = s.nextLine();
			if (strStNum.equalsIgnoreCase("Q")) {
				System.out.println("성적입력 종료");
				break;
			}
			// 학번조회
			scoreList = sc.findByStNum(strStNum);
			if (scoreList == null || scoreList.size() < 1) {
				System.out.println("학생정보에 학번이 없습니다");
				System.out.println("학생정보를 먼저 등록해야 합니다");
				continue;
			}
			break;
		}
		if (strStNum.equals("Q"))
			return;
		
		while(true) {
			System.out.println("ID\t이름\t과목\t점수");
			for (ScoreVO e : scoreList) // 입력된 학번에 대한 학생정보 보이기
			{
				System.out.print(e.getS_id() + "\t");
				System.out.print(e.getSt_name() + "\t");
				System.out.print(e.getSb_name() + "\t");
				System.out.print(e.getS_score() + "\n");
			}
			System.out.print("삭제할 ID(Q : QUIT) >> ");
			String strID = s.nextLine();
			if(strID.equalsIgnoreCase("Q")) {
				System.out.println("종료");
				break;
			}
			
			try {
				long longID = Long.valueOf(strID);
				int ret = sc.delete(longID);
				if(ret > 0) {
					System.out.println("삭제완료");
					scoreList = sc.findByStNum(strStNum);
				}
				else System.out.println("삭제실패");
				continue;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ID는 숫자만 입력");
				continue;
			}
		}
		s.close();
	}
}
