package com.yh.commodity.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yh.entity.Commodity;

@Repository
@Mapper
public interface CommodityMapper {
	  /* @Insert("INSERT INTO tb_user(login_name, password) VALUES(#{loginName},#{password})")  
    public int insertUser(@Param("loginName") String loginName, @Param("password")  String password); */ 
	
	public Long saveCommodity(@Param("commodity") Commodity commodity);
	
	public void modifyCommodity(@Param("commodity") Commodity commodity);
	
	public Commodity findById(@Param("id")Long id,@Param("commodityId")String commodityId);
}
