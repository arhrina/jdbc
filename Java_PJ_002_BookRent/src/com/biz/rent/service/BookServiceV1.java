package com.biz.rent.service;

import java.util.List;
import java.util.Scanner;

import com.biz.rent.config.DBConnection;
import com.biz.rent.dao.BookDao;
import com.biz.rent.persistence.BookDTO;

public class BookServiceV1 {
	protected BookDao bDao;
	protected BookDTO bDTO;
	protected Scanner s;

	public BookServiceV1() {
		bDao = DBConnection.getSqlSessionFactory().openSession(true).getMapper(BookDao.class);
		s = new Scanner(System.in);
	}

	public void viewAllBook() {
		List<BookDTO> bookList = bDao.selectAll();
		for (BookDTO t : bookList)
			System.out.println(t.toString());
	}

	public void insertBook() {
		viewAllBook();
		System.out.println("도서정보를 등록합니다");
		System.out.println("도서코드는 자동으로 생성됩니다");
		String str = bDao.getMaxBCode();
		int intP = Integer.valueOf(str.substring(2));
		intP++;
		String strP;
		strP = str.substring(0, 2);
		str = strP + String.format("%04d", intP);
		System.out.printf("등록될 도서코드는 %s입니다\n", str);
		String strBN; // 책 제목
		String strAu; // 저자
		String strComp; // 출판사
		int bYear = 0; // 구입연도
		int bPrice = 0; // 구입가격
		int rPrice = 0; // 렌탈가격

		while (true) {
			System.out.print("책 제목(-Q : quit) >> ");
			strBN = s.nextLine();
			if (strBN.trim().isEmpty()) {
				System.out.println("책 제목은 반드시 입력되어야합니다");
				continue;
			}
			if (strBN.equals("-Q"))
				break;
			bDTO = bDao.findBySubject(strBN);
			if (bDTO.getB_name().trim().equals(strBN.trim())) {
				System.out.println("이미 있는 책 제목입니다. 다시 입력하세요");
				continue;
			}
			break;
		}
		if (strBN.equals("-Q"))
			return;
		// end 도서이름 입력

		while (true) {
			System.out.print("글쓴이(-Q : quit) >> ");
			strAu = s.nextLine();
			if (strAu.trim().isEmpty()) {
				System.out.println("저자는 반드시 입력되어야합니다");
				continue;
			}
			if (strAu.equals("-Q"))
				break;
			break;
		}
		if (strAu.equals("-Q"))
			return;
		// end 저자 입력

		System.out.print("출판사(-Q : quit) >> ");
		strComp = s.nextLine();
		if (strComp.equals("-Q"))
			return;

		String strr;
		while (true) {
			System.out.print("구입연도(-Q : quit) >> ");
			strr = s.nextLine();
			if (strr.equals("-Q"))
				break; // -Q 입력시 끝내기 
			if (strr.trim().isEmpty()) {
				System.out.println("구입연도는 반드시 입력되어야합니다");
				continue;
			}
			try {
				bYear = Integer.valueOf(strr);
				break; // 구입연도가 입력됐고 숫자로 입력됐으면 while 종료. 구입연도는 bYear에 저장되어있음
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("연도는 숫자로만 입력하세요");
				continue;
			}
		}
		if (strr.equals("-Q"))
			return;
		// end 구입연도
		
		strr = null;
		while (true) {
			System.out.print("구입가격(-Q : quit) >> ");
			strr = s.nextLine();
			if (strr.equals("-Q"))
				break;
			try {
				bPrice = Integer.valueOf(strr);
				break;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("숫자로만 입력하세요");
				continue;
			}
		}
		if(strr.equals("-Q")) return;
		// end 구입가격 입력
		
		strr = null;
		while(true) {
			System.out.print("대여가격(-Q : quit) >> ");
			strr = s.nextLine();
			if(strr.equals("-Q")) break;
			if(strr.trim().isEmpty()) {
				System.out.println("대여가격은 반드시 입력되어야합니다");
				continue;
			}
			try {
				rPrice = Integer.valueOf(strr);
				break;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("대여가격은 숫자로만 입력되어야합니다");
				continue;
			}
		}
		if(strr.equals("-Q")) return;
		// end 렌탈가격 입력
		
		// 입력된 가격들 DTO에 세팅
		bDTO.setB_code(str);
		bDTO.setB_comp(strComp);
		bDTO.setB_auther(strAu);
		bDTO.setB_name(strBN);
		bDTO.setB_iprice(bPrice);
		bDTO.setB_rprice(rPrice);
		bDTO.setB_year(bYear);
		
		int ret = bDao.insert(bDTO); // 세팅된 DTO로 insert 수행
		if(ret > 0)
			System.out.println("입력 완료");
		else
			System.out.println("입력 실패");
	} // end insert
	
	public void updateBook() {
		
	} // end update
	
	public void searchSubAndAuth() {
		System.out.println("검색할 책 제목과 저자를 입력하세요");
		String subject;
		System.out.print("책 제목 >> ");
		subject = s.nextLine();
		String writer;
		System.out.print("저자 >> ");
		writer = s.nextLine();
		List<BookDTO> bookList = bDao.findBySubAndAuth(subject, writer);
		if(bookList.size() < 1 || bookList == null) {
			System.out.println("검색결과가 없습니다");
			return;
		}
		for(BookDTO d : bookList)
			System.out.println(d.toString());
	} // end 제목저자검색
}