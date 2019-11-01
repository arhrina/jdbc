package com.callor.memo.exec;

import java.io.IOException;

import com.callor.memo.service.MemoServiceV1;

public class MemoEx_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MemoServiceV1 memo = new MemoServiceV1();
		try {
			memo.menu();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
