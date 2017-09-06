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

import com.google.gson.Gson;

/**
 * Servlet implementation class listServlet
 */
@WebServlet(name = "Pagelist", urlPatterns = { "/page.do" })
public class Pagelist extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//根据类型的cid找出次类型的所有商品
		String cid = request.getParameter("cid");
		String pageindex = request.getParameter("pageIndex");
		if(cid==null){
			cid="1";
		}
		String pageSize = request.getParameter("pageSize");
		if(pageSize==null){
			pageSize="5";
		}
		if(pageindex==null||pageindex=="0"){
			pageindex="0";
		}
		GoodsService gs = new GoodsServiceImpl();
		PageInfo<Goods> pageInfo = gs.GoodsList(cid, Integer.parseInt(pageSize), Integer.parseInt(pageindex)+1);
	
		Gson gson = new Gson();
		System.out.println("yoyoyo");
	
		//response.setContentType("application/json");
		response.getWriter().write(gson.toJson(pageInfo));
		//System.out.println(pageInfo.getTotalNumber());
		//转到list页面
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
