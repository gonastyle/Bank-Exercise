package com.cx.bank.bll;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.cx.bank.factory.AdminDaoFactory;
import com.cx.bank.model.Admin;

//ҵ���߼��㣬����Admin�����ݣ�ʹ�õ���������ģʽ��
public class AdminManager {

	private static AdminManager instance = new AdminManager();

	public static AdminManager getInstance() {
		return instance;
	}


	//����
	public Admin findAdminById(String id) {

		return AdminDaoFactory.getInstance().createAdminDao().findAdminById(id);

	}

	//�޸�����
	public boolean modifyAdminPwd(String id, String pwd) {

		return AdminDaoFactory.getInstance().createAdminDao()
				.modifyAdminPwd(id, pwd);

	}

	//��½
	public boolean adminLogin(String id, String pwd) {
		return AdminDaoFactory.getInstance().createAdminDao()
				.adminLogin(id, pwd);
	}
	
	//��ʾ���й���Ա
	public List showAllAdmin(){
		return AdminDaoFactory.getInstance().createAdminDao().showAllAdmin();
	}

}
