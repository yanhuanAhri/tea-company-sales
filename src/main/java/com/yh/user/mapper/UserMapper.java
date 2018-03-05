package com.yh.user.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yh.entity.User;

@Repository
@Mapper
public interface UserMapper {
    @Insert("INSERT INTO tb_user(login_name, password) VALUES(#{loginName},#{password})")  
    public int insertUser(@Param("loginName") String loginName, @Param("password")  String password);  
    
    public com.yh.entity.User findByUserName(@Param("userName") String userName);
    
     /**  
     * 插入用户，并将主键设置到user中  
     * 注意：返回的是数据库影响条数，即1
     */  
    public int insertUserWithBackId(User user); 
}
