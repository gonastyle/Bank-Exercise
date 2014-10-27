package com.cx.bank.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cx.bank.bll.UserManager;

public class ShowAllUserServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//����ҵ������ȡ��Ӧ������
        UserManager um=UserManager.getInstance();
           
        List l=um.showAllUser();
   		if(l==null){
   			request.setAttribute("msg","û�������û���");
   			request.getRequestDispatcher("/userList.jsp").forward(request,
   					response);
   			return;
   		}else{
   			request.setAttribute("userlist",l);
   			request.getRequestDispatcher("/userList.jsp").forward(request,
   					response);
   			return;
   		}
 
	}

}
