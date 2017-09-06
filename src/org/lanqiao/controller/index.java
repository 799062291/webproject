package org.lanqiao.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.News;
import org.lanqiao.service.NewsService;
import org.lanqiao.service.impl.NewsServiceImpl;

/**
 * Servlet implementation class index
 */
@WebServlet(name = "index.do", urlPatterns = { "/index.do" })
public class index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NewsService ns = new NewsServiceImpl();  //创建service对象；
		List<News> news = ns.newList();     //使用dao层里的方法获取数据
		request.setAttribute("news", news);
	
		request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
			
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
