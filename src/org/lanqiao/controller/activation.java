package org.lanqiao.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.service.activationService;
import org.lanqiao.service.impl.activationServiceImpl;

/**
 * Servlet implementation class activation
 */
@WebServlet(name = "activation.do", urlPatterns = { "/activation.do" })
public class activation extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		activationService as = new activationServiceImpl();
		as.activationUser(id);
		request.getRequestDispatcher("/WEB-INF/actsuccess.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
