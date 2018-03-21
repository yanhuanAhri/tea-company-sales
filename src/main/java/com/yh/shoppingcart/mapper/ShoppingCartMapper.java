package com.yh.shoppingcart.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yh.entity.Commodity;
import com.yh.entity.ShoppingCart;

@Repository
@Mapper
public interface ShoppingCartMapper {
	
	
	public Long saveShoppingCart(@Param("shoppingCart")ShoppingCart shoppingCart);
	
	public void modifyShoppingCart(@Param("shoppingCart")ShoppingCart shoppingCart);
	
	public void deleteShoppingCartByCommodityNum(List<String> commodityNumList);
	//public void modifyCommodity(@Param("commodity") Commodity commodity);

}
