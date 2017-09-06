package org.lanqiao.admin.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.Order;
import org.lanqiao.entity.OrderDetail;
import org.lanqiao.service.OrderDetailService;
import org.lanqiao.service.OrderService;
import org.lanqiao.service.impl.OrderDetailServiceImpl;
import org.lanqiao.service.impl.OrderServiceImpl;

import com.google.gson.Gson;

/**
 * Servlet implementation class Ordercontroller
 */
@WebServlet(name = "ordercontroller", urlPatterns = { "/ordercontroller.do" })
public class Ordercontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		if(type!=null&&type.equals("order")){
			OrderService os = new OrderServiceImpl();
			List<Order> list = os.getList();
			Gson gson = new Gson();
			response.getWriter().write(gson.toJson(list));
			
		}else if(type!=null&&type.equals("detail")){
			String id = request.getParameter("id");
			OrderDetailService os = new OrderDetailServiceImpl();
			List<OrderDetail> list = os.getDetails(id);
			Gson gson = new Gson();
			response.getWriter().write(gson.toJson(list));
		}else if(type!=null&&type.equals("edit")){
		
			String orderid = request.getParameter("orderid");
		    String userid = request.getParameter("cate");
			String totalprice = request.getParameter("totalprice");
			String orderDate = request.getParameter("orderdate");
			SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = sdf.parse(orderDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(date);
			Order order = new Order(orderid, userid, Double.parseDouble(totalprice), date);
		    OrderService os	= new OrderServiceImpl();
		    os.updateOrder(order);
		    response.getWriter().write("1");
		}else if(type!=null&&type.equals("remove")){
			OrderService os	= new OrderServiceImpl();
			String id = request.getParameter("orderid");
			os.deleteOrder(id);
		    response.getWriter().write("1");
		}else if(type!=null&&type.equals("addorder")){
			OrderService os	= new OrderServiceImpl();
			String orderid = UUID.randomUUID().toString();
		    String userid = request.getParameter("cate");
			String totalprice = request.getParameter("totalprice");
			System.out.println("danjia-->"+totalprice);
			String orderDate = request.getParameter("orderdate");
			SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = sdf.parse(orderDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Order order = new Order(orderid, userid, 0, date);  //订单初始单价为0
		    os.insertOrder(order);
			response.getWriter().write("1");
		}else if(type!=null&&type.equals("addgoods")){
			String orderdetailid = UUID.randomUUID().toString();
			String gtitle = request.getParameter("gname");
			String gsaleprice = request.getParameter("gsaleprice");
			String gid = request.getParameter("gid");
			String gnumber = request.getParameter("gnumber");
			String orderid = request.getParameter("orderid");
		    OrderDetailService os = new OrderDetailServiceImpl();
		    OrderDetail orderDetail = new OrderDetail(orderdetailid, gtitle, Double.parseDouble(gsaleprice), gid, Integer.parseInt(gnumber), orderid);
			os.insertOrderDetail(orderDetail);
			OrderService os1 = new OrderServiceImpl();
			double price = Double.parseDouble(gnumber)*Double.parseDouble(gsaleprice);
			os1.changeSum(orderid, price);
		    response.getWriter().write("1");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
