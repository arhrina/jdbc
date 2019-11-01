package com.biz.rent.exec;

import java.io.IOException;
import java.util.Date;

import com.biz.rent.service.RentServiceV1;

public class RentEx_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date today = new Date(System.currentTimeMillis());
		RentServiceV1 rs = new RentServiceV1();
		try {
			rs.menu();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
