package com.biz.rent.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.biz.rent.config.DBConnection;
import com.biz.rent.dao.BookDao;
import com.biz.rent.dao.RentDao;
import com.biz.rent.dao.UserDao;
import com.biz.rent.persistence.BookDTO;
import com.biz.rent.persistence.RentDTO;
import com.biz.rent.persistence.UserDTO;

public class RentServiceV1 {
	protected BookDao bDao;
	protected RentDao rDao;
	protected UserDao uDao;
	protected BufferedReader s;

	public RentServiceV1() {
		bDao = DBConnection.getSqlSessionFactory().openSession(true).getMapper(BookDao.class);
		rDao = DBConnection.getSqlSessionFactory().openSession(true).getMapper(RentDao.class);
		uDao = DBConnection.getSqlSessionFactory().openSession(true).getMapper(UserDao.class);
		s = new BufferedReader(new InputStreamReader(System.in));
	}

	public void menu() throws IOException {

		System.out.println("책방 서비스 v1");
		while (true) {
			System.out.println("1.대출확인\t2.대출\t3.반납\t4.검색\t0.종료");
			System.out.print("업무선택 >> ");
			String str = s.readLine();
			int ints = -1;
			try {
				ints = Integer.valueOf(str);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("숫자만 입력하세요");
				continue;
			}
			if (ints == 0) {
				break;
			} else if (ints == 1) {
				rentCheck();
				continue;
			} else if (ints == 2) {
				rentBook();
				continue;
			} else if (Integer.valueOf(str) == 3) {
				returnBook();
				continue;
			} else if (Integer.valueOf(str) == 4) {
				while (true) {
					System.out.println("1.회원검색\t2.도서검색");
					System.out.print("업무선택 >> ");
					str = s.readLine();
					try {
						int i = Integer.valueOf(str);
						if (i == 1) {
							UserServiceV1 us = new UserServiceV1();
							us.searchUser();
							break;
						} else if (i == 2) {
							BookServiceV1 bs = new BookServiceV1();
							bs.searchSubAndAuth();
							break;
						} else {
							System.out.println("입력을 확인할 수 없습니다. 다시 입력하세요");
							continue;
						}
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("숫자만 입력하세요");
						continue;
					}
				} // 검색기능 while end
			} // end if
			else {
				System.out.println("서비스를 확인할 수 없습니다. 다시 입력하세요");
				continue;
			}
		} // end while
		System.out.println("도서대출 서비스를 종료합니다");
	} // end menu

	@SuppressWarnings({ "null", "unused" })
	public void rentBook() throws IOException {
		// TODO Auto-generated method stub
		/* 일련번호를 오라클의 SEQUENCE로 대체
		int seq;
		if(rDao.getSEQ() == null)
		{
			seq = 1;
		}
		else {
			seq = rDao.getSEQ();
			seq++;
		}
		*/
		RentDTO rDTO = new RentDTO();
		// rDTO.setRent_seq(seq); // 일련번호 가져와서 세팅
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date(System.currentTimeMillis());
		String strToday = sf.format(today);
		rDTO.setRent_date(strToday);
		// 오늘날짜 세팅
		Date returnDay = new Date();
		returnDay.setTime(today.getTime() + (long) (1209600 * 60 * 60 * 24)); // 14일의 초를 덧셈
		String strRDay = sf.format(returnDay);
		rDTO.setRent_return_date(strRDay);
		// 반환날짜 세팅

		String bCode;
		while (true) {
			BookServiceV1 bs = new BookServiceV1();
			bs.searchSubAndAuth();
			System.out.print("대출해줄 도서코드를 입력(-Q:quit) >> ");
			bCode = s.readLine();
			if (bCode.equals("-Q"))
				break;
			BookDTO bDTO = bDao.findByBCode(bCode);
			List<RentDTO> rentList = rDao.findByBCode(bCode);
			System.out.println(rentList.toString());
			if(rentList.size() > 0) {
				System.out.println("이미 대출된 도서입니다");
				continue;
			}
			if (bDTO == null) {
				System.out.println("도서코드를 잘못입력했습니다");
				continue;
			} else {
				rDTO.setRent_bcode(bDTO.getB_code());
				break;
			}
		} // 도서정보 확인해서 도서코드 세팅. bs 없애기위해 안에서 생성
		if (bCode.equals("-Q"))
			return;

		String uCode;
		while (true) {
			UserServiceV1 us = new UserServiceV1();
			us.searchUser();
			System.out.print("대출해줄 유저코드를 입력(-Q:quit) >> ");
			uCode = s.readLine();
			if (uCode.equals("-Q"))
				break;
			UserDTO uDTO = uDao.findByUCode(uCode);
			if (uDTO == null) {
				System.out.println("유저코드를 잘못입력했습니다");
				continue;
			} else {
				rDTO.setRent_ucode(uDTO.getU_code());
				break;
			}
		}
		if (uCode.equals("-Q"))
			return; // 유저정보 확인해서 유저코드 세팅. us 없애기 위해 안에서 생성
		int ret = rDao.insert(rDTO);
		if(ret > 0)
			System.out.println("대출 완료");
		else
			System.out.println("대출 실패");
	}

	public void returnBook() throws IOException {
		// TODO Auto-generated method stub
		// 현재 대여중인 도서리스트 show
		// SEQ 입력했을 시 해당 대여정보에서 반납예정일을 검사해서
		// 반납예정일 >= 반납일이면 포인트 5점
		// 반납예정일 < 반납일이면 포인트 0점
		// RENT_RETURN_YN컬럼 Y
		// 대여일은 렌트일로부터 14일
		rentCheck();
		System.out.print("반환처리할 작업번호를 입력(-Q : quit) >> ");
		String str = s.readLine();
		int intSeq = -1;
		if (str.equals("-Q"))
			return;
		try {
			intSeq = Integer.valueOf(str);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("숫자만을 입력하세요");
			return;
		}
		RentDTO rDTO = rDao.getRentCheck(intSeq);
		if (rDTO == null) {
			System.out.println("없는 번호입니다. 확인하고 다시 입력하세요");
			return;
		} else {
			System.out.println("정말로 반환처리? Enter:yes");
			String yesNo = s.readLine();
			if (yesNo.trim().isEmpty()) {
				rDTO.setRent_retur_yn("y");
				/*
				 * Date today = new Date(System.currentTimeMillis()); Date returnDay; //
				 * rDTO.getRent_return_date() String형을 Date형으로 만들 방법?; if(returnDay - today >=
				 * 0) rDTO.setRent_point(+500);
				 */
				Date today = new Date(System.currentTimeMillis());
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				String strToday = sf.format(today);
				String rentDueDate = rDTO.getRent_return_date();
				/* String을 날짜형으로 변환하기. 날짜끼리 계산하는 경우
				try {
					sf.parse(rentDueDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/
				int diff = strToday.compareTo(rentDueDate);
				if (diff > 0) {
					System.out.println("지연반납");
					rDTO.setRent_point(+5);
				} else {
					System.out.println("정상반납");
				}
			}
		}
		int ret =rDao.update(rDTO);
		if(ret > 0)
			System.out.println("반납 완료");
		else
			System.out.println("반납 실패");
	} // 반환완료

	public void rentCheck() {
		// TODO Auto-generated method stub
		// 도서중인 대여목록 show
		// return yn이 y인 값을 빼고 전부 show
		List<RentDTO> rentList = rDao.selectAll();
		if (rentList.size() < 1 || rentList == null) {
			System.out.println("현재 대여중인 목록이 없습니다");
		}
		else {
			for (RentDTO d : rentList)
				System.out.println(d.toString());
		}
	}
}