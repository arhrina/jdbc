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
		for(AddrDTO a : addrList)
			System.out.println(a.toString());
	}
	
	public void searchAddressById() {
		List<AddrDTO> addrList;
		System.out.print("ID를 입력하세요 >> ");
		String strId = s.nextLine();
		long id = Long.valueOf(strId);
		addrList = aDao.findById(id);
		if(addrList.size() < 1)
			System.out.println("일치하는 id값을 가진 데이터가 없습니다");
		else {
			for(AddrDTO a : addrList)
			System.out.println(a.toString());
		}
	}
	
	public void searchAddressByTel() {
		List<AddrDTO> addrList;
		System.out.print("전화번호를 입력하세요 >> ");
		String strTel = s.nextLine();
		addrList = aDao.findByTel(strTel);
		if(addrList.size() < 1)
			System.out.println("일치하는 id값을 가진 데이터가 없습니다");
		else {
			for(AddrDTO a : addrList)
			System.out.println(a.toString());
		}
	}
	
	public void searchAddressByName() {
		List<AddrDTO> addrList;
		System.out.print("이름을 입력하세요 >> ");
		String strName = s.nextLine();
		addrList = aDao.findByName(strName);
		if(addrList.size() < 1)
			System.out.println("일치하는 id값을 가진 데이터가 없습니다");
		else {
			for(AddrDTO a : addrList)
			System.out.println(a.toString());
		}
	}
	
	public void searchAddressByRelat() {
		List<AddrDTO> addrList;
		System.out.print("관계를 입력하세요 >> ");
		String strRelat = s.nextLine();
		addrList = aDao.findByRelat(strRelat);
		if(addrList.size() < 1)
			System.out.println("일치하는 id값을 가진 데이터가 없습니다");
		else {
			for(AddrDTO a : addrList)
			System.out.println(a.toString());
		}
	}
	
	public void insertData() {
		aDao.insertData();
	}
	
	public void deleteData() {
		aDao.deleteData();
	}
	
	public void updateData() {
		aDao.updateData();
	}
}