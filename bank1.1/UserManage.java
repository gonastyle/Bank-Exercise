package com.cx.bank.bll;

import javax.swing.JOptionPane;

import com.cx.bank.dal.UserService;
import com.cx.bank.model.User;

public class UserManage {

	private UserService us = new UserService(); // ʹ�����ݷ��ʲ�Ķ���
	//�����ѵ�½���û�
	private User user;
	private static UserManage instance = null;

	private UserManage() {
	}

	public static UserManage getInstance() {

		if (instance == null) {

			instance = new UserManage();
			return instance;
		}

		return instance;

	}

	// ��½-�߼���
	public boolean login(String id, String pwd) {

		user = us.getUserById(id);

		if (user == null) {

			return false;
		}

		if (user.getPwd().equals(pwd)) {

			return true;

		} else {

			return false;
		}

	}

	// ע�����û�-�߼���
	public boolean register(String id, String pwd) {

		if (us.Register(id, pwd)) {
			return true;
		} else {
			return false;
		}
	}

	public User getUser() {
		return this.user;
	}

	public UserService getUserService() {
		return this.us;
	}

	// ȡ��-�߼���
	public boolean takeMoney(String money) {

		if (us.take(UserManage.getInstance().getUser(), money)) {
			return true;
		} else {
			return false;
		}

	}

	// ���-�߼���
	public boolean saveMoney(String money) {

		if (us.save(UserManage.getInstance().getUser(), money)) {
			return true;
		} else {
			return false;
		}
	}

	// ת��-�߼���
	public boolean transferMoney(String id, String money) {

		User other = us.getUserById(id);
		String currid=this.user.getId();

		if(currid.equals(id)){
			JOptionPane.showMessageDialog(null, "����ת���Լ�", "��ʾ",JOptionPane.OK_OPTION);
			return false;
		}
		if (other == null) {
			JOptionPane.showMessageDialog(null, "�û�������", "��ʾ",JOptionPane.OK_OPTION);
			return false;
		}

		if (us.transfer(UserManage.getInstance().getUser(),other, money)) {
			return true;
		} else {
			return false;
		}

	}

}
