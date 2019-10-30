package com.biz.iolist.service.iolist;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.biz.iolist.persistence.DeptDTO;
import com.biz.iolist.persistence.ProductDTO;

public class IolistServiceV2 extends IolistServiceV1 {
	// 매입매출 등록
	// 현재날짜 자동등록
	// 거래처검색
	// 거래처코드입력
	// 입력한 코드 검증
	// 상품검색
	// 상품코드 입력
	// 입력코드 검증
	// 매입매출 구분 입력
	// 매입매출에 따라 매입단가, 매출단가 세팅하기(입력)
	// 매입합계 또는 매출합계 계산(단가 * 수량)
	// INSERT하고 추가한 데이터 보이기


	public IolistServiceV2() {
		s = new Scanner(System.in);
	}

	protected void insert() {
		System.out.println("매입매출 등록을 시작합니다");
		iDTO.setIo_seq((Integer.valueOf(iDao.getMaxCode())+1));

		// TODO : 현재날짜 자동등록
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String io_date = df.format(date);
		iDTO.setIo_date(io_date);

		// TODO : 거래처검색해서 코드 입력
		System.out.print("거래처 코드를 등록합니다.\n입력되어있는 거래처 이름을 입력해주세요 >> ");
		String d_name = s.nextLine();
		System.out.print("입력되어있는 거래처의 대표자명을 입력해주세요 >> ");
		String d_ceo = s.nextLine();
		List<DeptDTO> deptList = dDao.findByDNameAndCEO(d_name, d_ceo);
		if (deptList.size() < 1) {
			System.out.println("없는 거래처입니다. 정확하게 확인하고 다시 입력해주세요");
			return;
		}
		for(DeptDTO d : deptList) {
			System.out.println(d.getD_code());
			iDTO.setIo_dcode(d.getD_code());
		}

		// TODO : 상품검색해서 코드 입력
		System.out.print("상품코드를 등록합니다.\n입력되어있는 상품의 이름을 입력해주세요 >> ");
		String pName = s.nextLine();
		ProductDTO pDTO = pDao.findBySName(pName);
		if (pDTO == null) {
			System.out.println("없는 상품이름입니다. 확인 후 다시 입력해주세요");
			return;
		}
		System.out.printf("등록될 상품코드는 %s입니다\n", pDTO.getP_code());
		iDTO.setIo_pcode(pDTO.getP_code());

		// TODO : 매입매출 구분해서 입력받고 매입단가, 매출단가 값 입력하기
		while (true) {
			System.out.print("매입이면 1, 매출이면 2를 입력하세요 >> ");
			String st = s.nextLine();
			try {
				int it = Integer.valueOf(st);
				if (it == 1) {
					iDTO.setIo_inout("매입");
					break;
				} else if (it == 2) {
					iDTO.setIo_inout("매출");
					break;
				} else {
					System.out.println("매입매출은 1 또는 2만을 입력해야합니다. 다시 입력하세요");
					continue;
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("숫자만을 입력하세요");
			}
		}
		int e;
		while (true) {
			System.out.print("수량을 입력하세요 >> ");
			String qty = s.nextLine();
			try {
				e = Integer.valueOf(qty);
				break;
			} catch (Exception qe) {
				// TODO: handle exception
				System.out.println("숫자만 입력하세요");
				continue;
			}
		}
		iDTO.setIo_qty(e);

		if (iDTO.getIo_inout().equals("매입")) {
			iDTO.setIo_price(pDTO.getP_iprice());
			iDTO.setIo_total(iDTO.getIo_price() * iDTO.getIo_qty());
		} else if (iDTO.getIo_inout().equals("매출")) {
			iDTO.setIo_price(pDTO.getP_oprice());
			iDTO.setIo_total(iDTO.getIo_price() * iDTO.getIo_qty());
		}
		
		System.out.println(iDTO.toString());
		int ret = iDao.insert(iDTO);
		if (ret > 0)
			System.out.println("입력 성공");
		else
			System.out.println("입력 실패");
	} // end insert

	@Override
	protected void search() {
		// TODO 검색
		super.search();
	} // end search

	@Override
	protected void delete() {
		// TODO 삭제
		super.delete();
	} // end delete

	@Override
	protected void update() {
		// TODO 업데이트
		super.update();
	} // end update
}