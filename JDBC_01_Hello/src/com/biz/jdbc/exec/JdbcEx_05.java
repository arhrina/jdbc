package com.biz.jdbc.exec;

import java.util.List;

import com.biz.jdbc.domain.ScoreVO;
import com.biz.jdbc.service.ScoreJDBCServiceV2;

public class JdbcEx_05 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScoreJDBCServiceV2 sc = new ScoreJDBCServiceV2();
		List<ScoreVO> stdList;
		/*
		stdList = sc.selectAll();
		for(ScoreVO vo : stdList) {
			System.out.println(vo.toString());
		}
		*/
		stdList = sc.findByName("'갈한수' OR 1 = 1"); // SQL Injection 공격. 취약점 공격
		for(ScoreVO vo : stdList) {
			System.out.println(vo.toString());
		}
		
		/*
		stdList = sc.findByName("갈한수");
		for(ScoreVO vo : stdList) {
			System.out.println(vo.toString());
		}*/
	}
}
