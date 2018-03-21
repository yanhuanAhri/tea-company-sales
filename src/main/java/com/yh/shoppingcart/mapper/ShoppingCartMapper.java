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
	
	public void deleteShoppingCartByCommodityNum(List<String> commodityNumList);
	//public void modifyCommodity(@Param("commodity") Commodity commodity);
	
	public List<ShoppingCartVo> findByCreateUserIdAndStatus(@Param("createUserId")Long createUserId,@Param("status")Integer status);
	
	public ShoppingCart findByCommodityNum(@Param("commodityNum")String commodityNum);

}
