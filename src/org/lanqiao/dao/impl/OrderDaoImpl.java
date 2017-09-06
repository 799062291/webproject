package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.naming.java.javaURLContextFactory;
import org.lanqiao.dao.OrderDao;
import org.lanqiao.entity.Order;
import org.lanqiao.entity.User;
import org.lanqiao.util.DBUtil;

public class OrderDaoImpl implements OrderDao {

	public static Date getDate(java.sql.Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String s = sdf.format(date);
        Date d = null;
        	try {
				d = sdf.parse(s);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        

		return d;
		
	}
	
	
	@Override
	public void insert(Order order) {
	
		Connection conn = null;
		PreparedStatement ps = null;
	
	
	
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			
			//2.创建PreparedStatement对象
			String sql = "insert into t_order values(?,?,?,?)";
			System.out.println("!!!!名");
		    
			ps = conn.prepareStatement(sql);
			ps.setString(1, order.getOrderid());
			ps.setString(2, order.getUesrid());
			ps.setDouble(3, order.getTotalprice());
			java.sql.Date date = new java.sql.Date(order.getOrderDate().getTime());
			ps.setDate(4, date);
			
			System.out.println("!!!!名1");
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
	public List<Order> list() {
		List<Order> list = new ArrayList<Order>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			
			//2.创建PreparedStatement对象
			String sql = "select * from t_order";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			//3.执行操作
			Order order = null;
			while(rs.next()){
			
				order = new Order(rs.getString("orderid"), rs.getString("uesrid"), rs.getDouble("totalprice"),this.getDate(rs.getDate("orderdate")));
			    list.add(order);
			}
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
	public void update(Order order) {
		Connection conn = null;
		PreparedStatement ps = null;
	
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			
			//2.创建PreparedStatement对象
			String sql = "update t_order set uesrid = ?,totalprice=?,orderdate=? where orderid=?";
		    Date date = new Date();
		    java.sql.Date date2 = new java.sql.Date(date.getTime());
			ps = conn.prepareStatement(sql);
			ps.setString(1, order.getUesrid());
			ps.setDouble(2, order.getTotalprice());
			    
			java.sql.Date date1 = new java.sql.Date(order.getOrderDate().getTime());
			ps.setDate(3, date1);
			
			ps.setString(4, order.getOrderid());
			
			System.out.println("!!!!名1");
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
	public void delete(String id) {
		Connection conn = null;
		PreparedStatement ps = null;
	  
	
	
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			
			//2.创建PreparedStatement对象
			String sql = "delete t_order where orderid=?";
			
		 
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			
		
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
	public void totalprice(String id, double sum) {
		Connection conn = null;
		PreparedStatement ps = null;
	
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			
			//2.创建PreparedStatement对象
			String sql = "update t_order set totalprice= totalprice + ? where orderid=?";
			ps = conn.prepareStatement(sql);
			ps.setDouble(1,sum);
			ps.setString(2, id);
			
			
			System.out.println("!!!!名1");
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
	


}
