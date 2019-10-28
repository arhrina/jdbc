package com.biz.iolist.config.service.pro;

import java.util.List;

import com.biz.iolist.persistence.ProductDTO;

public class ProductServiceV2 extends ProductServiceV1 {

	public void searchPName() {
		System.out.print("검색할 상품명(Enter:전체) >> ");
		String strName = s.nextLine();

		List<ProductDTO> proList = null;
		if (strName.trim().isEmpty()) {
			proList = pDao.selectAll();
		} else {
			System.out.println("findByName 실행전");
			proList = pDao.findByName(strName);
			System.out.println("findByName 실행후");
		}
		for (ProductDTO dto : proList) {
			System.out.println(dto.toString());
		}
	}
}