package com.biz.iolist.service.dept;

import com.biz.iolist.persistence.DeptDTO;

public class DeptServiceV2 extends DeptServiceV1 {

	public void deptMenu() {
		System.out.println("거래처 정보 관리");
		System.out.println("1.등록 2.수정 3.삭제 4.검색 0.종료");
		System.out.print("업무선택 >> ");
		String strMenu = s.nextLine();
		try {
			int intMenu = Integer.valueOf(strMenu);
			// else를 사용하지 않으면 모든 if를 검사하며 진행되어 불필요한 검사를 진행한다
			if (intMenu == 0)
				return;
			else if (intMenu == 1) {
				deptInsert();
			} else if (intMenu == 2) {
				viewNameList();
				deptUpdate();
			} else if (intMenu == 3) { // 상호로 검색해서 정보를 보여주고 거래처코드를 입력받아서 삭제
				viewNameList();
				deptDelete();
			} else if (intMenu == 4) {
				viewNameAndCEO();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void deptDelete() {
		while (true) {
			System.out.print("삭제할 거래처 코드(-Q : quit) >> ");
			String strDCode = s.nextLine();
			DeptDTO dDTO = dDao.findById(strDCode);
			if(strDCode.equals("-Q")) break;
			if (dDTO == null) {
				System.out.println("삭제할 거래처코드가 없음");
				continue;
			}
			viewDetail(dDTO);
			System.out.println("정말 삭제? Enter:실행");
			String strYesNo = s.nextLine();
			if(strYesNo.trim().isEmpty()) {
				int ret = dDao.delete(strDCode);
				if(ret > 0)
				{
					System.out.println("삭제 성공");
					break;
				}
				else
					System.out.println("삭제 실패");
			}
		} // end while
	} // end deptDelete
	
	public void deptUpdate() {
		System.out.print("업데이트할 거래처 코드(-Q : quit) >> ");
		String strDCode = s.nextLine();
		DeptDTO dDTO = dDao.findById(strDCode);
		if(strDCode.equals("-Q")) return;
		System.out.printf("상호이름(%s) >> ", dDTO.getD_name());
		String d = s.nextLine();
		if(!d.trim().isEmpty())
			dDTO.setD_name(d);
		System.out.printf("대표(%s) >> ", dDTO.getD_ceo());
		d = s.nextLine();
		if(!d.trim().isEmpty())
			dDTO.setD_ceo(d);
		System.out.printf("전화번호(%s) >> ", dDTO.getD_tel());
		d = s.nextLine();
		if(!d.trim().isEmpty())
			dDTO.setD_tel(d);
		System.out.printf("주소(%s) >> ", dDTO.getD_addr());
		d = s.nextLine();
		if(!d.trim().isEmpty())
			dDTO.setD_addr(d);
		System.out.println(dDTO.toString());
		int ret = dDao.update(dDTO);
		if(ret > 0)
			System.out.println("업데이트 성공");
		else
			System.out.println("업데이트 실패");
	}
	
	public void deptInsert() {
		
	}
}