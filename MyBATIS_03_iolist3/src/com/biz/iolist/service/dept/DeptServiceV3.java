package com.biz.iolist.service.dept;

import java.util.List;

import com.biz.iolist.persistence.DeptDTO;

public class DeptServiceV3 extends DeptServiceV2 {

	@Override
	public void deptInsert() { // 키보드에서 정보를 입력받아서 db에 추가
		// 1.거래처코드 자동생성. 기존코드가 있으면 추가금지
		// 2.상호는 같은 상호가 있어도 대표자명이 다르면 입력가능
		DeptDTO dDTO = null;
		String dCode = dDao.getMaxDCode();
		String sTemp = dCode.substring(0, 1);
		int iTemp = Integer.valueOf(dCode.substring(1));
		iTemp++;
		dCode = sTemp + String.format("%04d", iTemp);
		System.out.println(dCode);
		if (dDao.findById(dCode) != null) {
			System.out.println("이미 등록되어있는 코드입니다");
			return;
		}
		while (true) {
			dDTO = new DeptDTO();
			System.out.printf("%s 거래처 코드에 정보를 추가합니다\n", dCode);
			dDTO.setD_code(dCode);
			String st = null;
			while (true) {
				System.out.print("거래처 이름(-Q : quit) >> ");
				st = s.nextLine();
				if(st.equals("-Q")) break;
				if (st.trim().isEmpty()) {
					System.out.println("거래처 이름은 반드시 입력되어야합니다");
					continue;
				}
				break;
			}
			if(st.equals("-Q")) break;
			dDTO.setD_name(st);
			while (true) {
				System.out.print("대표자 이름(-Q : quit) >> ");
				st = s.nextLine();
				if(st.equals("-Q")) break;
				if (st.trim().isEmpty()) {
					System.out.println("대표자 이름은 반드시 입력되어야합니다");
					continue;
				}
				break;
			}
			if(st.equals("-Q")) break;
			List<DeptDTO> deptList = dDao.findByDNameAndCEO(dDTO.getD_name(), st);
			System.out.println(deptList.toString());
			if (deptList.size() > 0) {
				System.out.println("이미 존재하는 거래처입니다. 다시 입력하세요");
				continue;
			}
			dDTO.setD_ceo(st);
			System.out.print("거래처 연락처(-Q : quit) >> ");
			st = s.nextLine();
			if(st.equals("-Q")) break;
			dDTO.setD_tel(st);
			System.out.print("거래처 주소(-Q : quit) >> ");
			st = s.nextLine();
			if(st.equals("-Q")) break;
			dDTO.setD_addr(st);
			
			int ret = dDao.insert(dDTO);
			if(ret > 0)
				System.out.println("입력 성공");
			else
				System.out.println("입력 실패");
			break;
		} // end 입력 while
	} // end deptInsert
}