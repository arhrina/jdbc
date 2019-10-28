package com.biz.iolist.exec;

import org.apache.ibatis.session.SqlSession;

import com.biz.iolist.config.DBConnection;

public class IolistEx_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SqlSession sqlSession = DBConnection.getSqlSessionFactory().openSession(true);
		sqlSession.close();
	}
}
