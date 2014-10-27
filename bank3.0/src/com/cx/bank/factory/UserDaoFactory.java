package com.cx.bank.factory;

import com.cx.bank.dao.UserDao;
import com.cx.bank.dao.UserDaoImpl;

//�������ݷ��ʲ��������֧��ͬ���������ݿ⣬�̰߳�ȫ
public class UserDaoFactory {

	private static UserDaoFactory instance;
	private UserDao userDao;

	private UserDaoFactory() {
		userDao = new UserDaoImpl();
	}

	public static synchronized UserDaoFactory getInstance() {

		if (instance == null) {
			instance = new UserDaoFactory();
		}

		return instance;
	}

	public UserDao createUserDao() {
		return userDao;
	}

}
