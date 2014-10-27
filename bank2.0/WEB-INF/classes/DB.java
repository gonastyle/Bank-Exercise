package com.cx.bank.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//���ݿ��������
public final class DB {

	
	private static DBConnManager dbm = new DBConnManager();
	private static String name = "mysql";
	
	private DB(){
		
	}
	// �����ӳػ�����ӣ�
	public static Connection getConn() {

		return dbm.getConnection(name);
	}

	//�ͷŵ�������
	public static void closeConn(Connection conn) {

		dbm.releaseConnection("mysql", conn);
	}
	
	//�ر����ӳ���������
	public static void closeConn() {

		dbm.closeConns();
	}

	public static void closeStmt(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void closeRs(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

}
