package com.biz.iolist.service.dept;

import java.util.List;
import java.util.Scanner;

import com.biz.iolist.config.DBConnection;
import com.biz.iolist.dao.DeptDao;
import com.biz.iolist.persistence.DeptDTO;

public class DeptServiceV1 {
	protected DeptDao dDao;
	Scanner s;
	public DeptServiceV1() {
		dDao = DBConnection.getSqlSessionFactory().openSession(true).getMapper(DeptDao.class);
		s = new Scanner(System.in);
	}
	
	// dDao.selectAll로 전체리스트 보여주기
	public void viewAllList() {
		List<DeptDTO> deptList = dDao.selectAll();
		if(deptList == null || deptList.size() < 1)
			System.out.println("리스트가 없음");
		else
			viewList(deptList);
	}
	
	// 키보드에서 거래처이름을 검색해서 리스트를 보여주기
	public void viewNameList() {
		System.out.print("검색할 거래처를 입력하세요(-Q : quit) >> ");
		String dName;
		dName = s.nextLine();
		if(dName.equals("-Q")) return;
		List<DeptDTO> dList = dDao.findByName(dName);
		if(dList == null)
			System.out.println("장부에 없는 거래처이름입니다");
		else
			viewList(dList);
	}
	
	// 거래처명과 대표이름을 입력받아 거래처리스트 보이기
	public void viewNameAndCEO() {
		String dName;
		String dCEO;
		List<DeptDTO> dList;
		
		System.out.print("검색할 거래처를 입력하세요(-Q : quit) >> ");
		dName = s.nextLine();
		if(dName.equals("-Q")) return;
		System.out.print("검색할 대표 이름을 입력(-Q : quit) >> ");
		dCEO = s.nextLine();
		if(dCEO.equals("-Q")) return;
		System.out.println("찾기전");
		dList = dDao.findByNameAndCEO(dName, dCEO);
		System.out.println("찾기끝");
		if(dList == null || dList.size() < 1)
			System.out.println("거래처와 대표명을 다시 확인하세요");
		else
			viewList(dList);
	}
	
	protected void viewList(DeptDTO d) { // 각 view에서 List를 출력할 때 사용
		System.out.println(d.toString());
	}
	
	protected void viewList(List<DeptDTO> deptList) { // List로 출력
		for(DeptDTO d : deptList)
			viewList(d);
	}
	
	protected void viewDetail(DeptDTO dDTO) {
		System.out.println("상호 : " + dDTO.getD_name());
		System.out.println("대표 : " + dDTO.getD_ceo());
		System.out.println("전화 : " + dDTO.getD_tel());
		System.out.println("주소 : " + dDTO.getD_addr());
		/*
		System.out.println("업종 : 슈퍼마켓");
		System.out.println("업태 : 도,소매");
		System.out.println("사업자번호 : 409-01-00001");
		System.out.println("담당자 : 홍길동");
		System.out.println("담당자 직통전화 : 010-123-1234");
		*/
	}
}
