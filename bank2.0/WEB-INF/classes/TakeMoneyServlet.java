package com.cx.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cx.bank.bll.UserManager;
import com.cx.bank.model.User;

public class TakeMoneyServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserManager am = UserManager.getInstance();
		String money = request.getParameter("umoney");
		User u = (User) request.getSession().getAttribute("user");

		// ��ֹע����������servlet
		if (u != null) {
			String id = u.getUid();
			if (am.takeMoney(id, money)) {
				// �޸ĳɹ���ת����ҳ
				request.setAttribute("msg", "ȡ���ɹ�");
				request.getRequestDispatcher("/takeMoney.jsp").forward(request,
						response);
				return;
			} else {
				request.setAttribute("msg", "ϵͳ���������");
				request.getRequestDispatcher("/takeMoney.jsp").forward(request,
						response);
				return;
			}

		}else{
			
			return;
		}
		
	}


}
