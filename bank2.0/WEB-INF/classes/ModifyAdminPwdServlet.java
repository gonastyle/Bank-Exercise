package com.cx.bank.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cx.bank.bll.AdminManager;
import com.cx.bank.model.Admin;

public class ModifyAdminPwdServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		AdminManager am = AdminManager.getInstance();
		String pwd=request.getParameter("upwd");
		Admin a=(Admin)request.getSession().getAttribute("admin");
		
		//��ֹע����������servlet
		if(a!=null){
			String id=a.getAid();
			if(am.modifyAdminPwd(id, pwd)){
				//�޸ĳɹ���ת����ҳ
				request.getRequestDispatcher("/adminSuccess.jsp").forward(
						request, response);
				return;
			}else{
				
				response.sendRedirect("error.jsp");
				return;
			}
			
		}
		
	

	}

}
