package com.biz.iolist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.biz.iolist.persistence.DeptDTO;

public interface DeptDao {
	public List<DeptDTO> selectAll();
	public DeptDTO findById(String d_code); // PK 검색
	public String getMaxDCode();
	public List<DeptDTO> findByName(String d_name);
	public List<DeptDTO> findByNameAndCEO
			(@Param("d_name") String d_name,
			@Param("d_ceo") String d_ceo);
	public List<DeptDTO> findByDNameAndCEO
	(@Param("d_name") String d_name,
	@Param("d_ceo") String d_ceo);
	public int insert(DeptDTO dDTO);
	public int update(DeptDTO dDTO);
	public int delete(String d_code);
}
