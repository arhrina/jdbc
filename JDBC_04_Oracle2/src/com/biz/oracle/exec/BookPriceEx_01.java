package com.biz.oracle.exec;

import com.biz.oracle.service.BookServiceV1;

public class BookPriceEx_01 {

	/*
	 * 두 개의 숫자(도서금액)을 입력받고 범위 내에 있는 도서 목록을 보이기
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BookServiceV1 bs = new BookServiceV1();
		bs.searchBookPrice();
	}

}
