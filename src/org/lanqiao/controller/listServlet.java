package org.lanqiao.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.Goods;
import org.lanqiao.entity.PageInfo;
import org.lanqiao.service.GoodsService;
import org.lanqiao.service.impl.GoodsServiceImpl;

/**
 * Servlet implementation class listServlet
 */
@WebServlet(name = "listServlet.do", urlPatterns = { "/listServlet.do" })
public class listServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//根据类型的cid找出次类型的所有商品
		String cid = request.getParameter("cid");
		String pageIndex = request.getParameter("pageIndex");
		String type = request.getParameter("type");
		String keywords = request.getParameter("key");
		String key = null;
		if(keywords!=null){
		key = new String(keywords.getBytes("ISO-8859-1"),"UTF-8"); //转换中文乱码
		}
		if(type==null){
		if(cid==null){
			cid="1";
		}
		int pageSize = 5;
		
		GoodsService gs = new GoodsServiceImpl();
		PageInfo<Goods> pageInfo = gs.GoodsList(cid, pageSize, Integer.parseInt(pageIndex));
		request.setAttribute("pageInfo", pageInfo);
		System.out.println(pageInfo.getTotalNumber());
		request.setAttribute("model", 0);
		//转到list页面
		request.getRequestDispatcher("/WEB-INF/list.jsp").forward(request, response);
		}else{
			int pageSize = 5;
			System.out.println("key:"+key);
			GoodsService gs = new GoodsServiceImpl();
			PageInfo<Goods> pageInfo = gs.GoodsList1(key, pageSize, Integer.parseInt(pageIndex));
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("key", key);
			request.setAttribute("pageIndex", Integer.parseInt(pageIndex));
			request.setAttribute("model", 1);
			System.out.println(pageInfo.getTotalNumber());
			//转到list页面
			request.getRequestDispatcher("/WEB-INF/list.jsp").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
