package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.lanqiao.dao.activationDao;
import org.lanqiao.util.DBUtil;

public class activationDaoImpl implements activationDao {

	@Override
	public void activation(String id) {
		Connection conn = null;
		PreparedStatement ps = null;	
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			
			//2.创建PreparedStatement对象
			String sql = "update t_user set ustateid = 'B5868B7A06E54DAEB19658343D3A2B28' where uesrid = ?";
			
		   
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			 System.out.println("!!!!!!"+id);
			//3.执行操作
		    ps.executeUpdate();
			//4.取数据
		    System.out.println("66666"+id);
			
		
			//5.关闭流
		
			if(ps!=null) ps.close();
			
			if(conn!=null) conn.close();
			
		} catch (Exception e) {
			
		}finally{
			
		}
		

	}

}
