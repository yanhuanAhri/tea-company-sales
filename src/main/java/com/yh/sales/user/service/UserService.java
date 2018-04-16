package com.yh.sales.user.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.yh.core.util.DesUtil;
import com.yh.core.util.EncryptionUtil;
import com.yh.core.util.URLBuilder;
import com.yh.entity.User;
import com.yh.sales.user.mapper.UserMapper;

@Service
public class UserService {
	
	 @Autowired  
	 private UserMapper userMapper; 
	 @Autowired
	 private SendMail sendMail;
	 
	 @Value("${disc.out.data.time}")
	 private String discOutDateTime;
	 @Value("${des.key}")
	 private String desDey;
	 @Value("${encryption.way}")
	 private String encryptionWay;
	 
	 
	 
		
    
	/**
	 * 用户登录
	 * @param account
	 * @param password
	 * @return
	 */
	public User login(String account,String password) {
		  User accountUser=userMapper.findByUserName(account);
		  User emailUser=userMapper.findByEmail(account);
		  User phoneUser=userMapper.findByPhone(account);
		  password= EncryptionUtil.getHash(password,encryptionWay);
		  User user=new User();
		  if(accountUser!=null && !String.valueOf(accountUser).isEmpty() && accountUser.getPassword().equals(password)) {
			  user=accountUser;
		  }else if(emailUser!=null && !String.valueOf(emailUser).isEmpty() && emailUser.getPassword().equals(password)) {
			  user= emailUser;
		  }else  if(phoneUser!=null && !String.valueOf(phoneUser).isEmpty() && phoneUser.getPassword().equals(password)) {
			  user= phoneUser;
		  }
		  if(user.getIsActive().intValue()==1) {
			  return user;
		  }
		  return new User();
	  }
  
	/**
	 * 用户注册
	 * @param user
	 */
	public Boolean register(String msg) {
		  if(StringUtils.isNotBlank(msg)) {
			 try {
				String data=URLDecoder.decode(msg,"UTF-8");
				  String[] arr=data.split("&");
				 // String[] account=arr[0].split("=");
				  String email=null,password=null,phone=null;
				  for(int j=0;j<arr.length;j++) {
						if(arr[j].contains("email")) {
							email=arr[j].split("=")[1];
						}else if(arr[j].contains("phone")) {
							phone=arr[j].split("=")[1];
						}else if(arr[j].contains("password")) {
							password=arr[j].split("=")[1];
							 password= EncryptionUtil.getHash(password,encryptionWay);
						}
					}
				  User user=new User();
				  if(email!=null) {
					  user.setEmail(email);
					  user.setNickName(email);
					  user.setUserName(email);
					  User emailUser=userMapper.findByEmail(email);
					  if(emailUser!=null) {
						  if(emailUser.getIsActive()==1) {
							  return false;
						  }else {
							  userMapper.deleteUser(email, null);
						  }
					  }
					  sendMail.sendHtmlMail(email);
				  }else if(phone!=null) {
					  user.setUserName(phone);
					  user.setPhone(phone);
					  user.setNickName(phone);
				  }
				  user.setPassword(password);
				  user.setSex(1);
				  user.setCreateTime(new Date());
				  user.setPaymentCode(password);
				  user.setIsActive(0);
				  user.setUpdateTime(new Date());
					  userMapper.saveUser(user);
					  return true;
			  } catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			 }
		  }
		return false;
	  }
	
	/**
	 * 账户激活
	 * @param token
	 * @return
	 */
	public boolean isActive(String token) {
		
		String userEmail=verifyActiveLink(token);
		if(userEmail==null) {
			return false;
		}else {
			User user=new User();
			user.setEmail(userEmail);
			user.setIsActive(1);
			userMapper.modifyUser(user);
			return true;
		}
	}
	
    /**
     * 验证激活链接
     * @param token
     * @return userEmail
     */
    private String verifyActiveLink(String token) {
    	String str=null;
		try {
			try {
				str=DesUtil.decrypt(token, desDey);
			} catch (Exception e) {
				token= URLDecoder.decode(token,"utf-8");
				str=DesUtil.decrypt(token, desDey);
			}
			String[] arr=str.split("&");
			//验证userEmail
			String[] emailArr= arr[0].split("=");
			if(!emailArr[0].equals("userEmail")) {
				return null;
			}
			User user=userMapper.findByEmail(emailArr[1]);
			if(user==null ) {
				return null;
			}
			String[] createTimeArr=arr[1].split("=");
			if (!createTimeArr[0].equals("createTime")) {
				return null;
			}
			Date nowDate=new Date();
			Long interval= (nowDate.getTime() - Long.valueOf(createTimeArr[1]))/1000; // 两个时间相差秒数
			if(interval>(Long.valueOf(discOutDateTime)*60)){
				return null;
			}
			return emailArr[1];
		}catch (IOException e) {
			e.getMessage();
			return null;
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
    }
    public User findByUserName(String userName) {
    	 User user=userMapper.findByUserName(userName);
    	 return user;
    }
}
