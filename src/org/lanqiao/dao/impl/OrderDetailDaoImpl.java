package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.lanqiao.dao.OrderDetailDao;
import org.lanqiao.entity.Order;
import org.lanqiao.entity.OrderDetail;
import org.lanqiao.util.DBUtil;

public class OrderDetailDaoImpl implements OrderDetailDao {

	@Override
	public void insert(OrderDetail orderDetail) {
		Connection conn = null;
		PreparedStatement ps = null;
	
	
	
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			
			//2.创建PreparedStatement对象
			String sql = "insert into t_orderdetail values(?,?,?,?,?,?)";
		    Date date = new Date();
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderDetail.getOrderdetailid());
			ps.setString(2, orderDetail.getGtitle());
			ps.setDouble(3, orderDetail.getGsaleprice());
			ps.setInt(4, orderDetail.getGnumber());
			ps.setString(5, orderDetail.getOrderid());
			ps.setString(6, orderDetail.getGid());
		
		
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
	public List<OrderDetail> list(String id) {
		List<OrderDetail> list = new ArrayList<OrderDetail>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			System.out.println("谁在呼唤我！？"+id);
			//2.创建PreparedStatement对象
			String sql = "select * from t_orderdetail where orderid = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			//3.执行操作
			OrderDetail order = null;
			while(rs.next()){
				System.out.println("yyoy!");
				order = new OrderDetail(rs.getString("orderdetailid"), rs.getString("gname"), rs.getDouble("gsalprice"), rs.getString("gid"), rs.getInt("gnumber"), rs.getString("orderid"));
			    list.add(order);
			}
			//4.取数据
		  
			System.out.println("谁在！？"+list.size());
		
			//5.关闭流
			if(rs!=null) rs.close();
			
			if(ps!=null) ps.close();
			
			if(conn!=null) conn.close();
			
		} catch (Exception e) {
			
		}finally{
			
		}
		return list;
	}

}
