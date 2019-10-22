package com.biz.addr.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.biz.addr.persistence.AddrDTO;
import com.biz.addr.persistence.dao.AddrDao;

public class AddrServiceV1 {
	AddrDao aDao;
	Scanner s;

	public AddrServiceV1() {
		s = new Scanner(System.in);
		aDao = new AddrDao();
	}

	public void viewAll() {
		List<AddrDTO> addrList = new ArrayList<AddrDTO>();
		addrList = aDao.selectAll();
		for (AddrDTO a : addrList)
			System.out.println(a.toString());
	}

	public void searchAddressById() {
		List<AddrDTO> addrList;
		System.out.print("ID를 입력하세요 >> ");
		String strId = s.nextLine();
		long id = Long.valueOf(strId);
		addrList = aDao.findById(id);
		if (addrList.size() < 1)
			System.out.println("일치하는 id값을 가진 데이터가 없습니다");
		else {
			for (AddrDTO a : addrList)
				System.out.println(a.toString());
		}
	}

	public void searchAddressByTel() {
		List<AddrDTO> addrList;
		System.out.print("전화번호를 입력하세요 >> ");
		String strTel = s.nextLine();
		addrList = aDao.findByTel(strTel);
		if (addrList.size() < 1)
			System.out.println("일치하는 id값을 가진 데이터가 없습니다");
		else {
			for (AddrDTO a : addrList)
				System.out.println(a.toString());
		}
	}

	public void searchAddressByName() {
		List<AddrDTO> addrList;
		System.out.print("이름을 입력하세요 >> ");
		String strName = s.nextLine();
		addrList = aDao.findByName(strName);
		if (addrList.size() < 1)
			System.out.println("일치하는 id값을 가진 데이터가 없습니다");
		else {
			for (AddrDTO a : addrList)
				System.out.println(a.toString());
		}
	}

	public void searchAddressByRelat() {
		List<AddrDTO> addrList;
		System.out.print("관계를 입력하세요 >> ");
		String strRelat = s.nextLine();
		addrList = aDao.findByRelat(strRelat);
		if (addrList.size() < 1)
			System.out.println("일치하는 id값을 가진 데이터가 없습니다");
		else {
			for (AddrDTO a : addrList)
				System.out.println(a.toString());
		}
	}

	public void insertData() {
		while (true) {
			String strB_name;
			while (true) {
				try {
					System.out.print("1.이름(Q : Quit) >> ");
					strB_name = s.nextLine();
					if (strB_name.equalsIgnoreCase("Q"))
						break;
					if (strB_name.isEmpty()) {
						System.out.println("이름은 반드시 입력해야합니다");
						continue;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				break;
			}
			if (strB_name.equalsIgnoreCase("Q"))
				break;

			System.out.print("2.전화번호(Q : Quit) >> ");
			String strB_comp = s.nextLine();
			if (strB_comp.equalsIgnoreCase("Q"))
				break;
			System.out.print("3.주소(Q : Quit) >> ");
			String strB_writer = s.nextLine();
			if (strB_writer.equalsIgnoreCase("Q"))
				break;

			String strB_price;
			System.out.print("4.관계(Q : Quit) >> ");
			strB_price = s.nextLine();
			if (strB_price.equalsIgnoreCase("Q"))
				break;
		}

		AddrDTO aDTO = AddrDTO.builder().name(strB_name).tel(strB_comp).addr(strB_writer).relat(strB_price).build();

		int ret = aDao.insertData(aDTO);
		if (ret > 0)
			System.out.println("주소정보 입력완료");
		else
			System.out.println("주소정보 입력실패");
	}

	public void deleteData() {
		while(true) {
			System.out.print("삭제할 아이디(Q : quit) >> ");
			String strCode = s.nextLine();
			if(strCode.equalsIgnoreCase("Q")) break;
			List<AddrDTO> addrList = aDao.findById(Long.valueOf(strCode));
			if(addrList == null) {
				System.out.println("아이디가 없습니다");
				continue;
			}
			aDao.deleteData(strCode);				
		}
		
	}

	public void updateData() {
		System.out.println("주소정보 수정");
		System.out.print("수정할 아이디(Q : QUIT) >> ");
		String strCode = s.nextLine();
		if(strCode.equalsIgnoreCase("Q")); // 입력끝내기
		AddrDTO addrDTO;
		
		// 입력받은 코드에 해당하는 도서정보를 가져오기
		List<AddrDTO> addrList = aDao.findById(Integer.valueOf(strCode));
		// bookDao.update(bDTO);
		
		// 각 항목별로 값을 수정. PK는 수정하지 않는 것이 좋다. PK를 수정하면 무결성이 훼손될 수 있다
		/*
		 * 보통 PK를 수정하는 것은 기존의 데이터를 삭제하고 새로운 데이터를 입력하거나
		 * 기존 데이터를 그대로 유지하고 새로운 데이터를 입력하는 경우
		 */
		System.out.printf("변경할 이름(%s) >> ", addrDTO.getName());
		String strName = s.nextLine();
		if(strName.trim().length() > 0) { // 공백이 입력되면 문제가 있을 수 있으므로 trim을 추가. 객체의 chaining
			addrDTO.setName(strName);
		}
		// 새로운 도서명을 입력했을 때는 bookDTO의 필드를 새로운 도서명으로 대치하고
		// 그냥 엔터만 입력했을 때는 변경하지 않는다
		
		System.out.printf("변경할 연락처(%s) >> ", addrDTO.getTel());
		String strComp = s.nextLine();
		if(strComp.trim().length() > 0) {
			addrDTO.setTel(strComp);
		}
		
		System.out.printf("변경할 주소(%s) >> ", addrDTO.getAddr());
		String strWriter = s.nextLine();
		if(strWriter.trim().length() > 0) {
			addrDTO.setAddr(strWriter);
		}
		
		System.out.printf("변경할 관계(%s) >> ", addrDTO.getRelat());
		String strPrice = s.nextLine();
		addrDTO.setRelat(strPrice);
		
		// 여기에 도착하면 bDTO에 담긴 값은 변경된 값들과 원래 있던 값들이 남아있는 인스턴스이다
		aDao.updateData(addrDTO);
		int ret = aDao.updateData(addrDTO);
		if(ret > 0) {
			System.out.println("주소정보 변경");
		}
		else {
			System.out.println("주소정보 변경실패");
		}

	}
}
