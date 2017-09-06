package org.lanqiao.admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.lanqiao.entity.Goods;
import org.lanqiao.entity.PageInfo;
import org.lanqiao.service.GoodsService;
import org.lanqiao.service.impl.GoodsServiceImpl;
import org.lanqiao.util.IOUtil;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class GoodsController
 */
@WebServlet(name = "goodsController", urlPatterns = { "/goodsController.do" })
@MultipartConfig   //设置为支持文件上传
public class GoodsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		GoodsService gs = new GoodsServiceImpl();
		
		if(type!=null&&type.equals("list")){
		String cid = request.getParameter("cid");
		int pageIndex = Integer.parseInt(request.getParameter("page"));
	    int pageSize = Integer.parseInt(request.getParameter("rows")); //从0开始
	    System.out.println("转页啦！"+pageIndex);
		PageInfo pageInfo = gs.GoodsList(cid, pageSize, pageIndex);
		//easyui datagrid 分页的数据格式要求{total:1000,rows:datas}必须要有两个参数
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", pageInfo.getTotalNumber());
		map.put("rows", pageInfo.getDatas());
		Gson gson = new Gson();
		response.getWriter().write(gson.toJson(map));
		}else if(type!=null&&type.equals("remove")){
			String id = request.getParameter("gid");
			gs.removeGoods(id);
			response.getWriter().write("1");
		}else if(type!=null&&type.equals("edit")){
			String gid = request.getParameter("gid");
			String gtitle = request.getParameter("gtitle");
			String gauthor = request.getParameter("gauthor");
			String gsaleprice = request.getParameter("gsaleprice");
			String ginprice = request.getParameter("ginprice");
			String gdesc = request.getParameter("gdesc");
			String gimgs = request.getParameter("gimg");
			String gimg = new String(gimgs.getBytes("ISO-8859-1"),"UTF-8"); //转换中文乱码		
			String gclick = request.getParameter("gclick");
			String cid = request.getParameter("cid");
			String pid = request.getParameter("pid");
			IOUtil io = new IOUtil();
			
		  if(gimg.equals("保持原路径不变")){
			  Goods goods = new Goods(gid, gtitle, gauthor, Double.parseDouble(gsaleprice), Double.parseDouble(ginprice), gdesc, gimg, Integer.parseInt(gclick), cid, pid);
				
				gs.updateGoods(goods);
		  }else{
			  String uppath = System.getProperty("user.dir")+"\\workspace\\web-project\\WebContent\\images\\bookcover\\";
			   
				
				
				io.up(gimg, uppath);
				String imgs = gimg.substring(gimg.lastIndexOf("\\")+1,gimg.lastIndexOf("."));
				System.out.println("商品名称："+imgs);
				Goods goods = new Goods(gid, gtitle, gauthor, Double.parseDouble(gsaleprice), Double.parseDouble(ginprice), gdesc, imgs, Integer.parseInt(gclick), cid, pid);
				
				gs.changeGoods(goods);
		  }
			
			response.getWriter().write("1");
		}else if(type!=null&&type.equals("add")){
			String gid = UUID.randomUUID().toString();
			String gtitle = request.getParameter("gtitle");
			String gauthor = request.getParameter("gauthor");
			String gsaleprice = request.getParameter("gsaleprice");
			String ginprice = request.getParameter("ginprice");
			String gdesc = request.getParameter("gdesc");
			String gimg = request.getParameter("gimg");
			String gclick = request.getParameter("gclick");
			String cid = request.getParameter("cid");
			String pid = request.getParameter("pid");
			Goods goods = new Goods(gid, gtitle, gauthor, Double.parseDouble(gsaleprice), Double.parseDouble(ginprice), gdesc, gimg, Integer.parseInt(gclick), cid, pid);
			
			gs.insertGoods(goods);
			response.getWriter().write("1");
		}else if(type!=null&&type.equals("search")){
			String data = request.getParameter("data");
			System.out.println(data);
			TypeToken<List<Goods>> listType = new TypeToken<List<Goods>>(){
				
			};
			Gson gson = new Gson();
			List<Goods> list = gson.fromJson(data, listType.getType());
			System.out.println("--->"+list.size());
			//easyui datagrid 分页的数据格式要求{total:1000,rows:datas}必须要有两个参数
			Map<String, Object> map = new HashMap<String, Object>();
			/*map.put("total", pageInfo.getTotalNumber());
			map.put("rows", pageInfo.getDatas());*/
			
			response.getWriter().write(gson.toJson(map));
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
