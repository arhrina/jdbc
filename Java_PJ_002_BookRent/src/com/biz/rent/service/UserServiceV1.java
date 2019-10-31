package com.biz.rent.service;

import java.util.List;
import java.util.Scanner;

import com.biz.rent.config.DBConnection;
import com.biz.rent.dao.UserDao;
import com.biz.rent.persistence.UserDTO;

public class UserServiceV1 {
	protected UserDao uDao;
	protected Scanner s;
	
	public UserServiceV1() {
		uDao = DBConnection.getSqlSessionFactory().openSession(true).getMapper(UserDao.class);
		s = new Scanner(System.in);
	}
	
	public void viewAllUser() {
		List<UserDTO> userList = uDao.selectAll();
		for(UserDTO d : userList)
			System.out.println(d.toString());
	}
	
	public void insertUser() {
		System.out.println("회원을 등록합니다");
		System.out.println("회원코드는 자동으로 생성됩니다");
		String uCode = uDao.getMaxUCode();
		String tempUCode = uCode.substring(0, 2);
		int tempiUCode = Integer.valueOf(uCode.substring(2));
		tempiUCode++;
		uCode = tempUCode + String.format("%05d", tempiUCode);
		System.out.printf("등록될 회원코드는 %s입니다\n", uCode);
		
		String uName = null;
		String uTel = null;
		String uAddr = null;
		while(true) {
			System.out.print("등록할 회원이름(-Q:quit) >> ");
			uName = s.nextLine();
			if(uName.equals("-Q")) break;
			if(uName.trim().isEmpty()) {
				System.out.println("회원이름은 반드시 입력되어야합니다");
				continue;
			}
			else
				break;
		}
		if(uName.equals("-Q")) return;
		// end 이름입력
		
		System.out.print("등록할 회원의 전화번호(-Q:quit) >> ");
		uTel = s.nextLine();
		if(uTel.equals("-Q")) return;
		// end 전화번호 입력
		
		System.out.print("등록할 회원의 주소(-Q:quit) >> ");
		uAddr = s.nextLine();
		if(uAddr.equals("-Q")) return;
		// end 주소입력
		UserDTO uDTO = UserDTO.builder()
				.u_code(uCode)
				.u_name(uName)
				.u_tel(uTel)
				.u_addr(uAddr)
				.build();
		int ret = uDao.insert(uDTO);
		if(ret > 0)
			System.out.println("회원정보 등록완료");
		else
			System.out.println("회원정보 등록실패");
	}
	
	public void updateUser() {
		
	}
	
	public void searchUser() {
		System.out.println("회원정보를 검색합니다");
		System.out.print("이름을 입력 >> ");
		String name = s.nextLine();
		System.out.print("전화번호를 입력 >> ");
		String tel = s.nextLine();
		List<UserDTO> userList = uDao.findByNameAndTel(name, tel);
		if(userList.size() < 1 || userList == null)
			System.out.println("검색결과가 없습니다");
		for(UserDTO d : userList)
			System.out.println(d.toString());
	}
}
