package com.cx.bank.model;

//ģ�Ͳ㣬���ڴ������
//��ͨ�ͻ�
public class User {

	private String uid;// ����
	private String uname;// ����
	private String upwd;// ����
	private double umoney;// ���

	
	public String getUid() {
		return uid;
	}

	public String getUname() {
		return uname;
	}

	public String getUpwd() {
		return upwd;
	}

	public Double getUmoney() {
		return umoney;
	}

	
	
	public void setUid(String uid) {
		this.uid = uid;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}

	public void setUmoney(double umoney) {
		this.umoney = umoney;
	}

}
