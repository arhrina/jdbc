package com.biz.grade.exec;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.biz.grade.persistence.ScoreDTO;
import com.biz.grade.persistence.ScoreVO;
import com.biz.grade.service.ScoreServiceV2;
import com.biz.grade.service.extend.ScoreServiceV2Ext;

public class ScoreInput_01 {
/*
 * 학생 성적 데이터 입력
 * 
 * 
 * 
 * 1.학번을 입력
 * 2.학번이 학생테이블에 있으면 성적 입력으로 진행, 학번이 없으면 학번을 다시 입력
 * 3.과목코드를 입력받고 과목코드가 과목테이블에 있으면 점수입력으로 진행, 없으면 과목코드 다시 입력
 * 4.점수를 입력받고 점수가 0~100 범위 내에 있으면 DB에 추가, 범위밖이면 다시입력
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 중첩되는 반복, 조건문은 최대한 절차들을 벗겨내서 제어권을 차례로 넘기게 설계하는 것이 좋다
		ScoreServiceV2 sc = new ScoreServiceV2Ext();
		Scanner s = new Scanner(System.in);
		String strStNum = null;
		String strSbNum = null;
		String strScore = null;
		
		// 학번을 입력받아서 학번이 학생테이블에 있는지 조회하고 있으면 다음으로 진행
		while(true) {
			System.out.print("학번(Q : QUIT) >> ");
			strStNum = s.nextLine();
			if(strStNum.equalsIgnoreCase("Q")) {
				System.out.println("성적입력 종료");
				break;
			}
			// 학번조회
			List<ScoreVO> scoreList = sc.findByStNum(strStNum);
			if(scoreList == null || scoreList.size() < 1) {
				System.out.println("학생정보에 학번이 없습니다");
				System.out.println("학생정보를 먼저 등록해야 합니다");
				continue;
			}
			for(ScoreVO e : scoreList) // 입력된 학번에 대한 학생정보 보이기 
				System.out.println(e.toString());
			break;
		}
		if(strStNum.equals("Q")) return; // Q를 입력받아서 나왔으면 다음 while문으로 들어가지말고 메인 종료
		// 과목코드를 입력받고 처리
		while(true) {
			System.out.print("과목코드(Q : QUIT) >> ");
			strSbNum = s.nextLine();
			if(strSbNum.equalsIgnoreCase("Q")) {
				System.out.println("성적입력 종료");
				break;
			}
			List<ScoreVO> scList = sc.findBySubject(strSbNum);
			if(scList == null || scList.size() < 1) {
				System.out.println("없는 과목코드입니다");
				System.out.println("확인하고 다시 입력해주세요");
				continue;
			}
			break;
		}
		if(strSbNum.equals("Q")) return; // Q를 입력받아서 나왔으면 다음 while문으로 들어가지말고 메인 종료
		
		// 점수를 입력받고 처리
		while(true) {
			System.out.print("점수(Q : QUIT) >> ");
			strScore = s.nextLine();
			if(strScore.equalsIgnoreCase("Q")) {
				System.out.println("성적입력 종료");
				break;
			}
			try {
				int intScore = Integer.valueOf(strScore);
				if(intScore < 0 || intScore > 100) {
					System.out.println("점수는 0~100 사이의 숫자를 입력해야합니다");
					continue;
				}
				// Integer.valueOf(strScore);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("성적은 숫자로만 입력");
				continue;
			}
			break;
		}
		if(strScore.equalsIgnoreCase("Q")) return;
		
		Random rand = new Random();
		ScoreDTO sDTO = ScoreDTO.builder().
				s_id(rand.nextLong())
				.s_std(strStNum)
				.s_subject(strSbNum)
				.s_score(Integer.valueOf(strScore))
				.build();
		int ret = sc.insert(sDTO);
		if(ret > 0) System.out.println("데이터 추가 성공");
		else System.out.println("데이터 추가 실패");
		
		s.close();
	}
}
