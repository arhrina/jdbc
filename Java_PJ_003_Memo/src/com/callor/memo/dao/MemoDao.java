package com.callor.memo.dao;

import java.util.List;

import com.callor.memo.persistence.MemoDTO;

public interface MemoDao {
	public int insert(MemoDTO mDTO);
	public List<MemoDTO> viewAllList();
	public List<MemoDTO> searchBySEQ(int intSeq);
	public List<MemoDTO> searchBySub(String str);
	public List<MemoDTO> searchByWri(String str);
	public MemoDTO searchByNum(int intSeq);
	public int update(MemoDTO mDTO);
	public int delete(int intSeq);
	public List<MemoDTO> viewAllSEQ();
	public List<MemoDTO> viewAllSub();
	public List<MemoDTO> viewAllWri();
}
