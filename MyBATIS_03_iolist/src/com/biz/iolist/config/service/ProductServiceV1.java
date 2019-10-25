package com.biz.iolist.config.service;

import java.util.List;
import java.util.Scanner;

import com.biz.iolist.config.DBConnection;
import com.biz.iolist.dao.ProDao;
import com.biz.iolist.persistence.ProductDTO;

public class ProductServiceV1 {
	protected ProDao pDao;
	protected Scanner s;
	public ProductServiceV1() {
		pDao = DBConnection.getSqlSessionFactory().openSession(true).getMapper(ProDao.class);
		s = new Scanner(System.in);
	}
	
	public void proUpdate() { // 리스트를 한번 보여주고, 업데이트할 상품 코드를 입력받아서 업데이트
		List<ProductDTO> proList = pDao.selectAll();
		for(ProductDTO p : proList)
			System.out.println(p.toString());
		System.out.print("수정할 상품의 코드(-Q : quit) >> ");
		String strPCode = s.nextLine();
		if(strPCode.equals("-Q")) return;
		strPCode = strPCode.toUpperCase(); // 상품코드 대문자 변경
		ProductDTO pDTO = pDao.findById(strPCode);
		System.out.println("========================================");
		System.out.println("상품코드 : " + pDTO.getP_code());
		System.out.println("상품이름 : " + pDTO.getP_name());
		System.out.println("매입단가 : " + pDTO.getP_iprice());
		System.out.println("매출단가 : " + pDTO.getP_oprice());
		String strVat = pDTO.getP_vat().equals("1") ? "과세" : "면세" ;
		// 삼항연산자
		System.out.println("과세여부 : " + strVat);
		System.out.println("========================================");
		
		System.out.print("변경할 상품이름 >> ");
		String strPName = s.nextLine();
		if(!strPName.trim().isEmpty())
		// strPName에 아무런 문자열도 들어있지 않으면
		// if(strPName.trim().isEmpty())
		// if(strPName.trim().length() < 1)
			pDTO.setP_name(strPName);
		
		System.out.print("변경할 매입단가 >> ");
		String strIPrice = s.nextLine();
		try {
			// 아무 값을 넣지 않는다면 Integer.valueOf에서 NumberFormattingException이 발생하고 나머지는 무시되고
			// catch로 나가는데 catch는 아무 행동도 하지 않으므로 아무런 행위도 하지 않는다
			// 값을 입력하지 않거나 문자열 등을 포함했을 때 변경하지 않는 것으로 이용할 수 있다
			pDTO.setP_iprice(Integer.valueOf(strIPrice));
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.print("변경할 매출단가 >> ");
		String strOPrice = s.nextLine();
		try {
			// 아무 값을 넣지 않는다면 Integer.valueOf에서 NumberFormattingException이 발생하고 나머지는 무시되고
			// catch로 나가는데 catch는 아무 행동도 하지 않으므로 아무런 행위도 하지 않는다
			// 값을 입력하지 않거나 문자열 등을 포함했을 때 변경하지 않는 것으로 이용할 수 있다
			pDTO.setP_oprice(Integer.valueOf(strOPrice));
		} catch (Exception e) {
			// TODO: handle exception
		}
		// 값을 입력하지 않았다면 원래 있던 값들이 pDTO에 남아있을 것이고,
		// 입력했다면 새로운 값들이 pDTO에 세팅이 되어 있을 것이다
		int ret = pDao.update(pDTO);
		if(ret > 0)
			System.out.println("데이터 업데이트 완료");
		else
			System.out.println("데이터 업데이트 실패");

		// 확인작업
		System.out.println(pDao.findById(strPCode).toString());
	}
}