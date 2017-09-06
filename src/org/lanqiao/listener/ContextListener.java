package org.lanqiao.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
//创建了一个ServletContextListener对象
public class ContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("application 对象消失了......");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("application 对象诞生了......");
	}

}
