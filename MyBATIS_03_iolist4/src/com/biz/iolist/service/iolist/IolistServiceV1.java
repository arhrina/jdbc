package com.biz.iolist.service.iolist;

import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.iolist.config.DBConnection;
import com.biz.iolist.dao.DeptDao;
import com.biz.iolist.dao.IolistDao;
import com.biz.iolist.dao.IolistViewDao;
import com.biz.iolist.dao.ProDao;
import com.biz.iolist.persistence.IolistDTO;
import com.biz.iolist.service.iolist.view.IolistViewServiceV1;

public class IolistServiceV1 {
	protected IolistDao iDao;
	protected DeptDao dDao;
	protected IolistViewDao iVDao;
	protected ProDao pDao;
	protected IolistDTO iDTO = new IolistDTO();
	protected IolistViewServiceV1 ioView;
	protected Scanner s;

	public IolistServiceV1() {
		SqlSession sqlSession = DBConnection.getSqlSessionFactory().openSession(true);
		pDao = sqlSession.getMapper(ProDao.class);
		dDao = sqlSession.getMapper(DeptDao.class);
		iDao = sqlSession.getMapper(IolistDao.class);
		iVDao = sqlSession.getMapper(IolistViewDao.class);
		ioView = new IolistViewServiceV1();
		s = new Scanner(System.in);
	}

	/*
	 * public void viewAllList() { List<IolistVO> iolist = iVDao.selectAll();
	 * for(IolistVO vo : iolist) { System.out.println(vo.toString()); } }
	 */
	public void iolistMenu() {
		while (true) {
			System.out.println("새나라 마트 매입매출 관리 V1");
			System.out.println("1.등록 2.수정 3.삭제 4.검색 0.종료");
			System.out.print("업무선택 >> ");
			String w = s.nextLine();
			int r = -1;
			try {
				r = Integer.valueOf(w);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("숫자만 입력하세요");
				return;
			} // end try~catch
			if (r == 0)
				break;
			else if (r == 1)
				insert();
			else if (r == 2)
				update();
			else if (r == 3)
				delete();
			else if (r == 4)
				search();
		} // end while
		System.out.println("업무종료");
	} // end iolistMenu

	protected void search() {
		// TODO 매입매출검색메소드

	}

	protected void delete() {
		// TODO 매입매출삭제메소드

	}

	protected void update() {
		// TODO 매입매출업데이트메소드

	}

	protected void insert() {
		// TODO 매입매출등록메소드

	}
}