	package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.dao.NewsDao;
import org.lanqiao.entity.News;
import org.lanqiao.util.DBUtil;



public class NewsDaoImpl implements NewsDao {   //dao层的实现类，主要负责与数据库交互，对数据进行增删改查的功能，通过service的实现类来调用此类以及类里的方法；

	@Override
	public List<News> list() {
		
		List<News> list = new ArrayList<News>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			
			//2.创建PreparedStatement对象
			String sql = "select * from t_news";
			ps = conn.prepareStatement(sql);
		
			//3.执行操作
			rs = ps.executeQuery();
			
			//4.取数据
			News news = null;
			
			
			while (rs.next()) {
				news = new News(rs.getString("tid"), rs.getString("title"),rs.getString("tcontent"), rs.getDate("tpubdate"));
			    list.add(news);
			    
			}
			
			//5.关闭流
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			
		}finally{
			
		}
		return list;
	}

	@Override
	public News get(String id) {
		News news1 = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			
			//2.创建PreparedStatement对象
			String sql = "select * from t_news where tid=?";
			ps = conn.prepareStatement(sql);
		    ps.setString(1, id);
			//3.执行操作
			rs = ps.executeQuery();
			
			//4.取数据
			News news = null;
			
			
			while (rs.next()) {
				news1 = new News(rs.getString("tid"), rs.getString("title"),rs.getString("tcontent"), rs.getDate("tpubdate"));			    
			}
			
			//5.关闭流
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			
		}finally{
			
		}
		return news1;
	}

}
