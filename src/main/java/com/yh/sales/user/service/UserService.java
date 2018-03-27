package com.yh.sales.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yh.entity.User;
import com.yh.sales.user.mapper.UserMapper;

@Service
public class UserService {
  @Autowired  
  private UserMapper userMapper;  
      
    
  public User login(String account,String password) {
	  User user=userMapper.findByUserName(account);
	  User emailUser=userMapper.findByEmail(account);
	  User phoneUser=userMapper.findByPhone(account);
	  if(user!=null && !String.valueOf(user).isEmpty() && user.getPassword().equals(password)) {
		  return user;
	  }else if(emailUser!=null && !String.valueOf(emailUser).isEmpty() && emailUser.getPassword().equals(password)) {
		  return emailUser;
	  }else  if(phoneUser!=null && !String.valueOf(phoneUser).isEmpty() && phoneUser.getPassword().equals(password)) {
		  return phoneUser;
	  }
	return new User();
	  
  }
  
    public User findByUserName(String userName) {
    	 User user=userMapper.findByUserName(userName);
    	 return user;
    }
}
