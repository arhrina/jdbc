package com.biz.iolist.config.service.pro;

import com.biz.iolist.persistence.ProductDTO;

public class ProductServiceV3 extends ProductServiceV2 {
	
	
	public void menuProduct() {
		System.out.println("==============================");
		System.out.println("대한쇼핑몰 상품관리 시스템 v3");
		System.out.println("==============================");
		System.out.println("1.등록  2.수정  3.삭제  4.검색 0.끝");
		System.out.print("업무선택 >> ");
		String strMenu = s.nextLine();
		int intMenu = Integer.valueOf(strMenu);
		if(intMenu == 1)
			insertProduct();
		else if(intMenu == 2) {
			searchPName();
			proUpdate();
		}
		else if(intMenu == 3) {
			searchPName();
			deleteProduct();
		}
	}
	
	/*
	 * 상품정보들을 입력받아서 insert 수행
	 * 상품코드를 입력받아서
	 * 있으면 다시 입력하도록
	 * 없으면 다음으로 진행
	 * 
	 * 1.상품코드를 어떻게 입력할 것인가?(자동으로 만들기, 이미 문서로 작성된 경우 직접 입력하기)
	 * 2.상품이름을 입력하는데 완전히 일치하는 상품이 이미 있는 경우(코드가 다르면 입력, 코드가 다르고 단가가 다르면 입력, 같은 상품이름 입력 금지)
	 * 		상품정보 : 상품이름, 품목, 주 매입처 중 하나라도 다르면 현실에서는 다른 상품으로 인식하고 입력한다
	 * 3.단가
	 * 		매입단가를 입력하면 표준판매단가를 계산
	 * 		매입단가, 매출단가를 별도로 입력
	 * 		매입단가일 경우 VAT 포함여부
	 * 		매출단가는 소매점에선 VAT 포함이 기본, 도매점에서는 VAT 포함여부를 고려해야
	 */
	public void insertProduct() {
		String strPCode;
		while(true) {
			System.out.print("상품코드(Enter : 자동생성, -Q : quit) >> ");
			strPCode = s.nextLine();
			if(strPCode.equals("-Q")) break;
			if(strPCode.trim().isEmpty()) {
			// 코드자동생성. mapper에서 SQL문을 이용.
			// SELECT MAX(p_code) FROM tbl_product; 현재 입력된 p_code 중 가장 큰 값을 확인
				String strTMPCode = pDao.getMaxPCode();
				int intPCode = Integer.valueOf(strTMPCode.substring(1));
			// 5자리 코드 중 맨 앞 영어를 떼고 숫자만 가져와서 1 증가시키고 맨 앞 영어를 다시 붙이고 숫자를 문자열로
				intPCode++;
				strPCode = strTMPCode.substring(0, 1);
				strPCode += String.format("%04d", intPCode);
				System.out.println("생성된 코드 : " + strPCode);
				System.out.print("사용하시겠습니까?(Enter : Yes)");
				String strYesNo = s.nextLine();
				if(strYesNo.trim().isEmpty())
					break;
				else
					continue;
			}
			if(strPCode.length() != 5) { // 상품코드 유효성검사
				System.out.println("상품코드의 길이규칙에 맞지 않음");
				continue;
			}
			strPCode = strPCode.toUpperCase();
			if(!strPCode.substring(0, 1).equalsIgnoreCase("P")) { // 상품코드 유효성검사
				System.out.println("상품코드는 첫글자가 P로 시작되어야함");
				continue;
			}
			try { // 상품코드 유효성검사
				Integer.valueOf(strPCode.substring(1));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("상품코드의 두번째부터는 숫자만 입력되어야함");
				continue;
			}
			if(pDao.findById(strPCode) != null) { // 상품코드 유효성검사
				System.out.println("이미 등록되어있는 코드");
				continue;
			}
			break;
		} // end while. pcode 입력끝
		if(strPCode.equals("-Q")) return;
		String strPName;
		while(true) {
			System.out.print("상품이름 (-Q : quit) >> ");
			strPName = s.nextLine();
			if(strPName.equals("-Q")) break;
			if(strPName.isEmpty()) {
				System.out.println("상품이름은 반드시 입력해야함");
				continue;
			}
			ProductDTO pDTO = pDao.findBySName(strPName);
			// pDTO = viewPDetail(pDTO.getP_code());
			if(pDTO != null) {
				System.out.println("같은 이름의 상품이 있음");
				System.out.println("상품코드 : " + pDTO.getP_code());
				System.out.println("상품이름 : " + pDTO.getP_name());
				System.out.println("매입단가 : " + pDTO.getP_iprice());
				System.out.println("매출단가 : " + pDTO.getP_oprice());
				System.out.println("이 이름을 사용하시겠습니까?(Enter:사용, NO:다시입력)");
				String yesNo = s.nextLine();
				if(yesNo.trim().isEmpty()) break;
				continue;
			}
			pDTO.setP_name(strPName);
		} // end while. pname 입력끝

	}
	
	/*
	 * 상품코드를 입력받아서 delete 수행
	 */
	public void deleteProduct() {
		System.out.print("삭제할 상품코드 >> ");
		String strPCode = s.nextLine();
		ProductDTO pDTO = viewPDetail(strPCode);
		if(pDTO == null) {
			System.out.println("상품코드가 없습니다");
			return;
		}
		
		int ret = pDao.delete(strPCode);
		if(ret > 0)
			System.out.println("정상적으로 삭제되었습니다");
		else
			System.out.println("삭제 실패");
	}
}