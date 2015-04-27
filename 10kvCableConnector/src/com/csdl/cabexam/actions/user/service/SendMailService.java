package com.csdl.cabexam.actions.user.service;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.*;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class SendMailService{
	
	public void sendEmail(String propertyPath,String actionPath,String userGetEamil){
		try{
		String userName = "";
		String passwd="";
		String userSendEmail="";
		InputStream in = new BufferedInputStream(new FileInputStream(propertyPath));   
		Properties property = new Properties();   
		property.load(in); 
		if(property.containsKey("mail.user")){
			userName=property.getProperty("mail.user");
		}
		if(property.containsKey("mail.passwd")){
			passwd=property.getProperty("mail.passwd");
		}
		if(property.containsKey("userSendEmail")){
			userSendEmail=property.getProperty("userSendEmail");
		}
		Session session=Session.getInstance(property);
		session.setDebug(true);
		//创建代表邮件的对象MimeMessage
		MimeMessage message = new MimeMessage(session);
		message.setSubject("10KV电缆接头考证管理系统--重置密码邮件");
		message.setFrom(new InternetAddress(userSendEmail));
		message.setRecipients(Message.RecipientType.TO, userGetEamil);
		MimeBodyPart part1 = new MimeBodyPart();
		part1.setContent("<a href='"+actionPath+"'>点击修改密码</a>", "text/html;charset=utf-8");	
		//描述各部分之间的关系MimeMultiPart
		MimeMultipart multi = new MimeMultipart();
		multi.addBodyPart(part1);
		//加到MimeMessage中
		message.setContent(multi);
		message.saveChanges();
		//发送
		Transport t=session.getTransport();
		t.connect(userName, passwd);
		t.sendMessage(message,message.getAllRecipients());
		t.close();
		}catch(Exception e){
			e.printStackTrace();
		}
				
	}
//	public static void main(String[] args) {
//		SendMailService  s = new SendMailService();
//			s.sendEmail("D:\\tomcat\\apache-tomcat-7.0.52\\webapps\\10kvCableConnector\\WEB-INF\\classes\\email.properties", "http://localhost:8080/10kvCableConnector/doSendEmail.action","zhangchaoss122@163.com");
//	}
}
