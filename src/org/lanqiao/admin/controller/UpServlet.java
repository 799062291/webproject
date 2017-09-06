package org.lanqiao.admin.controller;

import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.lanqiao.entity.Goods;
import org.lanqiao.service.GoodsService;
import org.lanqiao.service.impl.GoodsServiceImpl;

@WebServlet(urlPatterns="/UpServlet.do")  //等价于在配置文件里发布该Servlet
@MultipartConfig   //设置为支持文件上传
public class UpServlet extends HttpServlet{

	/**
	 * 
	 */
	int i =1;
	private static final long serialVersionUID = 1L;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	 //设置编码格式：
    	request.setCharacterEncoding("UTF-8");
    	response.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html;charset=UTF-8");
    	 //获取对上传文件执行操作的组件Part：
    	Part part = request.getPart("filename");//获取file类型框的name
    	System.out.println(part);
         //获取放置上传文件的路径地址
    	//String uppath = this.getServletContext().getRealPath("/Files");
    	  String uppath = System.getProperty("user.dir")+"\\workspace\\web-project\\WebContent\\images\\bookcover\\";
        
    	System.out.println("!!!"+uppath);
        //获取请求的信息（上传文件的名字、类型）
    	String headname = part.getHeader("content-disposition");
    	//获取文件类型
    	String ext = headname.substring(headname.lastIndexOf("."),headname.length()-1);
    	//设置上传文件存放的路径和文件名
    	String name = null;
    	if(headname.lastIndexOf("\\")==-1){
    	   name = headname.substring(headname.lastIndexOf("=")+2,headname.lastIndexOf("."));
    	}else{
    	 name = headname.substring(headname.lastIndexOf("\\")+1,headname.lastIndexOf("."));
    	}
    	
    	System.out.println("名字："+name);
    	String filename = uppath+"\\"+name+ext;
    	
    	//String imgs = "simg"+i;
    	//写入文件
    	part.write(filename);
    	i++;
    	System.out.println("上传成功！");
    	//----------------------设置数据库
    	String gid = request.getParameter("gid1");
		String gtitle = request.getParameter("gtitle1");
		String gauthor = request.getParameter("gauthor1");
		String gsaleprice = request.getParameter("gsaleprice1");
		String ginprice = request.getParameter("ginprice1");
		String gdesc = request.getParameter("gdesc1");
		
		String gclick = request.getParameter("gclick1");
		String cid = request.getParameter("cid1");
		String pid = request.getParameter("pid1");
		GoodsService gs = new GoodsServiceImpl();
		Goods goods = new Goods(gid, gtitle, gauthor, Double.parseDouble(gsaleprice), Double.parseDouble(ginprice), gdesc, name, Integer.parseInt(gclick), cid, pid);
		
		gs.changeGoods(goods);
		//-----------------------------------------------
    	//输出存放路径
		System.out.println("id:"+gid+"gtitle"+gtitle+"cid:"+cid);
    	System.out.println(filename);
    	String url = request.getRequestURL().toString();
    	url = url.substring(0, url.lastIndexOf("/")+1)+"admin/list.html";
    	response.sendRedirect(url);
    }
	
}
