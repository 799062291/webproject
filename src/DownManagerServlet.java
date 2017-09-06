

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownManagerServlet
 */
@WebServlet(name = "downManagerServlet", urlPatterns = { "/down.do" })
public class DownManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html;charset=utf-8"); 
		 PrintWriter out = response.getWriter(); 
		// 获取referer头的值 
		String referer = request.getHeader("referer"); 
		// 获取访问地址
		String sitePart = "http://" + request.getServerName(); 
		System.out.println("1--->"+referer);
		System.out.println("2--->"+sitePart);
		if (referer != null && referer.startsWith(sitePart)) { 
		// 处理正在下载的请求
		out.println("dealing download ...");   
		} else { 
		// 非法下载请求跳转到download.html页面
		RequestDispatcher rd = request.getRequestDispatcher("/download.html"); 
		rd.forward(request, response);  
		 
		} 

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
