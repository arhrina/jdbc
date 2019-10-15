package com.biz.jdbc.service;

public class DBConstract {
	// 기본형 public static final
	public static class DB_INFO {
		public final static String JdbcDriver = "oracle.jdbc.driver.OracleDriver";
		public final static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
		public final static String USER = "grade";
		public final static String PASSWORD = "grade";
	}

	public static class SCORE {
		final public static int S_ID = 1;
		final public static int S_STD = 2;
		final public static int S_SCORE = 3;
		final public static int S_REM = 4;
	}
}
