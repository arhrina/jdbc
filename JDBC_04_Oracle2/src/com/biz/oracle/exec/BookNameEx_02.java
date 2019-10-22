package com.biz.oracle.exec;

import com.biz.oracle.service.BookServiceV1;

public class BookNameEx_02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BookServiceV1 bs = new BookServiceV1();
		bs.searchBookName(true); // searchBookName에 true를 전달인자로 전달하면 반복하면서 도서이름검색
		// 파라미터를 전달하지 않으면 1번만 검색
	}

}
