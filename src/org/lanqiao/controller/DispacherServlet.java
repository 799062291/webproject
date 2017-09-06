package org.lanqiao.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.lanqiao.entity.Cart;
import org.lanqiao.entity.Category;
import org.lanqiao.entity.CookieItem;
import org.lanqiao.entity.Goods;
import org.lanqiao.entity.News;
import org.lanqiao.entity.Order;
import org.lanqiao.entity.OrderDetail;
import org.lanqiao.entity.User;
import org.lanqiao.service.CategoryService;
import org.lanqiao.service.GoodsService;
import org.lanqiao.service.NewsService;
import org.lanqiao.service.OrderDetailService;
import org.lanqiao.service.OrderService;
import org.lanqiao.service.UserService;
import org.lanqiao.service.impl.CategoryServiceImpl;
import org.lanqiao.service.impl.GoodsServiceImpl;
import org.lanqiao.service.impl.NewsServiceImpl;
import org.lanqiao.service.impl.OrderDetailServiceImpl;
import org.lanqiao.service.impl.OrderServiceImpl;
import org.lanqiao.service.impl.UserServiceImpl;
import org.lanqiao.util.CartUtil;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
//主要业务实现转发（因为用户不能直接访问WEB-INF下的资源）
/**
 * Servlet implementation class DispacherServlet
 */
@WebServlet(name = "DispacherServlet.do", urlPatterns = { "/DispacherServlet.do" })
public class DispacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
	    String id = request.getParameter("id");
	    System.out.println(id);
	    System.out.println(type);
	    if(type!=null && type.equals("news") && id!=null){
	    	NewsService ns = new NewsServiceImpl();
	    	News new1 = ns.getNewsById(id);
	    	request.setAttribute("news1", new1);
	    	request.getRequestDispatcher("/WEB-INF/title.jsp").forward(request, response);
	    }else if(type.equals("goods")&&id!=null){
	    	GoodsService gs = new GoodsServiceImpl();
	    	CategoryService cs = new CategoryServiceImpl();
	    	Goods goods = gs.getGoodsById(id);
	    	String cid = goods.getCid();
	    	String pid = goods.getPid();
	    	Category cate = cs.getCate(cid);
	    	request.setAttribute("cate", cate);
	    	request.setAttribute("goods", goods);
	    	request.getRequestDispatcher("/WEB-INF/detail.jsp").forward(request, response);	
	    }else if(type.equals("regedit")){
	    	request.getRequestDispatcher("/WEB-INF/regedit.jsp").forward(request, response);	
	    }else if(type!=null&&type.equals("login")){
	    	//验证用户是否登陆；
//	    	HttpSession session = request.getSession();
//	    	Object obj = session.getAttribute("user");
//	    	if(obj==null){
//	    		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);	
//	    	    return;
//	    	}
//	    	request.getRequestDispatcher("/WEB-INF/my.jsp").forward(request, response);	
	        Cookie[] cookies = request.getCookies();
	        Cookie userCookie = null;
	        if(cookies!=null){
	        for(Cookie c:cookies){
	        	if(c.getName().equals("uloginid")){
	        		userCookie = c;
	        		break;
	        	}
	        }
	        }
	        if(userCookie!=null&&userCookie.getValue().length()!=0){
	        	String uloginid = userCookie.getValue();
	        	//根据账号找此用户
	        	UserService us = new UserServiceImpl();
	        	User user = us.getUserByLoginId(uloginid);
	        	request.getSession().setAttribute("user", user);
	        }
	        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);	
	    }else if(type!=null&&type.equals("my")){
	    	request.getRequestDispatcher("/WEB-INF/my.jsp").forward(request, response);	
	    }else if(type!=null&&type.equals("edit")){
	    	request.getRequestDispatcher("/WEB-INF/modifyuserinfo.jsp").forward(request, response);	
	    }else if(type!=null&&type.equals("cart")){
	    	request.getRequestDispatcher("/WEB-INF/cart.jsp").forward(request, response);	
	    }else if(type!=null&&type.equals("order")){
	    	request.getRequestDispatcher("/WEB-INF/order.jsp").forward(request, response);	
	    }else if(type!=null&&type.equals("orderfinal")){
	    	request.getRequestDispatcher("/WEB-INF/orderfinal.jsp").forward(request, response);	
	    }else if(type!=null&&type.equals("final")){
	    	//1.下订单(取出数据)
	    	List<CookieItem> list = getItems(request);
	    	List<Cart> buygoods = CartUtil.convertCookieItemListToCartList(list);
	    	String orderid = UUID.randomUUID().toString();
	    	String uesrid = ((User)request.getSession().getAttribute("user")).getUserid();
	    	double totalprice = 0;
	    	for( int i = 0;i<buygoods.size();i++){
	    		Cart cart = buygoods.get(i);
	    		totalprice += cart.getAmount()*cart.getGsaleprice();
	    	}
	    	
	    	java.util.Date date = new java.util.Date();
	    	java.sql.Date date2 = new Date(date.getTime());
	    	Order order = new Order(orderid, uesrid, totalprice,date2);
	    	OrderService os = new OrderServiceImpl();
	    	os.insertOrder(order);
	    	
	    	for(Cart c:buygoods){
	    		String orderdetailid = UUID.randomUUID().toString();
	    		String gtitle = c.getGtitle();
	    		String gid = c.getGid();
	    		Double gsaleprice = c.getGsaleprice();
	    		int gnumber = c.getAmount();
	    		OrderDetail orderDetail = new OrderDetail(orderdetailid, gtitle, gsaleprice, gid, gnumber, orderid);
	    	    OrderDetailService od = new OrderDetailServiceImpl();
	    	    od.insertOrderDetail(orderDetail);
	    	}
	    	
	    	//2.清空购物车
	    	Cookie[]cookies = request.getCookies();
	    	Cookie currentCookie = null;
	    	if(cookies!=null){
	    		for(Cookie c:cookies){
	    			if(c.getName().equals("cart")){
	    				currentCookie = c;
	    				break;
	    			}
	    		}
	    		if(currentCookie!=null){
		    		currentCookie.setMaxAge(0);
		    		response.addCookie(currentCookie);
		    	}
	    	
	    	}
	    	
	    	request.getRequestDispatcher("/WEB-INF/final.jsp").forward(request, response);	
	    }
	    
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	//从车中取出所有商品；
		private List<CookieItem> getItems(HttpServletRequest request){
			Cookie cart = null;
			Cookie[] cookies = request.getCookies();
			if(cookies!=null){
				for(Cookie c:cookies){
					if(c.getName().equals("cart")){
						cart = c;
						break;
					}
				}
			}
			if(cart==null){
				return null;
			}
			String json = cart.getValue();
			Gson gson = new Gson();
			TypeToken<List<CookieItem>> listType = new TypeToken<List<CookieItem>>(){
				
			};
			List<CookieItem> list = gson.fromJson(json, listType.getType());
			return list;
		}

}
