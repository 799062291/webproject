package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.dao.UserDao;
import org.lanqiao.entity.Goods;
import org.lanqiao.entity.User;
import org.lanqiao.util.DBUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public void insert(User user) {
		
		Connection conn = null;
		PreparedStatement ps = null;
	
	
	
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			
			//2.创建PreparedStatement对象
			String sql = "insert into t_user values(?,?,?,?,?,?,?,?,?)";
			
		 
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserid());
			ps.setString(2, user.getUemail());
			ps.setString(3, user.getUname());
			ps.setString(4, user.getUpassword());
			ps.setString(5, user.getUsex());
			ps.setString(6, user.getUaddress());
			ps.setString(7, user.getUtel());
			ps.setString(8, user.getUstateid());
			ps.setString(9, user.getUroleid());
		
			//3.执行操作
		    ps.executeUpdate();
			//4.取数据
		 
			
		
			//5.关闭流
		
			if(ps!=null) ps.close();
			
			if(conn!=null) conn.close();
			
		} catch (Exception e) {
			
		}finally{
			
		}
		

	}

	@Override
	public User getUserByLoginId(String loginid) {
		User user = null;
		Connection conn = null;
		PreparedStatement ps = null;
	    ResultSet rs = null;
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			
			//2.创建PreparedStatement对象
			String sql = "select * from t_user where uloginid=?";
		 
		    ps = conn.prepareStatement(sql);
			ps.setString(1, loginid);
			
			rs = ps.executeQuery();
			
			if(rs.next()){
				user = new User(rs.getString("uesrid"), rs.getString("UEMAIL"), rs.getString("uloginid"), rs.getString("upassword"), rs.getString("usex"), rs.getString("utel"),rs.getString("uaddress"), rs.getString("uroleid"), rs.getString("ustateid"));
			   
			}
		
			//3.执行操作
		    ps.executeUpdate();
			//4.取数据
		 
			
		
			//5.关闭流
		    if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			
			if(conn!=null) conn.close();
			
		} catch (Exception e) {
			
		}finally{
			
		}
		return user;
	}

	@Override
	public void update(User user) {
		
		Connection conn = null;
		PreparedStatement ps = null;
	
	
	 
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			
			//2.创建PreparedStatement对象
			String sql = "update t_user set uemail = ?,uloginid=?,upassword=?,usex=?,uaddress=?,utel=?,ustateid=?,uroleid=? where uesrid=?";
			
		 
			ps = conn.prepareStatement(sql);
		
			ps.setString(1, user.getUemail());
			ps.setString(2, user.getUname());
			ps.setString(3, user.getUpassword());
			ps.setString(4, user.getUsex());
			ps.setString(5, user.getUaddress());
			ps.setString(6, user.getUtel());
			ps.setString(7, user.getUstateid());
			ps.setString(8, user.getUroleid());
			ps.setString(9, user.getUserid());
		
			//3.执行操作
		    ps.executeUpdate();
			//4.取数据
		 
		    System.out.println("!!!修改!!");
		
			//5.关闭流
		
			if(ps!=null) ps.close();
			
			if(conn!=null) conn.close();
			
		} catch (Exception e) {
			
		}finally{
			
		}
	}

	@Override
	public List<User> userList() {
		List<User> list = new ArrayList<User>();
		Connection conn = null;
		PreparedStatement ps = null;
	    ResultSet rs = null;
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			
			//2.创建PreparedStatement对象
			String sql = "select * from t_user";
		 
		    ps = conn.prepareStatement(sql);
			
			
			rs = ps.executeQuery();
			User user = null;
			while(rs.next()){
			
				user = new User(rs.getString("uesrid"), rs.getString("UEMAIL"), rs.getString("uloginid"), rs.getString("upassword"), rs.getString("usex"), rs.getString("utel"),rs.getString("uaddress"), rs.getString("uroleid"), rs.getString("ustateid"));
			    list.add(user);
			}
	
			//3.执行操作
		   // ps.executeUpdate();
			//4.取数据
		 
			
		
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
	public void removeUser(String id) {
		
		Connection conn = null;
		PreparedStatement ps = null;
	   System.out.println("山粗话");
	
	
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			
			//2.创建PreparedStatement对象
			String sql = "delete t_user where uesrid=?";
			
		 
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			System.out.println(id);
		
			//3.执行操作
		    ps.executeUpdate();
			//4.取数据
		 
		    System.out.println("1234!");
		
			//5.关闭流
		
			if(ps!=null) ps.close();
			
			if(conn!=null) conn.close();
			
		} catch (Exception e) {
			
		}finally{
			
		}
		
	}

}
