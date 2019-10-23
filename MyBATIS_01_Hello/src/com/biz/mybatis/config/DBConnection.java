package com.biz.mybatis.config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBConnection {
	private static SqlSessionFactory sqlSessionFactory;
	/*
	 * SqlSession을 필요에 따라 생성, 삭제 등을 관리할 클래스
	 * 
	 * static생성 => Single tone으로 활용
	 */
	static {
		String configFile = "com/biz/mybatis/config/mybatis-config.xml";
		// configFile을 읽어서 FactoryBuilder 생성
		try {
			InputStream inputStream = Resources.getResourceAsStream(configFile);
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			if(sqlSessionFactory == null) { // null이면 sqlSessionFactory에 팩토리빌더로 빌드
				sqlSessionFactory = builder.build(inputStream);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
}