package com.biz.iolist.exec;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateFormatCheck {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		try {
			sd.parse("20190101");
			// 안드로이드에서 써먹는다. 입력하는 날짜형식을 지정하고 싶을 때
			// 입력하는 사람이 지정된 형식대로 입력하지 않으면 catch로 넘어간다
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("날짜형식이 잘못되었습니다");
		}
	}
}
