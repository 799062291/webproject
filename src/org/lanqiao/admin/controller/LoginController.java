package org.lanqiao.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.User;
import org.lanqiao.service.UserService;
import org.lanqiao.service.impl.UserServiceImpl;

/**
 * Servlet implementation class LoginController
 */
@WebServlet(name = "loginController", urlPatterns = { "/logincontroller.do" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uloginid = request.getParameter("uloginid"); //取name的值
	    String upassword = request.getParameter("upassword");
	    UserService us = new UserServiceImpl();
	    User user = us.login(uloginid, upassword);
	    List<User> list = us.getUsers();
	    System.out.println(list);
	    if(user!=null){
	    	response.getWriter().write("1");
	    }else {
	    	response.getWriter().write("0");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
