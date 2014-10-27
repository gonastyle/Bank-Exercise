package com.cx.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cx.bank.bll.UserManager;
import com.cx.bank.model.User;

public class RegisterUserServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserManager am = UserManager.getInstance();
		String pwd = request.getParameter("upwd");
		String id = request.getParameter("uid");
		String name = new String(request.getParameter("uname").getBytes("ISO-8859-1"),"UTF-8") ;
	 
		if (id != null && pwd != null && name != null) {
			User u = new User();
			u.setUid(id);
			u.setUname(name);
			u.setUpwd(pwd);
			u.setUmoney(0.0);
			if (am.registerUser(u)) {
				request.setAttribute("msg", "ע��ɹ�");
				request.getRequestDispatcher("/registerUser.jsp").forward(
						request, response);
				return;
			} else {
				request.setAttribute("msg", "ע��ʧ��:���ݸ�ʽ������û�Id���ظ�");
				request.getRequestDispatcher("/registerUser.jsp").forward(
						request, response);
				return;
			}

		} else {
			request.setAttribute("msg", "�û���������Ϊ�գ�");
			request.getRequestDispatcher("/registerUser.jsp").forward(request,
					response);
			return;
		}

	}

}
