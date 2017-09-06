package org.lanqiao.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
//过滤器，拦截请求：1.初始化过滤器（init）；2.拦截请求（doFilter）；3.释放资源（destroy）；
public class CharacterFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// chain:放行对象;
		//1.设置服务器处理的字符编码：
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		chain.doFilter(request, response);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
