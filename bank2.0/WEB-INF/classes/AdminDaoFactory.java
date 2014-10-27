package com.cx.bank.factory;

import com.cx.bank.dao.AdminDao;
import com.cx.bank.dao.AdminDaoImpl;

// �������ݷ��ʲ��������֧��ͬ���������ݿ⣬�̰߳�ȫ
public class AdminDaoFactory {

	private static AdminDaoFactory instance;
	private AdminDao adminDao;

	private AdminDaoFactory() {
		adminDao = new AdminDaoImpl();
	}

	public static synchronized AdminDaoFactory getInstance() {

		if (instance == null) {
			instance = new AdminDaoFactory();
		}

		return instance;
	}

	public AdminDao createAdminDao() {
		return adminDao;
	}

}
