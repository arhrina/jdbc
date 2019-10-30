package com.biz.iolist.exec;

import java.util.Scanner;

public class Calender {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		System.out.print("보고 싶은 달력의 연도 >> ");
		int year = Integer.valueOf(s.nextLine());
		System.out.print("보고 싶은 달력의 월 >> ");
		int month = Integer.valueOf(s.nextLine());
		System.out.println("입력된 년과 월은 " + year + "년" + month + "월입니다");
		int count;
		count = year / 4; // 윤달이 있는 년도는 하루씩 더 더해서 일주일을 밀어버려야
		System.out.println("Sun\tMon\tTat\tWed\tThur\tFri\tSat");
		/* if(month == 1 || 3 || 5 || 7 || 8 || 10 || 12)
		{
			
		}
		*/
	}

}
