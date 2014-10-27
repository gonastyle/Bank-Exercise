package com.cx.bank.bll;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import com.cx.bank.filter.HibernateFilter;
import com.cx.bank.model.User;


//ҵ���߼��㣬����user������
public class UserManager {
	
	//����ģʽ
	private static UserManager instance = new UserManager();

	public static UserManager getInstance() {
		return instance;
	}
	
	private UserManager(){
		
	}
	
	
	// ����
	public User findUserById(String id) {

		Session session = null;
		User u = null;

		session = HibernateFilter.getSession();
		session.beginTransaction();
		//��ʹ��������
		u = (User) session.get(User.class, id);
		
		session.getTransaction().commit();
		
		return u;
	}

	// �޸�����
	public boolean modifyUserPwd(String id, String pwd) {

		Session session = null;
		try {
			session = HibernateFilter.getSession();
			session.beginTransaction();
			session.createQuery("update User u set u.upwd=? where u.uid=?")
					.setParameter(0, pwd).setParameter(1, id).executeUpdate();

			session.getTransaction().commit();
		} catch (Exception e) {
			// ��¼��־,log4j��......
			e.printStackTrace();
			session.getTransaction().rollback();

			return false;
		}

		return true;
	}

	// ��½
	public boolean userLogin(String id, String pwd) {

		Session session = null;

		session = HibernateFilter.getSession();
		session.beginTransaction();
		// ��ѯ�Ƿ���������¼��
		Query user = session.createQuery(
				"select count(*) from User u where uid=? and upwd=?")
				.setParameter(0, id).setParameter(1, pwd);
		Long count = (Long) user.uniqueResult();
		
		session.getTransaction().commit();
		//session.close();

		if (count != 0) {
			return true;
		} else {
			return false;
		}

	}

	// ��ʾ�����û�
	public List showAllUser() {

		Session session = null;
		List u = null;

		session = HibernateFilter.getSession();
		session.beginTransaction();

		u = session.createQuery("from User").list();
		session.getTransaction().commit();
		//session.close();
		return u;
	}

	// ��ѯ���
	public String findUserMoney(String id) {

		Session session = null;
		session = HibernateFilter.getSession();
		
		session.beginTransaction();
		//��ʹ��������
		User u = (User) session.get(User.class, id);
		
		session.getTransaction().commit();

        if(u!=null){
		Double m = u.getUmoney();
		String money = String.valueOf(m);
		return money;
		}else{
			return null;
		}
        
	}

	// ȡ��
	public boolean takeMoney(String id, String money) {

		Double curr = Double.valueOf(findUserMoney(id));
		Double m = Double.valueOf(money);
		//�ж�����Ƿ���
		if (curr != null&&curr>m) {
			Session session = null;
			m=curr-m;
			try {
				session = HibernateFilter.getSession();
				session.beginTransaction();
				session.createQuery(
						"update User u set u.umoney=? where u.uid=?")
						.setParameter(0, m).setParameter(1, id).executeUpdate();

				session.getTransaction().commit();

			} catch (NumberFormatException e) {
				e.printStackTrace();
				return false;
			} catch (Exception e) {
				// ��¼��־,log4j��......
				e.printStackTrace();
				session.getTransaction().rollback();
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
			Session session = null;
			//�������
			m=curr+m;
			try {
				session = HibernateFilter.getSession();
				session.beginTransaction();
				session.createQuery(
						"update User u set u.umoney=? where u.uid=?")
						.setParameter(0, m).setParameter(1, id).executeUpdate();

				session.getTransaction().commit();

			} catch (NumberFormatException e) {
				e.printStackTrace();
				return false;
			} catch (Exception e) {
				// ��¼��־,log4j��......
				e.printStackTrace();
				session.getTransaction().rollback();
				return false;
			} 
			
			return true;
		
	}

	// ת��
	public boolean transferMoney(String id1, String id2, String money) {
		
		if(takeMoney(id1,money)&&saveMoney(id2,money)){
			return true;
		}
		return false;
		
	}

	// ע��
	public boolean registerUser(User u) {

		Session session=null;
		try{
			session = HibernateFilter.getSession();
		session.beginTransaction();
		
		session.save(u);
		
		session.getTransaction().commit();
		
		}catch(Exception e){
			session.getTransaction().rollback();
			return false;
		}
		
		return true;
	}

}
