package com.biz.oracle.service;

import java.util.List;
import java.util.Scanner;

import com.biz.oracle.persistence.BookDTO;
import com.biz.oracle.persistence.dao.BookDao;
import com.biz.oracle.persistence.dao.BookDaoImp;

public class BookServiceV2 {
	BookDao bookDao;
	Scanner s;

	public BookServiceV2() {
		// 클래스 생성자. 객체를 사용할 수 있게 초기화
		s = new Scanner(System.in);
		bookDao = new BookDaoImp();
		// 초기화된 클래스 = 인스턴스
	}

	public void viewBookList() { // 도서정보 전체리스트를 DB로부터 읽어서 console에 출력
		List<BookDTO> bookList = bookDao.selectAll(); // 전체리스트를 DB로부터 읽어서 저장
		for (BookDTO w : bookList)
			System.out.println(w.toString());
	}

	public void searchBookName() { // 키보드에서 도서 입력받아서 리스트를 콘솔에 보이기
		
		while(true) {
			System.out.print("도서검색(Q : QUIT) >> ");
			String strName = s.nextLine();
			if (strName.equalsIgnoreCase("Q"))
				break;
				// return true;
			List<BookDTO> bookList = bookDao.findByName(strName);
			if(bookList == null || bookList.size() < 1)
				System.out.println("찾는 도서가 없음");
			for (BookDTO dto : bookList)
				System.out.println(dto.toString());
		}
	}
	
	public void searchBookName(boolean bConti) { // 키보드에서 도서 입력받아서 리스트를 콘솔에 보이기
		while (true) {
		}
	}

	private void viewList(List<BookDTO> bookList) {
		bookList = bookDao.selectAll(); // 전체리스트를 DB로부터 읽어서 저장
		for (BookDTO w : bookList)
			System.out.println(w.toString());
	}

	public void searchBookPrice() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				System.out.print("시작 가격검색(Q : QUIT) >> ");
				String strSprice = s.nextLine();
				if (strSprice.equalsIgnoreCase("Q"))
					break;
				int sPrice = Integer.valueOf(strSprice);

				System.out.print("종료 가격검색(Q : QUIT) >> ");
				String strEprice = s.nextLine();
				if (strEprice.equalsIgnoreCase("Q"))
					break;
				int ePrice = Integer.valueOf(strEprice);
				
				List<BookDTO> bookList = bookDao.findByPrice(sPrice, ePrice);
				this.viewList(bookList);

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("가격은 숫자로만 입력하세요");
				continue;
			}
		}
	} // end searchBookPrice
}
