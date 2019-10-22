package com.biz.oracle.exec;

import com.biz.oracle.service.BookCUDServiceV1;
import com.biz.oracle.service.BookServiceV1;

/*
 * 
 */
public class BookUpdateEx_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// SELECT를 위한 서비스 클래스
		BookServiceV1 bs = new BookServiceV1();
		
		// INSERT, UPDATE, DELETE를 위한 서비스 클래스
		BookCUDServiceV1 bC = new BookCUDServiceV1();
		
		// bs에서 도서명을 입력했을 때 List를 보여주는 메소드 호출
		String strName = bs.searchBookName();
		if(strName.equalsIgnoreCase("Q"))
			System.out.println("도서정보 변경 업무 종료");
		else {
			// 도서리스트를 보고 도서코드를 입력받아 도서정보를 수정
			bC.updateBook();
			bs.searchBookName(strName);
		}
	}
}