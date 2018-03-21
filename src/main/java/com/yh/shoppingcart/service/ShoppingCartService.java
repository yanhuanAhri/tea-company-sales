package com.yh.shoppingcart.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yh.commodity.mapper.CommodityMapper;
import com.yh.entity.Commodity;
import com.yh.entity.ShoppingCart;
import com.yh.entity.User;
import com.yh.shoppingcart.mapper.ShoppingCartMapper;

@Service
public class ShoppingCartService {
	@Autowired
	private ShoppingCartMapper shoppingCartMapper;
	@Autowired
	private CommodityMapper commodityMapper;
	
	public void saveShoppingCart(String commodityNum,Integer buyNum,User user) {
		ShoppingCart shoppingCart=new ShoppingCart();
		if(commodityNum!=null && buyNum!=0) {
			Commodity commodity=commodityMapper.findOneById(null, commodityNum);
			shoppingCart.setCommodityId(commodity.getId());
			shoppingCart.setCommodityNum(commodity.getCommodityNum());
			
			shoppingCart.setCommodityTitle(
					"【"+commodity.getTradeName()+"】 "+commodity.getTeaName()+" "+commodity.getPickYear()+" "
				   +commodity.getPickSeason()+" "+commodity.getProductType()+" "+commodity.getGoodsGrade()
				   +" "+commodity.getNetContent()+"克 "+(commodity.getPurpose()==1 ? "自饮":"礼盒"));
			shoppingCart.setBuyNum(buyNum);
			shoppingCart.setUnitPrice(commodity.getPromotionPrice());
			shoppingCart.setCreateTime(new Date());
			shoppingCart.setCreateUserId(user.getId());
			Long id=shoppingCartMapper.saveShoppingCart(shoppingCart);
		}
	}
	
	public void updateShoppingCart(String commodityNum,Integer buyNum,User user) {
		
	}

}
