package com.biz.rent.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.biz.rent.persistence.BookDTO;

public interface BookDao {
	public String getMaxBCode();
	public List<BookDTO> selectAll();
	public int insert(BookDTO bDTO);
	public int update(BookDTO bDTO);
	public int delete();
	public BookDTO findByBCode(String b_code);
	public BookDTO findBySubject(String subject);
	public List<BookDTO> findByWriter(String writer);
	public List<BookDTO> findBySubAndAuth(@Param("subject") String subject,
			@Param("writer") String writer);
}
