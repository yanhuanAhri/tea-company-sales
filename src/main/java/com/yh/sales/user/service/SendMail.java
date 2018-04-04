package com.yh.sales.user.service;

import java.net.URLEncoder;
import java.util.Date;

import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.yh.core.util.DesUtil;
import com.yh.core.util.URLBuilder;

/*@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("qq")*/
/**
 * @author Administrator
 *
 */
@Service
public class SendMail {
	
	 @Autowired
     private JavaMailSender mailSender; //自动注入的Bean

	    @Value("${spring.mail.username}")
	    private String Sender; //读取配置文件中的参数
	    
		 @Value("${des.key}")
		 private String desDey;
		 @Value("${register.email.active.link}")
		 private String activeLink;
		 @Value("${tea.company.sales.host}")
		 private String teaHost;

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
	        String link=getActiveLink(userEmail);
        	if(link!=null) {
        		 try {
     	            message = mailSender.createMimeMessage();
     	            MimeMessageHelper helper = new MimeMessageHelper(message, true);
     	            helper.setFrom(Sender);
     	            helper.setTo(userEmail);//userEmail
     	            helper.setSubject("茶叶公司销售系统激活链接");
     	            StringBuffer sb = new StringBuffer();
     	            sb.append("<h1 style='text-align: center;'>茶叶公司销售系统激活链接</h1>")
     	                    .append("<p style='text-align: center;'> 请复制该链接到浏览器打开，激活账号：<a style='color:blue;'>").append(link).append("</a></p>");
     	                    
     	            helper.setText(sb.toString(), true);
     	        } catch (Exception e) {
     	            e.printStackTrace();
     	        }
     	        mailSender.send(message);
        	}
	    }
	    
	    
	    /**
	     * 激活链接
	     * @param userEmail
	     * @return
	     */
	    private String getActiveLink(String userEmail) {
			 URLBuilder url=new URLBuilder(teaHost+activeLink);
			 String data="userEmail="+userEmail+"&createTime="+new Date().getTime();
			 try {
				String token=DesUtil.encrypt(data, desDey);
				 url.param("token", URLEncoder.encode(token,"UTF-8"));
				 return url.toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
}
