package com.cx.bank.model;

//ģ�Ͳ㣬���ڴ������
/*
���ݸ�ʽ����
10,10,6//admin
20,10,6,��11,2��//user
*/

public class Admin {

	private String aid;// ����
	private String aname;// ����
	private String apwd;// ����

	public String getAid() {
		return aid;
	}

	public String getAname() {
		return aname;
	}

	public String getApwd() {
		return apwd;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public void setApwd(String apwd) {
		this.apwd = apwd;
	}

}
