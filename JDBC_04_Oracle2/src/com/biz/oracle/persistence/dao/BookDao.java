package com.biz.oracle.persistence.dao;

import java.sql.Connection;
import java.util.List;

import com.biz.oracle.config.DBConnection;
import com.biz.oracle.persistence.BookDTO;

/*
 * 추상클래스로 선언
 * tbl_books 테이블의 CRUD
 */
public abstract class BookDao {
	protected Connection dbConn;
	public BookDao() {
		// TODO Auto-generated constructor stub
		dbConn = DBConnection.getDBConnection();
	}
	/*
		B_CODE : Primary Key. 이 컬럼을 기준으로 findById(String b_code)
		B_NAME : findByName(String b_name)
		B_COMP : findByComp(String b_comp)
		B_WRITER : findByWriter(String b_writer)
		B_PRICE : findByPrice(int price), findByPrice(int sPrice, int ePrice)
	 */
	public abstract List<BookDTO> selectAll();
	
	public abstract BookDTO findById(String b_code); // PK 기준 조회
	public abstract List<BookDTO> findByName(String b_name);
	public abstract List<BookDTO> findByComp(String b_comp);
	public abstract List<BookDTO> findByWriter(String b_writer);
	public abstract List<BookDTO> findByPrice(int price);
	public abstract List<BookDTO> findByPrice(int sPrice, int ePrice);
	
	public abstract int insert(BookDTO bookDTO);
	public abstract int update(BookDTO bookDTO);
	public abstract int delete(String b_code);
}