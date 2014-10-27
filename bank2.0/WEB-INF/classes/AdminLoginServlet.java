package com.cx.bank.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cx.bank.bll.AdminManager;
import com.cx.bank.model.Admin;

public class AdminLoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * �����������������ķ��� PrintWriter out=response.getWriter(); String name = new
		 * String(request.getParameter("uid").getBytes("ISO-8859-1"),"UTF-8") ;
		 * System.out.println("name = "+name) ; out.print("��ӭ"+name);
		 */

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String id = request.getParameter("uid");
		String pwd = request.getParameter("upwd");

		HttpSession session = request.getSession();

		AdminManager am = AdminManager.getInstance();
		Admin admin = am.findAdminById(id);
		if (admin == null) {
			request.setAttribute("msg", "�û�ID�Ų����ڣ�");
			request.getRequestDispatcher("/adminLogin.jsp").forward(request,
					response);
			return;
		} else {
			
			if (am.adminLogin(id, pwd)) {

				session.setAttribute("admin", admin);
				response.sendRedirect("adminSuccess.jsp");
				return;

			} else {

				request.setAttribute("msg", "�������");
				request.getRequestDispatcher("/adminLogin.jsp").forward(
						request, response);
				return;
			}

		}
	}

}
