package com.cx.bank.util;

import java.sql.*;
import java.util.*;

/*���ӳ���.�ܹ�����Ҫ�󴴽�������,ֱ�����������Ϊֹ.*/
public class DBConnPool {
	// ʵ��ʹ���е�������
	private int inUse = 0;
	// ��������
	private Vector connections = new Vector();
	// ���ӳ���
	private String poolname;
	// ���ݿ��ʶ
	private String dbid;
	// ����������
	private String drivername;
	// ���ݿ��˺�
	private String username;
	// ���ݿ�����
	private String passwd;
	// ���������
	private int maxconn;

	public DBConnPool(String poolname, String drivername, String dbid,
			String username, String passwd, int maxconn) {
		this.poolname = poolname;
		this.dbid = dbid;
		this.drivername = drivername;
		this.username = username;
		this.passwd = passwd;
		this.maxconn = maxconn;
	}

	/* �����ӷ��ظ����ӳ� */
	public synchronized void releaseConnection(Connection con) {
		// ��ָ�����Ӽ��뵽����ĩβ
		connections.addElement(con);
		// ��������һ
		inUse--;
	}

	/* �����ӳصõ�һ������ */
	public synchronized Connection getConnection() {
		Connection con = null;
		if (connections.size() > 0) {
			// ��ȡ�����б��л�õ�һ������
			con = (Connection) connections.elementAt(0);
			connections.removeElementAt(0);
			// ����������ѹرգ��������ȡ
			try {
				if (con.isClosed())
					con = getConnection();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
		// ���ʵ��ʹ�õ�����С����������������´���һ������
		else if (maxconn == 0 || inUse < maxconn) {
			con = newConnection();
		}
		
		if (con != null) {
			// ��������һ
			inUse++;
		}
		// ����һ������
		return con;
	}

	/* �����µ����� */
	private Connection newConnection() {
		Connection con = null;
		try {
			// ������������
			Class.forName(drivername);
			// ��������
			con = DriverManager.getConnection(dbid, username, passwd);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		// ���ظ�����
		return con;
	}

	/* �ر��������� */
	public synchronized void closeConn() {
		Enumeration allConnections = connections.elements();
		while (allConnections.hasMoreElements()) {
			Connection con = (Connection) allConnections.nextElement();
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		connections.removeAllElements();
	}
}