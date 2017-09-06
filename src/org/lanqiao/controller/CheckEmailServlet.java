package org.lanqiao.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckEmailServlet
 */
@WebServlet(name = "checkEmailServlet", urlPatterns = { "/email.do" })
public class CheckEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		System.out.println(email);
		if(email.equals("admin@qq.com")){
			response.getWriter().write("1");
		}else{
			System.out.println("进入");
			response.getWriter().write("0");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
