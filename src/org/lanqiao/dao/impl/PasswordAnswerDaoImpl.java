package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.lanqiao.dao.PasswordAnswerDao;
import org.lanqiao.entity.PasswordAnswer;
import org.lanqiao.entity.User;
import org.lanqiao.util.DBUtil;

public class PasswordAnswerDaoImpl implements PasswordAnswerDao{

	@Override
	public void insert(PasswordAnswer passwordAnswer) {
		Connection conn = null;
		PreparedStatement ps = null;
	
	
	
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			
			//2.创建PreparedStatement对象
			String sql = "insert into t_passwordanswer values(?,?,?,?,?)";
			
		
			ps = conn.prepareStatement(sql);
			ps.setString(1, passwordAnswer.getAnswerid());
			ps.setString(2, passwordAnswer.getUserid());
			ps.setString(3, passwordAnswer.getAquestion());
			ps.setString(4, passwordAnswer.getAnswer());
			ps.setString(5, passwordAnswer.getEmail());	
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
	public void update(PasswordAnswer passwordAnswer) {
		System.out.println("mima");
		Connection conn = null;
		PreparedStatement ps = null;
	
	
	
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			
			//2.创建PreparedStatement对象
			String sql = "update t_passwordanswer set aquestion=?,answer=?,email=?,uesrid=? where answerid=?";
		
		
			ps = conn.prepareStatement(sql);
			

			ps.setString(1, passwordAnswer.getAquestion());
			ps.setString(2, passwordAnswer.getAnswer());
			ps.setString(3, passwordAnswer.getEmail());	
			ps.setString(4, passwordAnswer.getUserid());
			ps.setString(5, passwordAnswer.getAnswerid());
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
	public PasswordAnswer get(String id) {
		PasswordAnswer passwordAnswer = null;
		Connection conn = null;
		PreparedStatement ps = null;
	    ResultSet rs = null;
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			
			//2.创建PreparedStatement对象
			String sql = "select * from t_passwordanswer where uesrid=?";
		 
		    ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			
			rs = ps.executeQuery();
			
			if(rs.next()){
				passwordAnswer = new PasswordAnswer(rs.getString("answerid"), rs.getString("aquestion"), rs.getString("answer"), rs.getString("email"), rs.getString("uesrid"));
			   
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
		return passwordAnswer;
		
	}

}
