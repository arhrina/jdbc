package com.biz.rent.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.biz.rent.persistence.UserDTO;

public interface UserDao {
	public String getMaxUCode();
	public int insert(UserDTO uDTO);
	public int update(UserDTO uDTO);
	public int delete();
	public List<UserDTO> selectAll();
	public List<UserDTO> findByNameAndTel(@Param("name") String name,
			@Param("tel") String tel);
	public UserDTO findByUCode(String uCode);
}
