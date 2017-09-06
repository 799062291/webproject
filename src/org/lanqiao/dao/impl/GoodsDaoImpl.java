package org.lanqiao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.lanqiao.dao.GoodsDao;
import org.lanqiao.entity.Category;
import org.lanqiao.entity.Goods;
import org.lanqiao.entity.PageInfo;
import org.lanqiao.util.DBUtil;



public class GoodsDaoImpl implements GoodsDao {

	@Override
	public PageInfo<Goods> list(String cid,int pageSize, int pageIndex) {
		
		PageInfo<Goods>  pageInfo = new PageInfo<Goods>();
		List<Goods> list = new ArrayList<Goods>();
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			
			//2.创建PreparedStatement对象
			String sql = "select * from (select A.*,rownum rn from ( select * from t_goods where cid = ?)A where rownum<=?)where rn >=?";
			String sql1="select cname from t_category where cid=?";
			ps = conn.prepareStatement(sql);
			ps1 = conn.prepareStatement(sql1);
			int endIndex = pageSize*pageIndex;
			int startInedex = (pageIndex-1)*pageSize+1;
			ps1.setString(1, cid);
			ps.setString(1, cid);
		    ps.setInt(2, endIndex);  //设置语句里的参数；
			ps.setInt(3, startInedex);
			//3.执行操作
			rs = ps.executeQuery();
			
			//4.取数据
			Goods goods = null;
			
			
			while (rs.next()) {
				goods = new Goods(rs.getString("gid"), rs.getString("gtitle"), rs.getString("gauthor"), rs.getDouble("gsaleprice"), rs.getDouble("ginprice"), rs.getString("gdesc"), rs.getString("gimg"), rs.getInt("gclicks"),rs.getString("cid"),rs.getString("pid"));
				list.add(goods);
			    
			}
			pageInfo.setDatas(list);
			pageInfo.setIsFirstPage(pageIndex==1);
			int totalnumber = totalRecords(cid);
		
			int  totalPages = totalnumber % pageSize == 0? totalnumber /pageSize:(totalnumber /pageSize)+1;
			pageInfo.setTotalPages(totalPages);
			pageInfo.setIsLastPage(totalPages==pageIndex);
			pageInfo.setPageIndex(pageIndex);
			pageInfo.setPageSize(pageSize);
			pageInfo.setTotalNumber(totalnumber);
			rs = ps1.executeQuery();
			if(rs.next()){
				pageInfo.setType(rs.getString(1));
			}
			//5.关闭流
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(ps1!=null) ps1.close();
			if(conn!=null) conn.close();
		} catch (Exception e) {
			
		}finally{
			
		}
		return pageInfo;
	}

	@Override
	public int totalRecords(String id) {
		int total = 0;
		//1.拿到连接；
		Connection conn = DBUtil.getConnection();
		//2.根据sql语句创建PreparedStatement对象
		String sql = "select count(*) from t_goods where cid = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				total = rs.getInt(1);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return total;
	}
	
	@Override
	public int totalRecords1(String id) {
		int total = 0;
		//1.拿到连接；
		Connection conn = DBUtil.getConnection();
		//2.根据sql语句创建PreparedStatement对象
		String sql = "select count(*) from t_goods where gtitle like ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+id+"%");
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				total = rs.getInt(1);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return total;
	}


	@Override
	public Goods get(String id) {
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		Goods goods = null;
		String publisher = null;
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			
			//2.创建PreparedStatement对象
			String sql = "select * from t_goods where gid = ?";
			
		
			ps = conn.prepareStatement(sql);
			
		
			ps.setString(1, id);
	
			//3.执行操作
			rs = ps.executeQuery();
			
			//4.取数据
		
			
			while (rs.next()) {
				goods = new Goods(rs.getString("gid"), rs.getString("gtitle"), rs.getString("gauthor"), rs.getDouble("gsaleprice"), rs.getDouble("ginprice"), rs.getString("gdesc"), rs.getString("gimg"), rs.getInt("gclicks"),rs.getString("cid"),rs.getString("pid"));
			    
			}
			String sql1 = "select pname from t_publisher where pid = ?";
			ps1 = conn.prepareStatement(sql1);
			ps1.setString(1, goods.getPid());
			rs = ps1.executeQuery();
			if(rs.next()){
				 publisher = rs.getString(1);
			}
			goods.setPulish(publisher);
			//5.关闭流
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(ps1!=null) ps1.close();
			if(conn!=null) conn.close();
			
		} catch (Exception e) {
			
		}finally{
			
		}
		return goods;
		
	}

	@Override
	public void remove(String id) {
		Connection conn = null;
		PreparedStatement ps = null;
	   System.out.println("山粗话");
	
	
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			
			//2.创建PreparedStatement对象
			String sql = "delete t_goods where gid=?";
			
		 
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

	@Override
	public void update(Goods goods) {
		Connection conn = null;
		PreparedStatement ps = null;
	  
	
	
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			
			//2.创建PreparedStatement对象
			String sql = "update t_goods set gtitle=?,gauthor=?,gsaleprice=?,ginprice=?,cid=?,pid=?,gclicks=? where gid=?";
			
		   System.out.println("yoyoy");
			ps = conn.prepareStatement(sql);
			ps.setString(1, goods.getGtitle());
			ps.setString(2, goods.getGauthor());
			ps.setDouble(3, goods.getGsaleprice());
			ps.setDouble(4, goods.getGinprice());
			ps.setString(5, goods.getCid());
			ps.setString(6, goods.getPid());
			ps.setInt(7, goods.getGclick());
			ps.setString(8, goods.getGid());
		
		
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

	@Override
	public void insert(Goods goods) {
		Connection conn = null;
		PreparedStatement ps = null;
	    System.out.println("123");
	
	
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			
			//2.创建PreparedStatement对象
			String sql = "insert into t_goods values(?,?,?,?,?,?,?,?,?,?)";
			
		 
			ps = conn.prepareStatement(sql);
			ps.setString(1, goods.getGid());
			ps.setString(2, goods.getGtitle());
			ps.setString(3, goods.getGauthor());
			ps.setDouble(4, goods.getGsaleprice());
			ps.setDouble(5, goods.getGinprice());
			ps.setString(6, goods.getGdesc());
			ps.setString(7, goods.getGimg());
			ps.setInt(8, goods.getGclick());
			ps.setString(9, goods.getCid());
			ps.setString(10, goods.getPid());
		   System.out.println(goods.toString());
			//3.执行操作
		    ps.executeUpdate();
			//4.取数据
		 
			System.out.println("charu!");
		
			//5.关闭流
		
			if(ps!=null) ps.close();
			
			if(conn!=null) conn.close();
			
		} catch (Exception e) {
			
		}finally{
			
		}
		
	}

	@Override
	public void changeImg(Goods goods) {
		Connection conn = null;
		PreparedStatement ps = null;
	  
	
	
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			
			//2.创建PreparedStatement对象
			String sql = "update t_goods set gtitle=?,gauthor=?,gsaleprice=?,ginprice=?,cid=?,pid=?,gclicks=?,gimg=? where gid=?";
			
		   System.out.println("开始！");
			ps = conn.prepareStatement(sql);
			ps.setString(1, goods.getGtitle());
			ps.setString(2, goods.getGauthor());
			ps.setDouble(3, goods.getGsaleprice());
			ps.setDouble(4, goods.getGinprice());
			ps.setString(5, goods.getCid());
			ps.setString(6, goods.getPid());
			ps.setInt(7, goods.getGclick());
			ps.setString(8, goods.getGimg());
			ps.setString(9, goods.getGid());
		
		
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

	@Override
	public PageInfo<Goods> list1(String key, int pageSize, int pageIndex) {
		PageInfo<Goods>  pageInfo = new PageInfo<Goods>();
		List<Goods> list = new ArrayList<Goods>();
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		try {
			//1.获取链接
			conn = DBUtil.getConnection();
			
			//2.创建PreparedStatement对象
			String sql = "select * from (select t.*,rownum rn from(select * from t_goods where gtitle like ?) t where rownum <= ?) where rn >=?";
			
			ps = conn.prepareStatement(sql);
		
			int endIndex = pageSize*pageIndex;
			int startInedex = (pageIndex-1)*pageSize+1;
			ps.setString(1, "%"+key+"%");
		    ps.setInt(2, endIndex);  //设置语句里的参数；
			ps.setInt(3, startInedex);
			//3.执行操作
			System.out.println(endIndex +"----"+startInedex+"-------"+key);
			rs = ps.executeQuery();
			
			//4.取数据
			Goods goods = null;
			
			
			while (rs.next()) {
				goods = new Goods(rs.getString("gid"), rs.getString("gtitle"), rs.getString("gauthor"), rs.getDouble("gsaleprice"), rs.getDouble("ginprice"), rs.getString("gdesc"), rs.getString("gimg"), rs.getInt("gclicks"),rs.getString("cid"),rs.getString("pid"));
				list.add(goods);
			    
			}
			System.out.println("长度："+list.size());
			pageInfo.setDatas(list);
			pageInfo.setIsFirstPage(pageIndex==1);
			int totalnumber = totalRecords1(key);
		   
			int  totalPages = totalnumber % pageSize == 0? totalnumber /pageSize:(totalnumber /pageSize)+1;
			pageInfo.setTotalPages(totalPages);
			pageInfo.setIsLastPage(totalPages==pageIndex);
			pageInfo.setPageIndex(pageIndex);
			pageInfo.setPageSize(pageSize);
			pageInfo.setTotalNumber(totalnumber);
			
			
				pageInfo.setType("搜索结果");
			
			//5.关闭流
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
		
			if(conn!=null) conn.close();
		} catch (Exception e) {
			
		}finally{
			
		}
		return pageInfo;
	}



}
