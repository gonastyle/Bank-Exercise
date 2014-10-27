package com.cx.bank.dal;

import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

import com.cx.bank.model.User;

//���ݷ��ʲ��������
public class UserService {
	
	public User getUserById(String id) {

		Properties props = new Properties();
		User user = new User();
		File f = new File("./file/" + id + ".properties");
		if (f.exists()) {
			try {
				FileInputStream in = new FileInputStream(f);
				props.load(in);
				in.close();
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
		} else {
			user = null;
			return user;
		}

		user.setId("id");
		user.setName(props.getProperty("name"));
		user.setPwd(props.getProperty("pwd"));
		user.setMoney(props.getProperty("money"));
		
		return user;

	}

	//ע��
	public boolean Register(String id, String pwd) {

		Properties props = new Properties();
	    User user = new User();
	    File dir=new File("./file/");
	    if(!dir.exists()){
	    dir.mkdir();
	    }
		File f = new File("./file/" + id + ".properties");
		
		if (f.exists()) {
			return false;
		} else {
			try {
				f.createNewFile();
				FileOutputStream out = new FileOutputStream("./file/" + id
						+ ".properties");

				props.setProperty("id", id);
				props.setProperty("pwd", pwd);
				props.setProperty("name", "");
				props.setProperty("money", "0");
				props.store(out, "./file/" + id + ".properties");
				out.close();

			} catch (FileNotFoundException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}

			return true;
		}

	}
	
    //ȡ��
	public boolean take(User user, String money) {
		
		int count = 0;
		try {
			count = Integer.parseInt(user.getMoney()) - Integer.parseInt(money);
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		Properties props = new Properties();
		File f = new File("./file/" + user.getId() + ".properties");
		if(count>=0){
		if (f.exists()) {
			try {
				FileInputStream in = new FileInputStream(f);
				props.load(in);
				in.close();
				props.setProperty("money", Integer.toString(count));
				FileOutputStream out = new FileOutputStream("./file/" + user.getId() + ".properties");
				props.store(out, "./file/" + user.getId() + ".properties");
			} catch (IOException e) {

				e.printStackTrace();
			}
			user.setMoney(Integer.toString(count));
			return true;
		} else {
			return false;
		}
		}else{
			JOptionPane.showMessageDialog(null, "����", "��ʾ",
					JOptionPane.OK_OPTION);
			return false;
		}
			

	}

	//���
	public boolean save(User user, String money) {
		int count = 0;
		try {
			count = Integer.parseInt(user.getMoney()) + Integer.parseInt(money);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		Properties props = new Properties();
		File f = new File("./file/" + user.getId() + ".properties");
		if (f.exists()) {
			try {
				FileInputStream in = new FileInputStream(f);
				props.load(in);
				in.close();
				props.setProperty("money", Integer.toString(count));
				FileOutputStream out = new FileOutputStream("./file/" + user.getId() + ".properties");
				props.store(out, "./file/" + user.getId() + ".properties");
			} catch (IOException e) {

				e.printStackTrace();
			}
			user.setMoney(Integer.toString(count));
			return true;
		} else {
			return false;
		}
	}
	//ת��
	public boolean transfer(User user,User other, String money){
		
		if(Integer.parseInt(user.getMoney())>Integer.parseInt(money)){
		this.take(user,money);
		this.save(other, money);
		
		return true;
		}else{
			JOptionPane.showMessageDialog(null, "����", "��ʾ",
					JOptionPane.OK_OPTION);
			return false;
		}
		
		
	}
}
