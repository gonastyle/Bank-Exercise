package com.cx.bank.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.cx.bank.bll.UserManage;
import com.cx.bank.dal.UserService;
import com.cx.bank.model.User;

public class BankTest {

	public static void main(String[] args) {

		new LoginFrame();
	}

}
		/*        
			 * Model-1(��ʾ������Ʋ�û�з���)
			 * ���д��嶼��һ������һ��UserMange����
		 */

class LoginFrame extends JFrame implements ActionListener {

	JLabel nameLable = new JLabel("��   �ţ�");
	JTextField nameText = new JTextField(20);

	JLabel pwdLabel = new JLabel("��    �룺");
	JPasswordField pwdText = new JPasswordField(20);

	JButton login = new JButton("��½");
	JButton register = new JButton("ע��");

	JPanel centerPane = new JPanel();
	JPanel southPane = new JPanel();

	UserManage um = UserManage.getInstance();

	public LoginFrame() {

		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());

		c.add(centerPane, "Center");
		c.add(southPane, "South");

		centerPane.add(nameLable);
		centerPane.add(nameText);
		centerPane.add(pwdLabel);
		centerPane.add(pwdText);
		southPane.add(login);
		southPane.add(register);

		login.addActionListener(this);
		register.addActionListener(this);

		this.setTitle("���й���ϵͳSwing�汾");
		this.setSize(320, 150);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void actionPerformed(ActionEvent e) {

		User user = new User();
		user.setId(this.nameText.getText());
		user.setPwd(this.pwdText.getText());

		System.out.println(user.getId() + user.getName() + user.getPwd()
				+ user.getMoney());

		if (e.getSource() == login) {

			if (um.login(user.getId(), user.getPwd())) {

				new UserInfoFrame();
				this.dispose();
			} else {

				JOptionPane.showMessageDialog(this, "���������˺Ų�����", "����",
						JOptionPane.ERROR_MESSAGE);
			}

		} else if (e.getSource() == register) {

			new RegisterFrame();
			this.dispose();
		}

	}

}

class RegisterFrame extends JFrame implements ActionListener {

	JLabel nameLable = new JLabel("�������˺ţ�");
	JTextField nameText = new JTextField(20);

	JLabel pwdLabel = new JLabel("�� �� �� �� �룺");
	JPasswordField pwdText = new JPasswordField(20);

	JLabel confirmLabel = new JLabel("ȷ  ��  ��   �� ��");
	JPasswordField confirmText = new JPasswordField(20);

	JButton post = new JButton("�ύ");

	JPanel centerPane = new JPanel();
	JPanel southPane = new JPanel();

	UserManage um = UserManage.getInstance();

	public RegisterFrame() {

		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());

		c.add(centerPane, "Center");
		c.add(southPane, "South");

		centerPane.add(nameLable);
		centerPane.add(nameText);
		centerPane.add(pwdLabel);
		centerPane.add(pwdText);
		centerPane.add(confirmLabel);
		centerPane.add(confirmText);

		southPane.add(post);

		post.addActionListener(this);

		this.setTitle("ע�����");
		this.setSize(360, 170);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void actionPerformed(ActionEvent e) {

		if (this.pwdText.getText().equals(this.confirmText.getText())) {

			boolean is = this.um.register(this.nameText.getText(),
					this.pwdText.getText());
			if (is) {
				JOptionPane.showMessageDialog(this, "ע��ɹ�", "��ʾ",
						JOptionPane.OK_OPTION);
			} else {
				JOptionPane.showMessageDialog(this, "�û��Ѵ��ڻ�ͬ��", "ע��ʧ��",
						JOptionPane.OK_OPTION);
			}

			this.dispose();
			new LoginFrame();

		} else {

			JOptionPane.showMessageDialog(this, "���ε��������벻һ��", "����",
					JOptionPane.ERROR_MESSAGE);
		}

	}

}

class UserInfoFrame extends JFrame implements ActionListener {

	JLabel nameLable = new JLabel("�˺ţ�");
	JLabel nameText = new JLabel("");

	JLabel moneyLabel = new JLabel("��");
	static JLabel moneyText = new JLabel();

	JButton transfer = new JButton("ת��");
	JButton save = new JButton("���");
	JButton take = new JButton("ȡ��");
	JButton back = new JButton("����");

	JPanel NorthPane = new JPanel();
	JPanel centerPane = new JPanel();
	JPanel southPane = new JPanel();

	UserManage um = UserManage.getInstance();

	public UserInfoFrame() {

		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());

		c.add(NorthPane, "North");
		c.add(centerPane, "Center");
		c.add(southPane, "South");

		NorthPane.add(nameLable);
		NorthPane.add(nameText);
		centerPane.add(moneyLabel);
		centerPane.add(moneyText);

		southPane.add(transfer);
		southPane.add(save);
		southPane.add(take);
		southPane.add(back);

		transfer.addActionListener(this);
		save.addActionListener(this);
		take.addActionListener(this);
		back.addActionListener(this);

		this.setTitle("�û���Ϣ����");
		this.setSize(360, 170);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.moneyText.setText(um.getUser().getMoney());
		this.nameText.setText(um.getUser().getId());

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == transfer) {

			new TransferJFrame();

		} else if (e.getSource() == save) {

			new SaveFrame();
		} else if (e.getSource() == take) {

			new TakeFrame();
		}else if (e.getSource() == back) {

			this.dispose();
			new LoginFrame();
		}
	}

}
//ת��
class TransferJFrame extends JFrame implements ActionListener {

	JLabel transferLable = new JLabel("ת���˻��ţ�");
	JTextField transferText = new JTextField(20);

	JLabel moneyLable = new JLabel("ת �� �� �");
	JTextField moneyText = new JTextField(20);

	JButton confirm = new JButton("ȷ��");

	JPanel centerPane = new JPanel();
	JPanel southPane = new JPanel();
	UserManage um = UserManage.getInstance();

	public TransferJFrame() {

		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());

		c.add(centerPane, "Center");
		c.add(southPane, "South");

		centerPane.add(transferLable);
		centerPane.add(transferText);
		centerPane.add(moneyLable);
		centerPane.add(moneyText);

		southPane.add(confirm);

		confirm.addActionListener(this);

		this.setTitle("ת�˽���");
		this.setSize(360, 170);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		boolean flag=true;
		if (this.moneyText.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "���Ϊ��", "��ʾ",
					JOptionPane.OK_OPTION);
		} else {
			for(int i=0;i<this.moneyText.getText().length();i++){
				if(!(47<this.moneyText.getText().charAt(i)&&this.moneyText.getText().charAt(i)<58)){
					JOptionPane.showMessageDialog(this, "����ʽ����", "��ʾ",JOptionPane.OK_OPTION);
					flag=false;
					break;
				}
			}
			if(flag&&!um.transferMoney(this.transferText.getText(),this.moneyText.getText())){
				//JOptionPane.showMessageDialog(this, "�ڲ�����", "��ʾ",JOptionPane.OK_OPTION);
			}else{
				if(flag)
				JOptionPane.showMessageDialog(this,"ת�˳ɹ�", "��ʾ",JOptionPane.OK_OPTION);
				
				UserInfoFrame.moneyText.setText(um.getUserService().getUserById(um.getUser().getId()).getMoney());
			}
		}
		

		this.dispose();
	}

}

class SaveFrame extends JFrame implements ActionListener {

	JLabel moneyLable = new JLabel("���������");
	JTextField moneyText = new JTextField(20);

	JButton confirm = new JButton("ȷ��");

	JPanel centerPane = new JPanel();
	JPanel southPane = new JPanel();
	UserManage um = UserManage.getInstance();
	public SaveFrame() {

		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());

		c.add(centerPane, "Center");
		c.add(southPane, "South");

		centerPane.add(moneyLable);
		centerPane.add(moneyText);

		southPane.add(confirm);

		confirm.addActionListener(this);

		this.setTitle("������");
		this.setSize(360, 120);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
         boolean flag=true;
		if (this.moneyText.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "���Ϊ��", "��ʾ",
					JOptionPane.OK_OPTION);
		} else {
			for(int i=0;i<this.moneyText.getText().length();i++){
				if(!(47<this.moneyText.getText().charAt(i)&&this.moneyText.getText().charAt(i)<58)){
					JOptionPane.showMessageDialog(this, "����ʽ����", "��ʾ",JOptionPane.OK_OPTION);
					flag=false;
					break;
				}
			}
			
			if(flag&&!um.saveMoney(this.moneyText.getText())){
			//	JOptionPane.showMessageDialog(this, "�ڲ�����", "��ʾ",JOptionPane.OK_OPTION);
			}else{
				if(flag)
				JOptionPane.showMessageDialog(this,"���ɹ�", "��ʾ",JOptionPane.OK_OPTION);
				
				UserInfoFrame.moneyText.setText(um.getUserService().getUserById(um.getUser().getId()).getMoney());
			}
		}

	    
		this.dispose();

	}
}

class TakeFrame extends JFrame implements ActionListener {

	JLabel moneyLable = new JLabel("������ȡ���");
	JTextField moneyText = new JTextField(20);

	JButton confirm = new JButton("ȷ��");

	JPanel centerPane = new JPanel();
	JPanel southPane = new JPanel();
	UserManage um = UserManage.getInstance();
	public TakeFrame() {

		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());

		c.add(centerPane, "Center");
		c.add(southPane, "South");

		centerPane.add(moneyLable);
		centerPane.add(moneyText);

		southPane.add(confirm);

		confirm.addActionListener(this);

		this.setTitle("ȡ�����");
		this.setSize(360, 120);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {

		 boolean flag=true;
		if (this.moneyText.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "���Ϊ��", "��ʾ",
					JOptionPane.OK_OPTION);
		} else {
			for(int i=0;i<this.moneyText.getText().length();i++){
				if(!(47<this.moneyText.getText().charAt(i)&&this.moneyText.getText().charAt(i)<58)){
					JOptionPane.showMessageDialog(this, "����ʽ����", "��ʾ",JOptionPane.OK_OPTION);
					flag=false;
					break;
				}
			}
			if(flag&&!um.takeMoney(this.moneyText.getText())){
			//	JOptionPane.showMessageDialog(this, "�ڲ�����", "��ʾ",JOptionPane.OK_OPTION);
			}else{
				if(flag)
				JOptionPane.showMessageDialog(this,"ȡ��ɹ�", "��ʾ",JOptionPane.OK_OPTION);
				
				UserInfoFrame.moneyText.setText(um.getUserService().getUserById(um.getUser().getId()).getMoney());
				
			}
		}

		this.dispose();
	}

}



