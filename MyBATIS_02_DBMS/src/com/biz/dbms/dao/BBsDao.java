package com.biz.dbms.dao;

import java.util.List;

import com.biz.dbms.persistence.BBsDTO;

public interface BBsDao {
	public List<BBsDTO> selectAll();
	public BBsDTO findById(long id); // Mapper에 #{ arugment이름 }
	public int insert(BBsDTO bbsDTO);
	public int update(BBsDTO bbsDTO);
	public int delete(long id);
}
