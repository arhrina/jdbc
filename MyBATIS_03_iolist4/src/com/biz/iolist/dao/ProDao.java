package com.biz.iolist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.biz.iolist.persistence.ProductDTO;

public interface ProDao { // 여러개의 데이터를 받아야 될 경우엔 return을 List로 만들어야한다
	public String getMaxPCode(); // tbl_product table에 p_code 컬럼의 가장 큰 값을 String으로 return
	public List<ProductDTO> selectAll();
	public ProductDTO findById(String p_code);
	public List<ProductDTO> findByName(String p_name);
	public ProductDTO findBySName(String p_name); // 상품정보를 입력할 때 완전히 일치하는 이름을 가진게 있는지 검사
	public int insert(ProductDTO productDTO);
	public int update(ProductDTO productDTO);
	public int delete(String p_code); // 매개변수가 1개일 경우 mapper로 값을 전달시 1개짜리 배열처럼 전달
	// 매개변수가 2개 이상일 경우 @Param() annotation을 반드시 명시해줘야한다. Mapper에선 Param에 있는 ""안에 있는 변수명이 넘어가고 매개변수명은 상관없다
	// public List<ProductDTO> findByPrice(@Param("sprice") int sprice, @Param("eprice") int eprice;
	public List<ProductDTO> findByIPrice(@Param("sprice") int sprice, @Param("eprice") int eprice);
}
