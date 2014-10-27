package com.cx.bank.bll;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cx.bank.model.User;

//ҵ���߼��㣬����user������
public class UserManagerImpl extends HibernateDaoSupport implements UserManager{

	// ����
	public User findUserById(String id) {
		
		User u = (User) this.getSession().get(User.class, id);
		
		return u;
	}

	// �޸�����
	public boolean modifyUserPwd(String id, String pwd) {

		try {
			this.getSession().createQuery(
					"update User u set u.upwd=? where u.uid=?").setParameter(0,
					pwd).setParameter(1, id).executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}

		return true;
	}

	// ��½
	public boolean userLogin(String id, String pwd) {

		// ��ѯ�Ƿ���������¼��
		Query user = this.getSession().createQuery(
				"select count(*) from User u where uid=? and upwd=?")
				.setParameter(0, id).setParameter(1, pwd);
		Long count = (Long) user.uniqueResult();

		if (count != 0) {
			return true;
		} else {
			return false;
		}

	}

	// ��ѯ���
	public String findUserMoney(String id) {

		// ��ʹ��������
		User u = (User) this.getSession().get(User.class, id);
		if (u != null) {
			Double m = u.getUmoney();
			String money = String.valueOf(m);
			return money;
		} else {
			return null;
		}

	}

	// ȡ��
	public boolean takeMoney(String id, String money) {

		Double curr = Double.valueOf(findUserMoney(id));
		Double m = Double.valueOf(money);
		// �ж�����Ƿ���
		if (curr != null && curr > m) {
			m = curr - m;
			try {
				this.getSession().createQuery(
						"update User u set u.umoney=? where u.uid=?")
						.setParameter(0, m).setParameter(1, id).executeUpdate();

			} catch (NumberFormatException e) {
				e.printStackTrace();
				return false;
			} catch (Exception e) {
				// ��¼��־,log4j��......
				e.printStackTrace();
				return false;
			}

			return true;
		} else {
			return false;
		}

	}

	// ���
	public boolean saveMoney(String id, String money) {

		Double curr = Double.valueOf(findUserMoney(id));
		Double m = Double.valueOf(money);

		// �������
		m = curr + m;
		try {
			this.getSession().createQuery(
					"update User u set u.umoney=? where u.uid=?").setParameter(
					0, m).setParameter(1, id).executeUpdate();
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			
			return false;
		}

		return true;

	}

	// ת��
	public boolean transferMoney(String id1, String id2, String money) {

		if (takeMoney(id1, money) && saveMoney(id2, money)) {
			return true;
		}
		return false;

	}

	// ע��
	public boolean registerUser(User u) {

		try {

			this.getSession().save(u);

		} catch (Exception e) {

			return false;
		}

		return true;
	}

}
