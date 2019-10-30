package com.biz.iolist.service.iolist.view;

import java.util.List;

import com.biz.iolist.config.DBConnection;
import com.biz.iolist.dao.IolistViewDao;
import com.biz.iolist.persistence.IolistVO;

public class IolistViewServiceV1 {
	IolistViewDao ioViewDao;
	
	public IolistViewServiceV1() {
		ioViewDao = DBConnection.getSqlSessionFactory().openSession(true)
				.getMapper(IolistViewDao.class);
	}
	
	protected void viewList(List<IolistVO> iolist) {
		System.out.println("매입매출정보");
		System.out.println("거래일자\t구분\t거래처\t상품\t수량\t단가\t합계");
		for(IolistVO vo : iolist) {
			viewItem(vo);
		}
	}
	
	protected void viewItem(IolistVO vo) {
		System.out.print(vo.getIo_date() + "\t");
		System.out.print(vo.getIo_inout() + "\t");
		System.out.printf("(%s)%s", vo.getIo_dcode(), vo.getTbl_iolist_dname() + "\t");
		System.out.printf("(%s)%s", vo.getIo_pcode(), vo.getTbl_iolist_pname() + "\t");
		System.out.print(vo.getIo_qty() + "\t");
		System.out.print(vo.getIo_price() + "\t");
		System.out.print(vo.getIo_total() + "\n");
	}
	
	public void viewAllList() {
		List<IolistVO> iolist = ioViewDao.selectAll();
		if(iolist != null && iolist.size() > 0) {
			viewList(iolist);
		}
	}
	
	public void viewListByPCode(String pcode) { // 상품코드를 매개변수로 받아서 리스트 출력
		List<IolistVO> iolist = ioViewDao.findByPCode(pcode);
		if(iolist != null && iolist.size() > 0) {
			viewList(iolist);
		}
	}
	
	public void viewListByPName(String pcode) { // 상품이름을 매개변수로 받아서 리스트 출력
		List<IolistVO> iolist = ioViewDao.findByPName(pcode);
		if(iolist != null && iolist.size() > 0) {
			viewList(iolist);
		}
	}
	
	public void viewListByDCode(String pcode) { // 거래처코드를 매개변수로 받아서 리스트 출력
		List<IolistVO> iolist = ioViewDao.findByDCode(pcode);
		if(iolist != null && iolist.size() > 0) {
			viewList(iolist);
		}
	}
	
	public void viewListByDName(String pcode) { // 거래처이름을 매개변수로 받아서 리스트 출력
		List<IolistVO> iolist = ioViewDao.findByDName(pcode);
		if(iolist != null && iolist.size() > 0) {
			viewList(iolist);
		}
	}
}
