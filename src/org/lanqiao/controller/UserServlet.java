package org.lanqiao.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.lanqiao.entity.PasswordAnswer;
import org.lanqiao.entity.User;
import org.lanqiao.service.PasswordAnswerService;
import org.lanqiao.service.UserService;
import org.lanqiao.service.impl.PasswordAnswerServiceImpl;
import org.lanqiao.service.impl.UserServiceImpl;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet(name = "userServlet", urlPatterns = { "/user.do" })
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		if(type!=null&&type.equals("edit")){
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
	    String upassword = request.getParameter("upassword");
	    String usex = request.getParameter("usex");
	    String utel = request.getParameter("utel");
	    String uaddress = request.getParameter("uaddress");
	    String aquestion = request.getParameter("aquestion");
	    String answer = request.getParameter("answer");
	    String email = request.getParameter("reemail");
	    user.setUpassword(upassword);
	    user.setUsex(usex);
	    user.setUtel(utel);
	    user.setUaddress(uaddress);
	    UserService us = new UserServiceImpl();
	    us.updateUser(user);
	   
	    PasswordAnswerService ps = new PasswordAnswerServiceImpl();
	    PasswordAnswer passwordAnswer = ps.getPasswordAnswer(user.getUserid());
	    System.out.println(passwordAnswer.getAnswerid());
	    passwordAnswer.setAquestion(aquestion);
	    passwordAnswer.setAnswer(answer);
	    passwordAnswer.setEmail(email);
	    ps.updatePasswordAnswer(passwordAnswer);
	    request.getRequestDispatcher("/WEB-INF/updatesuccess.jsp").forward(request, response);	
		}
	    
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
