package org.lanqiao.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class RequestListener implements ServletRequestListener{

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("request 请求处理结束！");
	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("request 请求处理开始！");
	}

}
