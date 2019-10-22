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

	public void updateBook() {
		// TODO Auto-generated method stub
		System.out.println("도서정보 수정");
		System.out.print("수정할 도서코드(Q : QUIT) >> ");
		String strCode = s.nextLine();
		if(strCode.equalsIgnoreCase("Q")); // 입력끝내기
		
		// 입력받은 코드에 해당하는 도서정보를 가져오기
		BookDTO bDTO = bookDao.findById(strCode);
		// bookDao.update(bDTO);
		
		// 각 항목별로 값을 수정. PK는 수정하지 않는 것이 좋다. PK를 수정하면 무결성이 훼손될 수 있다
		/*
		 * 보통 PK를 수정하는 것은 기존의 데이터를 삭제하고 새로운 데이터를 입력하거나
		 * 기존 데이터를 그대로 유지하고 새로운 데이터를 입력하는 경우
		 */
		System.out.printf("변경할 도서명(%s) >> ", bDTO.getB_name());
		String strName = s.nextLine();
		if(strName.trim().length() > 0) { // 공백이 입력되면 문제가 있을 수 있으므로 trim을 추가. 객체의 chaining
			bDTO.setB_name(strName);
		}
		// 새로운 도서명을 입력했을 때는 bookDTO의 필드를 새로운 도서명으로 대치하고
		// 그냥 엔터만 입력했을 때는 변경하지 않는다
		
		System.out.printf("변경할 출판사(%s) >> ", bDTO.getB_comp());
		String strComp = s.nextLine();
		if(strComp.trim().length() > 0) {
			bDTO.setB_comp(strComp);
		}
		
		System.out.printf("변경할 저자(%s) >> ", bDTO.getB_writer());
		String strWriter = s.nextLine();
		if(strWriter.trim().length() > 0) {
			bDTO.setB_writer(strWriter);
		}
		
		System.out.printf("변경할 가격(%s) >> ", bDTO.getB_price());
		String strPrice = s.nextLine();
		int intPrice = 0;
		try {
			// 가격을 숫자로 변환하는 코드에서 값을 입력하지 않고 엔터를 누르면
			// NumberFormatException이 발생하는 것을 역이용하여 가격을 입력하면 해당값을 저장하고 아니면 패스
			intPrice = Integer.valueOf(strPrice);
			bDTO.setB_price(intPrice);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		// 여기에 도착하면 bDTO에 담긴 값은 변경된 값들과 원래 있던 값들이 남아있는 인스턴스이다
		bookDao.update(bDTO);
		int ret = bookDao.update(bDTO);
		if(ret > 0) {
			System.out.println("도서정보 변경");
		}
		else {
			System.out.println("도서정보 변경실패");
		}

	}
}
