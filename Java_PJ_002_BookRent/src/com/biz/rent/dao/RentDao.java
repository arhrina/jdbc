package com.biz.rent.dao;

import java.util.List;

import com.biz.rent.persistence.RentDTO;

public interface RentDao {
	public RentDTO getRentCheck(int seq);
	public int insert(RentDTO rDTO);
	public int update(RentDTO rDTO);
	public int delete();
	public List<RentDTO> selectAll();
	public List<RentDTO> findByBCode(String bCode);
//	public Integer getSEQ(); SEQ를 데이터베이스에서 생성
}