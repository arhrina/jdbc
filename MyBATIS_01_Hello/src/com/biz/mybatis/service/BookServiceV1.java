package com.biz.mybatis.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.mybatis.config.DBConnection;
import com.biz.mybatis.mapper.BookDao;
import com.biz.mybatis.persistence.BookDTO;

public class BookServiceV1 {
	SqlSession sqlSession;
	Scanner s;
	
	public BookServiceV1() {
		sqlSession = DBConnection.getSqlSessionFactory().openSession(true);
		s = new Scanner(System.in);
	}
	
	public void searchName() {
		while(true) {
			System.out.println("도서검색 v2");
			System.out.print("도서명(Q : quit) >> ");
			String strName = s.nextLine();
			if(strName.equalsIgnoreCase("Q")) break;
			BookDao bDao = sqlSession.getMapper(BookDao.class);
			List<BookDTO> bookList = new ArrayList<BookDTO>();
			BookDTO bookDTO = bDao.findByName(strName);
			System.out.println(bookDTO.toString());

			bookList.add(bookDTO);
			System.out.println(bookList.get(0).toString());
			/*
			 * for(BookDTO b: bookList)
				System.out.println(b.toString());
				*/
		}
	} // end searchName
}
