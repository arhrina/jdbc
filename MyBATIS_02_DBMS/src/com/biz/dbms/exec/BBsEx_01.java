package com.biz.dbms.exec;

import com.biz.dbms.service.BBsServiceV1;

public class BBsEx_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BBsServiceV1 bbs = new BBsServiceV1();
		bbs.writeBBS();	// 키보드를 사용한 게시판 글쓰기
		bbs.viewBBsList(); // 게시판 보기
	}
}
