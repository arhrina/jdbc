package com.biz.iolist.config.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.biz.iolist.config.DBConnection;
import com.biz.iolist.dao.DeptDao;
import com.biz.iolist.dao.IolistDao;
import com.biz.iolist.dao.IolistViewDao;
import com.biz.iolist.dao.ProDao;
import com.biz.iolist.persistence.IolistVO;

public class IolistServiceV1 {
	protected IolistDao iDao;
	protected DeptDao dDao;
	protected IolistViewDao iVDao;
	protected ProDao pDao;
	
	public IolistServiceV1() {
		SqlSession sqlSession = DBConnection.getSqlSessionFactory().openSession(true);
		pDao = sqlSession.getMapper(ProDao.class);
		dDao = sqlSession.getMapper(DeptDao.class);
		iDao = sqlSession.getMapper(IolistDao.class);
		iVDao = sqlSession.getMapper(IolistViewDao.class);
	}
	
	public void viewAllList() {
		List<IolistVO> iolist = iVDao.selectAll();
		for(IolistVO vo : iolist) {
			System.out.println(vo.toString());
		}
	}
}