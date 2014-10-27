package com.cx.bank.bll;

import java.io.UnsupportedEncodingException;
import java.util.List;
import com.cx.bank.factory.UserDaoFactory;
import com.cx.bank.model.User;

//ҵ���߼��㣬����user������
public class UserManager {
	private static UserManager instance = new UserManager();

	public static UserManager getInstance() {
		return instance;
	}


	// ����
	public User findUserById(String id) {

		return UserDaoFactory.getInstance().createUserDao().findUserById(id);

	}

	// �޸�����
	public boolean modifyUserPwd(String id, String pwd) {

		return UserDaoFactory.getInstance().createUserDao()
				.modifyUserPwd(id, pwd);

	}

	// ��½
	public boolean userLogin(String id, String pwd) {
		return UserDaoFactory.getInstance().createUserDao().userLogin(id, pwd);
	}

	// ��ʾ�����û�
	public List showAllUser() {
		return UserDaoFactory.getInstance().createUserDao().showAllUser();
	}

	// ��ѯ���
	public String findUserMoney(String id) {

		Double m = UserDaoFactory.getInstance().createUserDao()
				.findUserMoney(id);
		if (m != null) {
			String money = String.valueOf(m);
			return money;
		} else {
			return null;
		}
	}

	// ȡ��
	public boolean takeMoney(String id, String money) {

		try {
			Double m = Double.valueOf(money);
			return UserDaoFactory.getInstance().createUserDao()
					.takeMoney(id, m);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		}

	}

	// ���
	public boolean saveMoney(String id, String money) {
		try {
			Double m = Double.valueOf(money);
			return UserDaoFactory.getInstance().createUserDao()
					.saveMoney(id, m);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		}
	}

	// ת��
	public boolean transferMoney(String id1, String id2, String money) {
		try {
			Double m = Double.valueOf(money);
			return UserDaoFactory.getInstance().createUserDao()
					.transferMoney(id1, id2, m);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//ע��
	public boolean registerUser(User u) {
	
		return UserDaoFactory.getInstance().createUserDao().registerUser(u);
	}
	

}
