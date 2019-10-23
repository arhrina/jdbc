package com.biz.mybatis.exec;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.biz.mybatis.config.DBConnection;
import com.biz.mybatis.mapper.BookDao;
import com.biz.mybatis.persistence.BookDTO;

public class MyBatisEx_03 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SqlSession sqlSession; // DBMS와 java application간에 연결을 called session
		sqlSession = DBConnection.getSqlSessionFactory().openSession(true);
		// JDBC의 다양한 클래스들을 대신하여 java application과 DBMS간의 연결 Connection을 대신 관리해줄 클래스
		// sqlSession에 config파일 설정으로부터 연결

		// Session
		// 네트워크 환경에서 지점과 지점사이가 다양한 방법으로 연결되고 데이터를 주고 받을 준비가 된 통로

		BookDao bookDao = sqlSession.getMapper(BookDao.class);
		// BookDao.class라는 인터페이스를 참조해서 config에 있는 Mapper를 가져오고, mapper는
		// com/biz/mybatis/mapper/book-mapper.xml에 있다
		// dao.SelectAll을 호출하면 interface에 있는 selectAll의 이름인 id를 Mapper 파일에서 찾아 sql문을
		// DBMS에 전송
		// resultType에 있는 class에 담는데, interface에 있는 메소드 return type에 맞춰 return해준다

		BookDTO bDTO = BookDTO
				.builder()
				.b_name("MYBATIS")
				.b_comp("경영원")
				.b_writer("내멋으로")
				.b_price(50000)
				.build();
		bookDao.insert(bDTO);
		// 맵퍼에 insert에 만든 값이 들어있는 DTO를 날려준 것을 받아서 SQL문으로 날린다
		List<BookDTO> bookList = bookDao.selectAll();
		for(BookDTO b : bookList)
			System.out.println(b.toString());
	}
}
