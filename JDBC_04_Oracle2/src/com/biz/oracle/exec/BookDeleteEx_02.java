package com.biz.oracle.exec;

import com.biz.oracle.service.BookCUDServiceV1;
import com.biz.oracle.service.BookServiceV1;

public class BookDeleteEx_02 {
/* 
 * 도서명을 입력받아서 도서명을 검색한 리스트를 보여주고 도서코드를 입력받아서 해당 도서를 삭제하고
 * 결과를 리스트로 보여주기 
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BookServiceV1 bs = new BookServiceV1();
		BookCUDServiceV1 bC = new BookCUDServiceV1();
		bs.searchBookName();
		bC.deleteBook();
		bs.viewBookList();
	}
}
