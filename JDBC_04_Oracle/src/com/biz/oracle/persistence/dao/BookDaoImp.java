package com.biz.oracle.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.oracle.config.DBContract;
import com.biz.oracle.persistence.BookDTO;

public class BookDaoImp extends BookDao {

	@Override
	public List<BookDTO> selectAll() {
		// TODO Auto-generated method stub
		PreparedStatement pStr;
		String sql = DBContract.SQL.SELECT_BOOKS;
		try {
			// sql 문자열을 JDBC driver를 통해 DBMS로 전송하기 위한 데이터형 변환
			pStr = dbConn.prepareStatement(sql);
			// DBMS에 형변환된 sql를 실행하여 결과를 rst에 저장
			ResultSet rst = pStr.executeQuery();
			
			List<BookDTO> bookList = new ArrayList<BookDTO>();
			
			// rst가 1개 이상의 record 이상이면 rst.next()의 값이 true, rst 내부에서 값을 빼낼 수 있는 상태로 변환
			while(rst.next()) {
				BookDTO dto = BookDTO.builder().b_code(
				rst.getString("B_CODE")).b_name(
				rst.getString("B_NAME")).b_writer(
				rst.getString("B_WRITER")).b_comp(
				rst.getString("B_COMP")).b_price(
				rst.getInt("B_PRICE")).build();
				bookList.add(dto);
			}
			rst.close();
			pStr.close();
			return bookList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	} // viewBookList end
	


	@Override
	public BookDTO findById(String b_code) {
		// TODO Auto-generated method stub
		PreparedStatement pStr;
		String sql = DBContract.SQL.SELECT_BOOKS;
		sql += " WHERE b_code = ? ";
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, b_code);
			ResultSet rst = pStr.executeQuery();
			BookDTO bDTO = null;
			if(rst.next()) {
				bDTO = this.rst_2_DTO(rst);
			}
			rst.close();
			pStr.close();
			return bDTO;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	private BookDTO rst_2_DTO(ResultSet rst) throws SQLException {
		BookDTO dto = BookDTO.builder().b_code(
				rst.getString("B_CODE")).b_name(
				rst.getString("B_NAME")).b_writer(
				rst.getString("B_WRITER")).b_comp(
				rst.getString("B_COMP")).b_price(
				rst.getInt("B_PRICE")).build();
		return dto;
	}
	
	@Override
	public List<BookDTO> findByName(String b_name) {
		// TODO Auto-generated method stub
		PreparedStatement pStr;
		String sql = DBContract.SQL.SELECT_BOOKS;
		// sql += " WHERE B_name LIKE '%' + ? + '%' "; MySQL 
		// sql += " WHERE B_name LIKE '%' & ? & '%' "; MSSQL
		sql += " WHERE B_name LIKE '%' || ? || '%' "; // 오라클 문자열 결합방식
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1,  b_name);
			ResultSet rst = pStr.executeQuery();
			List<BookDTO> bookList = new ArrayList<BookDTO>();
			while(rst.next()) {
				BookDTO dto = rst_2_DTO(rst);
				bookList.add(dto);
			}
			rst.close();
			pStr.close();
			return bookList;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<BookDTO> findByComp(String b_comp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookDTO> findByWriter(String b_writer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookDTO> findByPrice(int price) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookDTO> findByPrice(int sPrice, int ePrice) {
		// TODO Auto-generated method stub
		PreparedStatement pStr;
		String sql = DBContract.SQL.SELECT_BOOKS;
		sql += " WHERE b_price BETWEEN ? AND ? ";
		
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setInt(1, sPrice);
			pStr.setInt(2, ePrice);
			ResultSet rst = pStr.executeQuery();
			
			List<BookDTO> bookList = new ArrayList<BookDTO>();
			while(rst.next()) {
				bookList.add(rst_2_DTO(rst));
			}
			rst.close();
			pStr.close();
			return bookList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public int insert(BookDTO bookDTO) {
		// TODO Auto-generated method stub
		PreparedStatement pStr;
		String sql = null;
		sql = " INSERT INTO tbl_books( ";
		sql += " B_CODE," + 
				" B_NAME," + 
				" B_COMP," + 
				" B_WRITER," + 
				" B_PRICE ) ";
		sql += " VALUES( 'B' || LPAD(SEQ_BOOKS.NEXTVAL, 4, '0') , ?, ?, ?, ?) ";
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, bookDTO.getB_name());
			pStr.setString(2, bookDTO.getB_comp());
			pStr.setString(3, bookDTO.getB_writer());
			pStr.setInt(4, bookDTO.getB_price());
			
			int ret = pStr.executeUpdate();
			pStr.close();
			
			return ret;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public int update(BookDTO bookDTO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String b_code) {
		// TODO Auto-generated method stub
		PreparedStatement pStr;
		String sql = " DELETE FROM tbl_books ";
		sql += " WHERE b_code = ? ";
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, b_code);
			int ret = pStr.executeUpdate();
			
			pStr.close();
			return ret;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 0;
	}

}
