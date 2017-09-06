package org.lanqiao.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.User;
import org.lanqiao.service.UserService;
import org.lanqiao.service.impl.UserServiceImpl;

import com.google.gson.Gson;

/**
 * Servlet implementation class Usercontroller
 */
@WebServlet(name = "usercontroller", urlPatterns = { "/usercontroller.do" })
public class Usercontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    //将所有user数据变为json数据输出
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   String type = request.getParameter("type");
	   System.out.println(type);
	   
	   if(type!=null && type.equals("list")){
	   UserService us = new UserServiceImpl();
	   List<User> list = us.getUsers();
	   Gson gson = new Gson();
	   String json = gson.toJson(list);
	   response.getWriter().write(json);
	   }else if(type!=null && type.equals("remove")){
		   UserService us = new UserServiceImpl();
		   String userid = request.getParameter("userid");
		   System.out.println(userid);
		   us.remove(userid); 
		   response.getWriter().write("1");
	   }else if(type!=null && type.equals("edit")){
		   String uname = request.getParameter("uname");
	       String userid = request.getParameter("userid");
	       String uemail = request.getParameter("uemail");
	       String upassword = request.getParameter("upassword");
	       String uaddress = request.getParameter("uaddress");
	       String utel = request.getParameter("utel");
	       String usex = request.getParameter("usex");
	       String ustateid = request.getParameter("ustateid");
	       String uroleid = request.getParameter("uroleid");
		   UserService us = new UserServiceImpl();
		   User user = new User(userid, uemail, uname, upassword, usex, utel, uaddress, uroleid, ustateid);
		   us.updateUser(user);
		   response.getWriter().write("1");
	   }else if(type!=null && type.equals("add")){
		   System.out.println("添加啦！");
		   String uname = request.getParameter("uname");
	       String userid = UUID.randomUUID().toString();
	       String uemail = request.getParameter("uemail");
	       String upassword = request.getParameter("upassword");
	       String uaddress = request.getParameter("uaddress");
	       String utel = request.getParameter("utel");
	       String usex = request.getParameter("usex");
	       String ustateid = request.getParameter("ustateid");
	       String uroleid = "2";
		   UserService us = new UserServiceImpl();
		   User user = new User(userid, uemail, uname, upassword, usex, utel, uaddress, uroleid, ustateid);
		   us.insertUser(user);
		   response.getWriter().write("1");
	   }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
