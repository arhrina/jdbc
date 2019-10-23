package com.biz.addr.exec;

import com.biz.addr.service.AddrServiceV1;

public class AddrEx_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AddrServiceV1 ad = new AddrServiceV1();
		ad.viewAll();
		ad.searchAddressById();
		ad.insertData();
		ad.updateData();
		ad.deleteData();
	}
}
