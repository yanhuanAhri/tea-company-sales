package com.yh.sales.shoppingcart.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yh.entity.Commodity;
import com.yh.entity.ShoppingCart;
import com.yh.entity.ShoppingCartVo;
import com.yh.entity.User;
import com.yh.sales.commodity.mapper.CommodityMapper;
import com.yh.sales.shoppingcart.mapper.ShoppingCartMapper;

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
			ShoppingCart shoppingCommdity=shoppingCartMapper.findByCommodityNum(commodityNum,user.getId());
			//购物车商品新增
			if(shoppingCommdity==null || shoppingCommdity.equals("")) {
				Commodity commodity=commodityMapper.findOneById(null, commodityNum);
				shoppingCart.setCommodityId(commodity.getId());
				shoppingCart.setCommodityNum(commodity.getCommodityNum());
				shoppingCart.setCommodityTitle(commodity.getTradeName());
				/*shoppingCart.setCommodityTitle(
						"【"+commodity.getTradeName()+"】 "+commodity.getTeaName()+" "+commodity.getPickYear()+" "
					   +commodity.getPickSeason()+" "+commodity.getProductType()+" "+commodity.getGoodsGrade()
					   +" "+commodity.getNetContent()+"克 "+(commodity.getPurpose()==1 ? "自饮":"礼盒"));*/
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
	
	/**
	 * 购物车列表信息
	 * @param user
	 * @return
	 */
	public Map<String,Object> getShoppingCart(User user){
		Map<String,Object> map=new HashMap<>();
		//状态（1有货，2缺货，3下架，4未上架）
		List<ShoppingCartVo> cartList=shoppingCartMapper.findByCreateUserIdAndStatus(user.getId(), null,null);
		//limit 分页
		Object count=getCount(user,null).get("count");
		map.put("count", count);
		map.put("cartList", cartList);
		return map;
		
	}
	
	/**
	 * 购物车数量
	 * @param user
	 * @param status
	 * @return
	 */
	public Map<String,Object> getCount(User user,Integer status){
		Map<String,Object> map=new HashMap<>();
		Integer count=shoppingCartMapper.getCount(user.getId(), null,null);
		map.put("count", count);
		return map;
	}
	
	/**
	 * 根据商品编号删除购物车中的商品
	 * @param commodityNums
	 * @param user
	 */
	public void delShopping(List<String> commodityNums,User user) {
		if(commodityNums!=null && !commodityNums.isEmpty()) {
			shoppingCartMapper.deleteShoppingCartByCommodityNum(commodityNums, user.getId());
		}
	}
	
	/*public void updateShoppingCart(String commodityNum,Integer buyNum,User user) {
		
	}*/

}
