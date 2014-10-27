package com.cx.bank.dao;

import java.util.List;

import com.cx.bank.model.User;

public interface UserDao {

	// �����Ƿ�������,û�з��ؿա�
	public User findUserById(String id);

	// ����user��ֵ�ж��Ƿ�Ϊ�Ϸ��û�
	public boolean userLogin(String id, String pwd);

	// ����id�����޸Ķ�Ӧ��Admin����
	public boolean modifyUserPwd(String id, String pwd);

	// ���������û�
	public List showAllUser();
	
	//�û�ע��
	public boolean registerUser(User u);

	// ��ѯ���
	public Double findUserMoney(String id);

	// ȡ��
	public boolean takeMoney(String id, Double money);

	// ���
	public boolean saveMoney(String id, Double money);

	// ת��
	public boolean transferMoney(String id1, String id2, Double money);
}
