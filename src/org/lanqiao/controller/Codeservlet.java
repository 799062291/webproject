package org.lanqiao.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.net.aso.g;

/**
 * Servlet implementation class Codeservlet
 */
@WebServlet(name = "Codeservlet.do", urlPatterns = { "/Codeservlet.do" })
public class Codeservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   //验证码：动态生成内存中的一张验证图片（图片中显示的是验证字符）
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String chars="QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890";
	   //1.生成验证字符
		//创建一个随机对象；
		String codes = "";
		StringBuilder builder = new StringBuilder();
		Random rand = new Random();
		for(int i = 0;i<4;i++){
		int index = rand.nextInt(61);//[0,61]
		builder.append(chars.charAt(index));
		}
		codes = builder.toString();
		//2.生成一个内存中的图片，在图片中写入验证字符
		//A.创建一个内存中的图片
		BufferedImage img = new BufferedImage(90, 30, BufferedImage.TYPE_3BYTE_BGR);
		//B.绘制图片
		//拿到一个画笔-->绘制图片以及图片的内容
		Graphics gs = img.getGraphics();
		gs.setColor(Color.WHITE); //设置画笔颜色
		gs.fillRect(0, 0, 90, 30); //填充白色背景
		gs.setFont(new Font("宋体",Font.BOLD, 20));
		//画边框；
		gs.setColor(Color.RED);
		gs.drawRect(1, 1, 88, 28);//画边框
		
		
		//画验证字符
		gs.drawString(codes, 20, 20);
		//画干扰线
		gs.setColor(Color.GREEN);
		for(int i = 0;i<5;i++){
			gs.drawLine(rand.nextInt(90), rand.nextInt(30), rand.nextInt(90), rand.nextInt(30));
		}
		//将验证字符存到session
		request.getSession().setAttribute("code", codes);
		//填充颜色
	
		
		//3.输出图片
		//制定输出图片为图片格式数据
		response.setContentType("image/jpeg");
		ImageIO.write(img, "jpg", response.getOutputStream());
	    response.getOutputStream().flush();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
