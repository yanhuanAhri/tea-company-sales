package com.yh.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yh.entity.User;
import com.yh.user.mapper.UserMapper;

@Service
public class UserService {
  @Autowired  
  private UserMapper userMapper;  
      
    public boolean addUser(String username, String password){  
        return userMapper.insertUser(username, password)==1?true:false;  
    }  
      
    public User addUserWithBackId(String loginname, String password){  
        User user = new User();  
        user.setUserName(loginname);  
        user.setPassword(password);  
        userMapper.insertUserWithBackId(user);//该方法后，主键已经设置到user中了  
        return user;  
    }  
    
    public User findByUserName(String userName) {
    	return userMapper.findByUserName(userName);
    }
}
