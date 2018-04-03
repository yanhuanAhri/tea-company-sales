package com.yh.sales.user.service;

import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/*@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("qq")*/
@Service
public class SendMail {
	
	 @Autowired
     private JavaMailSender mailSender; //自动注入的Bean

	    @Value("${spring.mail.username}")
	    private String Sender; //读取配置文件中的参数

	    //简单文本邮件
	    public void sendSimpleMail(String userEmail) throws Exception {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setFrom(Sender);
	        message.setTo(userEmail); //自己给自己发送邮件
	        message.setSubject("主题：简单邮件");
	        message.setText("测试邮件内容");
	        mailSender.send(message);
	    }
	    //HTML邮件  激活链接
	    public void sendHtmlMail(String userEmail) {
	        MimeMessage message = null;
	        try {
	            message = mailSender.createMimeMessage();
	            MimeMessageHelper helper = new MimeMessageHelper(message, true);
	            helper.setFrom(Sender);
	            helper.setTo(userEmail);//userEmail
	            helper.setSubject("茶叶公司销售系统激活链接");

	            StringBuffer sb = new StringBuffer();
	            sb.append("<h1 style='text-align: center;'>茶叶公司销售系统激活链接</h1>")
	                    .append("<p style='text-align: center;'> 请复制该链接到浏览器打开激活账号：<a style='color:blue;'>").append(userEmail).append("</a></p>");
	                    
	            helper.setText(sb.toString(), true);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        mailSender.send(message);
	    }
}
