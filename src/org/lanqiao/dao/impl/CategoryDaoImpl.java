package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.dao.CategoryDao;
import org.lanqiao.entity.Category;
import org.lanqiao.entity.News;
import org.lanqiao.util.DBUtil;

public class CategoryDaoImpl implements CategoryDao {

	@Override
	public List<Category> list() {
		List<Category> list = new ArrayList<Category>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			
			//2.创建PreparedStatement对象
			String sql = "select * from t_category order by ordervalue";
			ps = conn.prepareStatement(sql);
		
			//3.执行操作
			rs = ps.executeQuery();
			
			//4.取数据
			Category cate = null;
			
			
			while (rs.next()) {
				cate = new Category(rs.getString("cid"), rs.getString("cname"));
			    list.add(cate);
			    
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
	public Category get(String cid) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Category cate = null;
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			
			//2.创建PreparedStatement对象
			String sql = "select * from t_category where cid = ?";
			ps = conn.prepareStatement(sql);
		    ps.setString(1, cid);
			//3.执行操作
			rs = ps.executeQuery();
			
			//4.取数据
			
			
			
			while (rs.next()) {
				cate = new Category(rs.getString("cid"), rs.getString("cname"));
			   
			    
			}
			
			//5.关闭流
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			
		}finally{
			
		}
		return cate;
	}

}
