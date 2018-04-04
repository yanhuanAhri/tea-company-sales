package com.yh.sales.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yh.entity.User;

@Repository
@Mapper
public interface UserMapper {
   /* @Insert("INSERT INTO tb_user(login_name, password) VALUES(#{loginName},#{password})")  
    public int insertUser(@Param("loginName") String loginName, @Param("password")  String password); */ 
    
   
    
     /**  
     * 插入用户，并将主键设置到user中  
     * 注意：返回的是数据库影响条数，即1
     */  
   /* public int insertUserWithBackId(User user); */
    
    public User findByUserName(@Param("userName") String userName);
    public User findByEmail(@Param("email") String email);
    public User findByPhone(@Param("phone") String phone);
    public User findById(@Param("id")Long id);
    
    public void modifyUser(@Param("user")User user);
    
    public void saveUser(@Param("user")User user);
    public void deleteUser(@Param("email")String email,@Param("phone")String phone);
}
