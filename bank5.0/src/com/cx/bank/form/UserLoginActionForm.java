package com.cx.bank.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * �ռ������ݵ�ActionForm��ActionForm�е����Ա�����html�б��е�name����һ��
 * @author Administrator
 *
 */
public class UserLoginActionForm extends ActionForm {

	private String uid;
	
	private String upwd;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUpwd() {
		return upwd;
	}

	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}

	
	
}
