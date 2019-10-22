package com.biz.addr.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.addr.DBConnection;
import com.biz.addr.persistence.AddrDTO;

public class AddrDao {
	public Connection dbConn;
	private List<AddrDTO> addrList;
	
	
	public AddrDao() {
		dbConn = DBConnection.getDBConnection();
	}
	
	public List<AddrDTO> selectAll() {
		AddrDTO aDTO;
		addrList = new ArrayList<AddrDTO>();
		PreparedStatement p;
		ResultSet r;
		String sql = " SELECT * FROM tbl_addr ";
		try {
			p = dbConn.prepareStatement(sql);
			r = p.executeQuery();
			addrList = new ArrayList<AddrDTO>();
			while(r.next()) {
				aDTO = rst_2_dto(r);
				addrList.add(aDTO);
			}
			r.close();
			p.close();
			return addrList;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<AddrDTO> findById(long id) {
		AddrDTO aDTO;
		String sql = " SELECT * FROM tbl_addr ";
		sql += " WHERE id = ? ";
		addrList = new ArrayList<AddrDTO>();
		PreparedStatement p;
		ResultSet r;
		try {
			p = dbConn.prepareStatement(sql);
			p.setLong(1, id);
			r = p.executeQuery();
			while(r.next()) {
				aDTO = rst_2_dto(r);
				addrList.add(aDTO);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return addrList;
	}
	
	public List<AddrDTO> findByName(String name) {
		AddrDTO aDTO;
		String sql = " SELECT * FROM tbl_addr ";
		sql += " WHERE name = ? ";
		addrList = new ArrayList<AddrDTO>();
		PreparedStatement p;
		ResultSet r;
		try {
			p = dbConn.prepareStatement(sql);
			p.setString(1, name);
			r = p.executeQuery();
			while(r.next()) {
				aDTO = rst_2_dto(r);
				addrList.add(aDTO);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return addrList;
	}
	
	public List<AddrDTO> findByTel(String tel) {
		AddrDTO aDTO;
		String sql = " SELECT * FROM tbl_addr ";
		sql += " WHERE tel = ? ";
		addrList = new ArrayList<AddrDTO>();
		PreparedStatement p;
		ResultSet r;
		try {
			p = dbConn.prepareStatement(sql);
			p.setString(1, tel);
			r = p.executeQuery();
			while(r.next()) {
				aDTO = rst_2_dto(r);
				addrList.add(aDTO);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return addrList;
	}
	
	public List<AddrDTO> findByRelat(String rel) {
		AddrDTO aDTO;
		String sql = " SELECT * FROM tbl_addr ";
		sql += " WHERE relat = ? ";
		addrList = new ArrayList<AddrDTO>();
		PreparedStatement p;
		ResultSet r;
		try {
			p = dbConn.prepareStatement(sql);
			p.setString(1, rel);
			r = p.executeQuery();
			while(r.next()) {
				aDTO = rst_2_dto(r);
				addrList.add(aDTO);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return addrList;
	}
	
	private AddrDTO rst_2_dto(ResultSet r) throws SQLException {
		AddrDTO aDTO = AddrDTO.builder()
		.id(r.getInt("ID"))
		.name(r.getString("NAME"))
		.tel(r.getString("TEL"))
		.addr(r.getString("ADDR"))
		.relat(r.getString("RELAT"))
		.build();
		return aDTO;
	}

	public int insertData() {
		int ret = 0;
		// TODO Auto-generated method stub
		return ret;
	}

	public int deleteData() {
		// TODO Auto-generated method stub
		int ret = 0;
		return ret;
		
	}

	public int updateData() {
		// TODO Auto-generated method stub
		int ret = 0;
		return ret;		
	}
}