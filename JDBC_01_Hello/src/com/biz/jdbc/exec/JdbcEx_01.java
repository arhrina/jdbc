package com.biz.jdbc.exec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcEx_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("HELLO ORACLE");

		// 이하 오라클에 접속하기 위한 접속설정값 4개

		String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
		// 뒤에 .class가 있으면 불가능. 대소문자 구분 주의
		// 라이브러리에 추가한 jdbc에 jdbc.driver에 oracledriver의 경로
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		// jdbc-oracle-thin 드라이버를 통해 localhost인 host를 통해 1521 port에 xe db명 주소에 접속
		// Data Source Explorer에 생성한 데이터베이스 커넥션 이름에 properties에 가서 주소를 복사
		String userName = "grade";
		String password = "grade";

		try {
			Class.forName(jdbcDriver);
			// jdbcDriver 변수에 있는 것을 JVM에 로드. 외부에 있는 드라이버 관리 클래스
			Connection dbConn = null; // 1개의 프로젝트엔 1개의 커넥션만.
			dbConn = DriverManager.getConnection(url, userName, password);
			// 3개의 문자열 정보(url : 주소, username : 사용자, password : 비밀번호)를 받아
			// 드라이버랑 연결을 생성
			// 이 프로젝트에서 드라이버는 Oracle DBMS 드라이버이므로 oracle dbms와 연결되어 SQL을 통신
			System.out.println("Connection OKAY"); // 여기까지 오라클 드라이버를 위한 영역

			// DBMS에게 SQL을 전송하는 절차가 필요. 이하는 JDBC와 app간의 데이터 교환
			PreparedStatement pStr = null;
			String sql = " SELECT * FROM tbl_score ";
			pStr = dbConn.prepareStatement(sql); // 연결통로를 통해 sql문 전달. pStr변수에 결과를 받음(일종의 버퍼)

			// sql문 실행을 지시하고 SELECT 결과를 rst에 받는다
			ResultSet rst = pStr.executeQuery();
			// rst엔 db로부터 읽어온 List가 ResultSet라는 데이터 타입으로 저장되어 있다. 큐. 선입선출
			// 데이터가 더이상 없을 때 rst.next를 실행하면 false를 return
			// ResultSet은 일종의 Iterator(반복자)처럼 취급할 수 있다
			// rst.next는 실행될 때마다 데이터 리스트의 앞쪽부터 꺼내 해당하는 리스트를 읽을 준비를 한다
			// get은 읽을 준비가 되어있는 현재 리스트의 1번 컬럼(배열, 리스트와 같이 0이 시작이 아닌 1이 시작)을 꺼내라
			// get뒤에 형을 지정해주어야한다. 데이터타입을 모르면 문자열
			while(rst.next()) {
				System.out.print(rst.getString(1)+"\t");
				System.out.print(rst.getString(2)+"\t");
				System.out.print(rst.getString(3)+"\n");
			}
			rst.close();
			dbConn.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}