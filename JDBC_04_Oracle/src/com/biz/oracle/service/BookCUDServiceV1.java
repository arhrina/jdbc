package com.biz.oracle.service;

import java.util.Scanner;

import com.biz.oracle.persistence.BookDTO;
import com.biz.oracle.persistence.dao.BookDao;
import com.biz.oracle.persistence.dao.BookDaoImp;

public class BookCUDServiceV1 {
	private BookDao bookDao;
	private Scanner s;

	public BookCUDServiceV1() {
		s = new Scanner(System.in);
		bookDao = new BookDaoImp();
	}

	public void inputBook() {
		while (true) {
			String strB_name;
			while (true) {
				try {
					System.out.print("1.도서명(Q : Quit) >> ");
					strB_name = s.nextLine();
					if (strB_name.equalsIgnoreCase("Q"))
						break;
					if(strB_name.isEmpty()) {
						System.out.println("도서명은 반드시 입력해야합니다");
						continue;
					}
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("가격은 숫자로만 입력");
					continue;
				}
				break;
			}
			if (strB_name.equalsIgnoreCase("Q"))
				break;
			
			System.out.print("2.출판사(Q : Quit) >> ");
			String strB_comp = s.nextLine();
			if (strB_comp.equalsIgnoreCase("Q"))
				break;
			System.out.print("3.저자(Q : Quit) >> ");
			String strB_writer = s.nextLine();
			if (strB_writer.equalsIgnoreCase("Q"))
				break;
			
			String strB_price;
			int intB_price = 0;
			while (true) {
				try {
					System.out.print("4.가격(Q : Quit) >> ");
					strB_price = s.nextLine();
					if (strB_price.equalsIgnoreCase("Q"))
						break;
					intB_price = Integer.valueOf(strB_price);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("가격은 숫자로만 입력");
					continue;
				}
				break;
			}
			if (strB_price.equalsIgnoreCase("Q"))
				break;
			
			BookDTO bookDTO = BookDTO.builder().b_name(strB_name).b_comp(strB_comp).b_writer(strB_writer).b_price(intB_price).build();
			
			int ret = bookDao.insert(bookDTO);
			if(ret > 0)
				System.out.println("도서정보 입력완료");
			else
				System.out.println("도서정보 입력실패");
		}
	}

	public void deleteBook() {
		// TODO Auto-generated method stub
		while(true) {
			System.out.print("삭제할 코드(Q : quit) >> ");
			String strCode = s.nextLine();
			if(strCode.equalsIgnoreCase("Q")) break;
			BookDTO bDTO = bookDao.findById(strCode);
			if(bDTO == null) {
				System.out.println("도서 코드가 없습니다");
				continue;
			}
			bookDao.delete(strCode);				
		}
	}
}
