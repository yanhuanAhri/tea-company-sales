package com.yh.shoppingcart.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
	
	/**
	 * 加入购物车
	 * @param commodityNum 商品编号
	 * @param buyNum 购买数量
	 * @param user 登录用户信息
	 */
	public void saveShoppingCart(String commodityNum,Integer buyNum,User user) {
		ShoppingCart shoppingCart=new ShoppingCart();
		if(commodityNum!=null && buyNum!=0) {
			ShoppingCart shoppingCommdity=shoppingCartMapper.findByCommodityNum(commodityNum);
			//购物车商品新增
			if(shoppingCommdity==null || shoppingCommdity.equals("")) {
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
				shoppingCart.setUpdateTime(new Date());
				Long id=shoppingCartMapper.saveShoppingCart(shoppingCart);
			}//购物车商品数量改变
			else {
				Commodity commodity=commodityMapper.findOneById(null, commodityNum);
				shoppingCart.setBuyNum(shoppingCommdity.getBuyNum()+buyNum);
				shoppingCart.setUnitPrice(commodity.getPromotionPrice());
				shoppingCart.setCommodityNum(commodityNum);
				shoppingCartMapper.modifyShoppingCart(shoppingCart);
			}
		}
	}
	
	public Map<String,Object> getShoppingCart(String commodityNum){
		Map<String,Object> map=new HashMap<>();
		return null;
		
	}
	
	/*public void updateShoppingCart(String commodityNum,Integer buyNum,User user) {
		
	}*/

}
