package com.cx.bank.dao;

import java.util.List;

import com.cx.bank.model.Admin;

public interface AdminDao {

	// ����Ա��id�š������Ƿ�������,û�з��ؿա�
	public Admin findAdminById(String id);

	// ����admin��ֵ�ж��Ƿ�Ϊ�Ϸ��û�
	public boolean adminLogin(String id, String pwd);

	//����id�����޸Ķ�Ӧ��Admin����
	public boolean modifyAdminPwd(String id,String pwd);
	
	//��ʾ���й���Ա
	public List showAllAdmin();
	
	
	

}
