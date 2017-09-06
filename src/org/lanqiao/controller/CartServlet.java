package org.lanqiao.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.Cart;
import org.lanqiao.entity.CookieItem;
import org.lanqiao.util.CartUtil;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet(name = "cartServlet", urlPatterns = { "/cart.do" })
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.将商品添加到购物车(cookie)|更新购物车的商品 //{gid:1,amount:1}
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
		String gid = request.getParameter("gid");
		String did = request.getParameter("did");
		String id = request.getParameter("id");
		System.out.println("gid!!--"+gid);
		CookieItem item = new CookieItem(gid,1);
		addItem(item, request, response);
		if(did!=null&&did.equals("delete")){
			removeItem(id, request, response);
			request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
			
			return;
		}
		//2.获取购物车中所有商品
//		List<CookieItem> cart = getItems(request);
//		//3.存到request域对象，转到cart.jsp显示车中的商品
//		List<Cart> list = CartUtil.convertCookieItemListToCartList(cart);
//		request.setAttribute("cart", list);
		request.getRequestDispatcher("/WEB-INF/buySuccess.jsp").forward(request, response);	
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
	//将商品添加到购物车
	private void addItem(CookieItem item,HttpServletRequest request,HttpServletResponse response){
		List<CookieItem> list = getItems(request);
		//第一次向购物车添加商品
		if(list==null){
			list = new ArrayList<CookieItem>();
			//list.add(item);
		}
		//表示购物车不为空（有商品）
		if(list!=null){
			CookieItem currentItem = null;
			for(CookieItem goods:list){
				if(goods.getGid().equals(item.getGid())){
					currentItem = goods;
				
				}
			}
			if(currentItem == null){  //说明购物车没有此商品
				list.add(item);
			}else{
				currentItem.setAmount(currentItem.getAmount()+1);
			}
		}
		//重新将数据写入到cookie；
		Gson gson = new Gson();
		String json = gson.toJson(list);
		Cookie cookie = new Cookie("cart", json);
		cookie.setMaxAge(60*60*24*365);
		response.addCookie(cookie);
	}
	//从购物车中删除商品
	private void removeItem(String id,HttpServletRequest request,HttpServletResponse response){
		List<CookieItem> list = getItems(request);
		//第一次向购物车添加商品
		if(list==null){
			return;
		}
		//表示购物车不为空（有商品）
	
			CookieItem currentItem = null;
			for(CookieItem goods:list){
				if(goods.getGid().equals(id)){
					currentItem = goods;
		
				}
			}
			if(currentItem == null){  //说明购物车没有此商品
				return;
			}else{
				list.remove(currentItem);
			}			
		
			//重新将数据写入到cookie；
			Gson gson = new Gson();
			String json = gson.toJson(list);
			Cookie cookie = new Cookie("cart", json);
			cookie.setMaxAge(60*60*24*365);
			response.addCookie(cookie);

	}

}
