package org.lanqiao.util;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;


//需要两个jar包：
public class MailUtil {
	static final String STMPHOST = "smtp.qq.com";  //邮件服务器地址
    static final String AUTHPASSWORD = "wgozpgylumcxbbae";  //授权码
    static final String FROM ="799062291@qq.com";  //发送邮件的账号   
	public static void sendMail(String mailAddress,String subject,String content){
    	//1.建立与邮件服务器的会话；-- Session
        Properties props = new Properties(); //配置类，存放建立会话需要用到的参数
        props.setProperty("mail.smtp.host", STMPHOST);
        props.setProperty("mail.transport.protocol", "smtp"); //设置发送邮件的协议；
        props.setProperty("mail.smtp.auth", "true"); //设置验证；
        
        
           //qq邮箱开启ssl认证
      		MailSSLSocketFactory sf=null;
      		try {
      			sf = new MailSSLSocketFactory();
      		} catch (GeneralSecurityException e) {
      			// TODO Auto-generated catch block
      			e.printStackTrace();
      		}
      		sf.setTrustAllHosts(true);
      		props.put("mail.smtp.ssl.enable", "true");
      		props.put("mail.smtp.ssl.socketFactory", sf); 
        
		Session session = Session.getDefaultInstance(props);
    	//2.创建一封邮件；-- MimeMessage
    	MimeMessage message = new MimeMessage(session);
    	try {
			message.setFrom(new InternetAddress(FROM,"eShop","UTF-8"));//设置邮件的发送地址，发件人名称，编码；
			message.setSubject("eShop电子商城账号激活");//设置邮件的主题
			message.setSentDate(new Date()); //设置发送日期
			message.setContent(content,"text/html;charset=utf-8"); //设置发送内容
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(mailAddress,"eShop","UTF-8"));//设置发送样式（抄送/密送/发送）以及收件人地址
			//3.发送邮件；-- Transport
	    	Transport transport = session.getTransport();  //创建一个发送邮件对象；
	    	transport.connect(FROM, AUTHPASSWORD);  //通过授权码以发送账号身份连接服务器；
	    	transport.sendMessage(message,message.getAllRecipients()); //发送邮件，第二个参数是拿到上面设置的所有收件人
    	    transport.close();
    	} catch (UnsupportedEncodingException | MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	
    }
    //同时发送多人：
    public void sendMail(String subject,String content,String...mailAddress){
    	
    }
}
