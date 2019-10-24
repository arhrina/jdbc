package com.biz.dbms.config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBConnection {
	private static SqlSessionFactory sqlSessionFactory; // 기본값이 null값
	static {// static은 생성자보다 먼저
		// 파일에 있는 myBatis 초기설정 불러오기
		String configFile = "com/biz/dbms/config/mybatis-config.xml";
		// XPathParser => XML 문법 오류
		InputStream inputStream;
		try {
			// configFile 읽어오기
			inputStream = Resources.getResourceAsStream(configFile);
			
			// sqlSessionFactory를 싱글톤으로 생성하기
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			if(sqlSessionFactory == null) { // 팩토리가 안만들어졌다면
				sqlSessionFactory = builder.build(inputStream); // 파일정보를 가지고 팩토리 생성
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // end static
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
}
