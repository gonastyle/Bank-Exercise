package com.cx.bank.view;

import java.util.Scanner;

import com.cx.bank.bll.UserManage;
import com.cx.bank.model.User;

public class BankTest {

	public static void main(String[] args) {
		
		LoginView l=new LoginView();

	}

}

class LoginView{
	
	UserManage um = UserManage.getInstance();
	User u=new User();
	Scanner s=new Scanner(System.in);
	public LoginView(){
		
	   choose();
	   
		
	}

	public void choose() {
		
		showLogin();
		int c=s.nextInt();
		while(true){
			switch(c){
			case 1: 
				verifyLogin();
			break;
			
			case 2: 
				register();
			break;
			
			case 3: System.exit(0);break;
			default: System.out.println("����������");break;
			}
			break;
		}
		choose();
	}
	
	public void register(){
		System.out.println("��ӭע���˺�");
		System.out.println("����������˺ţ�");
		String id=s.next();
		System.out.println("������������룺");
		String pwd=s.next();
		if(um.register(id, pwd)){
			System.out.println("ע��ɹ���");
		}else{
			System.out.println("ע��ʧ�ܣ��û����ظ���");
		}
		
		
	}

	//��½����
	public void showLogin() {
		
		System.out.println(" ���й���ϵͳDos�� ");
		System.out.println("   1.��½");
		System.out.println("   2.ע��");
		System.out.println("   3.�˳�");
		System.out.println("������ѡ�1-3");
		
	}
	//��������
	public void showMain(){
		
		System.out.println(" �û���������        ");
		System.out.println("   1.��ѯ");
		System.out.println("   2.���");
		System.out.println("   3.ȡ��");
		System.out.println("   4.ת��");
		System.out.println("   5���˳�");
		System.out.println("������ѡ�1-5");
		
	}
	
	public void verifyLogin(){
		String id;
		String pwd;
		System.out.println("�������û�����");
		id=s.next();
		System.out.println("���������룺��");
		pwd=s.next();
		
		u.setId(id);
		u.setPwd(pwd);
		
		if(um.login(id, pwd)){
			System.out.println(id+"��½�ɹ�");
			//���浱ǰ�û�����Ϣ	
			dealMoney();
			
		}else{
			System.out.println("��½ʧ��//�û������������://�����µ�½");
			choose();
			
		}
		
			
	}
	
	public void dealMoney(){
		showMain();
		int c=s.nextInt();
		while(true){
			
			switch(c){
			case 1: 
				selectMoney();
			break;
			
			case 2: 
				saveMoney();
			break;
			case 3: 
				takeMoney();
				break;
			case 4: 
				transferMoney();
				break;
			case 5: 
			System.out.println("ϵͳ�����˳���");
			System.exit(0); 
			break;
			default: System.out.println("����������");break;
			}
			
			break;
		}
		dealMoney();
	}
	
	public void selectMoney(){
		 System.out.println("������Ϊ:"+um.getUser().getMoney());
		
	}
	
   public void takeMoney(){
	   System.out.println("������ȡ���");
	   String money=s.next();
	   if(um.takeMoney(money)){
		   System.out.println("ȡ��ɹ���");
	   }else{
		   System.out.println("ȡ��ʧ�ܣ�");
	   }
	   
		
	}
   public void saveMoney(){
	   System.out.println("���������");
	   String money=s.next();
	   if(um.saveMoney(money)){
		   System.out.println("���ɹ���");
	   }else{
		   System.out.println("���ʧ�ܣ�");
	   }
   }
   public void transferMoney(){
	
	   System.out.println("������ת�˺�:");
	   String id=s.next();
	   System.out.println("������ת�˽�");
	   String money=s.next();
	   if(um.transferMoney(id,money)){
		   System.out.println("ת�˳ɹ���");
	   }else{
		   System.out.println("ת��ʧ�ܣ�");
	   }
   }
	
}





