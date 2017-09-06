package org.lanqiao.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.User;
import org.lanqiao.service.UserService;
import org.lanqiao.service.impl.UserServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "loginServlet", urlPatterns = { "/login.do" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uloginid = request.getParameter("uname");
		String upassword = request.getParameter("upassword");
	    UserService us = new UserServiceImpl();
	    User user = us.login(uloginid, upassword);
	    if(user!=null){
	    	if(user.getUstateid().equals("B5868B7A06E54DAEB19658343D3A2B28")){  //验证账号是否有效
	    	request.getSession().setAttribute("user", user);
	    	String chk = request.getParameter("chk");
	    	if(chk!=null){
	    		//将账号写入cookie
	    		Cookie cookie = new Cookie("uloginid", uloginid);
	    		cookie.setMaxAge(60*60*24*7);
	    		response.addCookie(cookie);
	    	}
	    	request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	    	}else{    //如果验证账号无效，则返回提示
	    		request.getRequestDispatcher("/WEB-INF/login.jsp?id=1").forward(request, response);
	    	}
	    }else if(user==null){ //若该账号不存在或密码错误，则返回提示
	    	request.getRequestDispatcher("/WEB-INF/login.jsp?id=2").forward(request, response);
	    }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
