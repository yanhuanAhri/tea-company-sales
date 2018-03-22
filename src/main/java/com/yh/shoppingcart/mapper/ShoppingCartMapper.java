package com.yh.shoppingcart.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yh.entity.ShoppingCart;
import com.yh.entity.ShoppingCartVo;

@Repository
@Mapper
public interface ShoppingCartMapper {
	
	
	public Long saveShoppingCart(@Param("shoppingCart")ShoppingCart shoppingCart);
	
	public void modifyShoppingCart(@Param("shoppingCart")ShoppingCart shoppingCart);
	
	public void deleteShoppingCartByCommodityNum(@Param("commodityNumList")List<String> commodityNumList,@Param("userId")Long userId);
	//public void modifyCommodity(@Param("commodity") Commodity commodity);
	
	public List<ShoppingCartVo> findByCreateUserIdAndStatus(@Param("userId")Long userId,@Param("status")Integer status);
	
	public ShoppingCart findByCommodityNum(@Param("commodityNum")String commodityNum,@Param("userId")Long userId);
	
	public Integer getCount(@Param("userId")Long userId,@Param("status")Integer status);

}
