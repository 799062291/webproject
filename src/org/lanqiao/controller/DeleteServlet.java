package org.lanqiao.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet(name = "deleteServlet", urlPatterns = { "/delete.do" })
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  Cookie[] cookies = request.getCookies();
	       
	        if(cookies!=null){
	        for(Cookie c:cookies){
	        	if(c.getName().equals("uloginid")){
	        		System.out.println("cookie!!");
	        		c.setValue(null); 
	        		c.setMaxAge(0);
	        		c.setPath(null);
	        		response.addCookie(c);
	        		break;
	        	}
	        }
	        }
	        
	        HttpSession session = request.getSession();
			session.setAttribute("user", null);
			session.setMaxInactiveInterval(0);
	       
		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
