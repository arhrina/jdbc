package com.biz.mybatis.mapper;

import java.util.List;

import com.biz.mybatis.persistence.BookDTO;

public interface BookDao {
	public List<BookDTO> selectAll(); // DB에서 모든 레코드를 가져와서 List로 만듦
	public BookDTO findById(String b_code);
	public BookDTO findByName(String b_name);
	public int insert(BookDTO bookDTO);
	public int update(BookDTO bookDTO);
	public int delete(String b_code);
}

// mybatis에서 다형성은 지원하지 않는다