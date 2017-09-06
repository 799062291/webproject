package org.lanqiao.controller;

import java.io.IOException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.PasswordAnswer;
import org.lanqiao.entity.User;
import org.lanqiao.service.PasswordAnswerService;
import org.lanqiao.service.UserService;
import org.lanqiao.service.impl.PasswordAnswerServiceImpl;
import org.lanqiao.service.impl.UserServiceImpl;
import org.lanqiao.util.MailUtil;

/**
 * Servlet implementation class regeditServlet
 */
@WebServlet(name = "regedit.do", urlPatterns = { "/regedit.do" })
public class regeditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//验证验证码是否正确
		//从session中取验证码与用户输入的验证码进行比对
		String code = request.getSession().getAttribute("code").toString().toLowerCase();
		String ucheckcode = request.getParameter("ucheckcode").toLowerCase();
		if(!code.equals(ucheckcode)){
			request.getRequestDispatcher("/WEB-INF/regedit.jsp?id=1").forward(request, response);
		    return;
		}
		//处理注册
		String uname = request.getParameter("uname");
		String uemail = request.getParameter("uemail");
		String upassword = request.getParameter("upassword");
		String usex = request.getParameter("usex");
		String utel = request.getParameter("utel");
		String uaddress = request.getParameter("uaddress");
		String uesrid = UUID.randomUUID().toString(); //生成全球唯一16进制编码，作为主键，不重复！
		String ustateid = "36D0F394FC6A45829385E0BE11208263";//账号默认设为无效；
		String uroleid = "2"; //账号类型设为普通用户；
		//A.验证邮箱格式：
		// 编译正则表达式
		Pattern pattern = Pattern.compile("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+",Pattern.CASE_INSENSITIVE);
		// 字符串是否与正则表达式相匹配
		Matcher matcher = pattern.matcher(uemail);	
		System.out.println("!!!"+matcher.matches());
		if(!matcher.matches()){
			request.getRequestDispatcher("/WEB-INF/regedit.jsp?id=2").forward(request, response);
		    return;
		}
		//B.密码格式验证
		if(upassword.length()<6){
			request.getRequestDispatcher("/WEB-INF/regedit.jsp?id=3").forward(request, response);
		    return;
		}
		
		
		User user = new User(uesrid, uemail, uname, upassword, usex, utel, uaddress, uroleid, ustateid);
		UserService us = new UserServiceImpl();
		us.insertUser(user);
		
		//处理密保问题
		String squestion = request.getParameter("squestion");
		String sanswer = request.getParameter("sanswer");
		String ubackupemail = request.getParameter("ubackupemail");
		String answerid = UUID.randomUUID().toString();
		PasswordAnswerService pas = new PasswordAnswerServiceImpl();
		PasswordAnswer passwordAnswer = new PasswordAnswer(answerid, squestion, sanswer, ubackupemail, uesrid);
		pas.insertPasswordAnswer(passwordAnswer);
		
		//发送一封激活邮件；
		MailUtil.sendMail(uemail, "激活邮件", "请点此链接激活账号： http://localhost:8080/web-project/activation.do?id="+uesrid+"");
		request.getRequestDispatcher("/WEB-INF/regsuccess.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
