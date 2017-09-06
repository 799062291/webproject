package org.lanqiao.admin.controller;

import java.io.IOException;
import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.Goods;
import org.lanqiao.entity.KeyWord;
import org.lanqiao.util.DBUtil;

import com.google.gson.Gson;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet(name = "searchservlet", urlPatterns = { "/searchservlet.do" })
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-Type", "text/html; charset = UTF-8"); 
		request.setCharacterEncoding("UTF-8");
		System.out.println("woliguo");
		String keywords = request.getParameter("key");
		String cid = request.getParameter("cid");
		String type = request.getParameter("type");
		String page = request.getParameter("page");
		String keyword = new String(keywords.getBytes("ISO-8859-1"),"UTF-8"); //转换中文乱码
		int pageIndex=0;
	    if(page!=null){
	    	pageIndex = Integer.parseInt(page);
		}
	   // int pageSize = Integer.parseInt(request.getParameter("rows")); //从0开始
	    System.out.println("当前页数："+keyword);
		//根据key返回一个提示列表
	 
	    if(keyword.equals("")||keyword.length()<1){
		  response.getWriter().write("reload");
	    }else if(type.equals("load")){
	    	Map<String, Object> map = new HashMap<String, Object>();
	    	List<Goods> list = search(keyword, cid, type, pageIndex);
	    	int count = getCount(keyword, cid, type, pageIndex);
	    	map.put("total", count);
			map.put("rows", list);
			Gson gson = new Gson();
	
			String json = gson.toJson(map);
			//response.setContentType("application/json");
		
			response.getWriter().write(json);
		
	    }else if(cid==null){
	    
		List<Goods> list = search(keyword, cid, type, pageIndex);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		response.setContentType("application/json");
		
		response.getWriter().write(json);
	    }else{
	    	List<Goods> list = search(keyword, cid, type, pageIndex);
			Gson gson = new Gson();
			String json = gson.toJson(list);
			response.setContentType("application/json");
			
			response.getWriter().write(json);
	    }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public List<Goods> search(String id,String cid,String type,int curr){
		List<Goods> list = new ArrayList<Goods>();
		Connection conn = DBUtil.getConnection();
		String sql = null;
		if(cid==null){ //全局查询
			 sql = "select t.*,rownum rn from(select * from t_goods where gtitle like ?) t where rownum < 6";
		}
		else if(type.equals("look")){
			 sql = "select t.*,rownum rn from(select * from t_goods where cid= ? and gtitle like ?) t where rownum < 6";
		}else{
			
			sql = "select * from (select t.*,rownum rn from(select * from t_goods where cid= ? and gtitle like ?) t where rownum <= ?) where rn >=?";
		    System.out.println("type");
		}
	
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			//ps.setString(1, word);
		if(cid==null){
			ps.setString(1, "%"+id+"%");
		}
			else if(type.equals("look")){
				ps.setString(1, cid);
				ps.setString(2, "%"+id+"%");
			}else{
				
				int end = 10*curr;
				int begin = (curr-1)*10+1;
				System.out.println(begin+" "+end);
				ps.setString(1, cid);
				ps.setString(2, "%"+id+"%");
				ps.setInt(3, end);
				ps.setInt(4, begin);
			}
			
			ResultSet rs = ps.executeQuery();
			System.out.println(cid);
			Goods keyWord = null;
			while(rs.next()){
				
				keyWord = new Goods(rs.getString("gid"), rs.getString("gtitle"), rs.getString("gauthor"), rs.getDouble("gsaleprice"), rs.getDouble("ginprice"), rs.getString("gdesc"), rs.getString("gimg"), rs.getInt("gclicks"), rs.getString("cid"), rs.getString("pid"));
			    list.add(keyWord);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
	public int getCount(String id,String cid,String type,int curr){
		int num = 0;
		Connection conn = DBUtil.getConnection();
		String sql = "select count(1) from (select t.*,rownum rn from(select * from t_goods where cid= ? and gtitle like ?) t)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			int end = 10*curr;
			int begin = (curr-1)*10+1;
			System.out.println(begin+" "+end);
			ps.setString(1, cid);
			ps.setString(2, "%"+id+"%");
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				num = rs.getInt(1);
			}
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
		
	}

}
