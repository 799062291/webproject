package org.lanqiao.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.KeyWord;
import org.lanqiao.util.DBUtil;

import com.google.gson.Gson;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet(name = "searchServlet", urlPatterns = { "/search.do" })
public class SearchServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("key");
		//根据key返回一个提示列表
		List<KeyWord> list = search(keyword);
		Gson gson = new Gson();
		String json = gson.toJson(list);
		response.setContentType("application/json");
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public List<KeyWord> search(String id){
		List<KeyWord> list = new ArrayList<KeyWord>();
		Connection conn = DBUtil.getConnection();
		String sql = "select t.*,rownum rn from(select * from keywords where keyword like ?) t where rownum<6";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+id+"%");
			ResultSet rs = ps.executeQuery();
			KeyWord keyWord = null;
			while(rs.next()){
				keyWord = new KeyWord(rs.getString(1), rs.getString(2));
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

}
