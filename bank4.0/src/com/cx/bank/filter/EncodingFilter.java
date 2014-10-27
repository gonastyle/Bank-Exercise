package com.cx.bank.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {
	public void init(FilterConfig filterConfig) throws ServletException {
		 //System.out.println("** ��������ʼ��...") ;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		//ʹ�ͻ�����utf-8������ʾ
		 response.setContentType("text/html;charset=UTF-8");
		 
		 chain.doFilter(request, response);
	}

	public void destroy() {
		 //System.out.println("** ����������...") ;
	}
};