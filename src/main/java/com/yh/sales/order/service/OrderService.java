package com.yh.sales.order.service;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yh.core.util.SerialUtil;
import com.yh.entity.Commodity;
import com.yh.entity.CommodityRefOrder;
import com.yh.entity.Order;
import com.yh.entity.ReceivingInfrom;
import com.yh.entity.ShoppingCartVo;
import com.yh.entity.User;
import com.yh.sales.commodity.mapper.CommodityMapper;
import com.yh.sales.commodityimg.mapper.CommodityImgMapper;
import com.yh.sales.commodityreforder.mapper.CommodityRefOrderMapper;
import com.yh.sales.order.mapper.OrderMapper;
import com.yh.sales.receiving.mapper.ReceivingMapper;
import com.yh.sales.shoppingcart.mapper.ShoppingCartMapper;
import com.yh.sales.user.mapper.UserMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class OrderService {
	
	@Autowired
	private CommodityMapper commodityMapper;
	@Autowired
	private CommodityImgMapper commodityImgMapper;
	@Autowired
	private CommodityRefOrderMapper commodityRefOrderMapper;
	@Autowired
	private ReceivingMapper receivingMapper;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ShoppingCartMapper shoppingCartMapper;
	
	private static Map<String,Object> toDelete=new HashMap<>();
	
	/**
	 * 跳转到支付界面，商品数据展示
	 * @param msg 请求体
	 * @param user
	 * @return
	 *     库存和已售
	 */
	public  List<ShoppingCartVo> toBepaidMsg(String msg,User user,String params){
		List<ShoppingCartVo> list=new ArrayList<>();
		if(StringUtils.isNotBlank(msg)) {
			toDelete.put(user.getId().toString()+"type", params);
			//商品详情跳转结算结算展示数据
			if(msg.contains("&")) {
				//shoppingCart=''&commodityArr[]=201702&commodityArr[]=201601
				if(msg.contains("shoppingCart")) {
					msg=URLDecoder.decode(msg);
					String[] str=msg.split("&");
					List<String> commodityNumList=new ArrayList<>();
					for(int j=0;j<str.length;j++) {
						if(str[j].contains("commodityArr")) {
							commodityNumList.add(str[j].split("=")[1]);
						}
					}
					if(commodityNumList!=null && !commodityNumList.isEmpty()) {
						toDelete.put(user.getId().toString(), commodityNumList);
						list.addAll(shoppingCartMapper.findByCommodityNumList(commodityNumList, user.getId()));
					}
				}else {
					//msg:type=C&commodityNum=201601&buyNum=1
					String type=null,commodityNum=null,buyNum=null;
					String[] str=msg.split("&");
					for(int i=0;i<str.length;i++) {
						if(str[i].contains("type")) {
							 type=str[i].split("=")[1];
						}else if(str[i].contains("commodityNum")) {
							 commodityNum=str[i].split("=")[1];
						}
						else if(str[i].contains("buyNum")) {
							 buyNum=str[i].split("=")[1];
						}
					}
					if(type!=null && commodityNum!=null && buyNum!=null) {
						if(type.equals("C")) {
							Commodity commodity=commodityMapper.findOneById(null, commodityNum);
							if(commodity!=null && commodity.getStatus()==1) {
								ShoppingCartVo buyVo=new ShoppingCartVo();
								buyVo.setCommodityId(commodity.getId());
								buyVo.setCommodityNum(commodity.getCommodityNum());
								buyVo.setCommodityTitle(
										"【"+commodity.getTradeName()+"】 "+commodity.getTeaName()+" "+commodity.getPickYear()+" "
									   +commodity.getPickSeason()+" "+commodity.getProductType()+" "+commodity.getGoodsGrade()
									   +" "+commodity.getNetContent()+"克 "+(commodity.getPurpose()==1 ? "自饮":"礼盒"));
								buyVo.setBuyNum(Integer.valueOf(buyNum));
								buyVo.setStatus(commodity.getStatus());
								buyVo.setRepertoryStatus(commodity.getRepertoryStatus());
								buyVo.setPromotionPrice(commodity.getPromotionPrice());
								buyVo.setCover(commodityImgMapper.findCommodityImgByType(1, null, commodityNum).get(0).getPath());
								list.add(buyVo);
							}
						}
					}
				}
			}
		}
		return list;
	}
	
	/**
	 * 订单创建
	 * @param msg
	 * @param user
	 */
	public Map<String,Object> createOrder(String msg,User user) {
		Map<String,Object> map=new HashMap<>();
		if(StringUtils.isNotBlank(msg)) {
			JSONObject msgObj=JSONObject.fromObject(msg);
			//保存商品
			Order order=new Order();
			order.setOrderNum(SerialUtil.generateOrderSerial("ORDER"+user.getUserName().toUpperCase()));
			order.setCreateTime(new Date());
			order.setCreateUserId(user.getId());
			order.setPaymentAmount(new BigDecimal(msgObj.getString("paymentAmount")));
			order.setTotalAmount(new BigDecimal(msgObj.getString("totalAmount")));
			order.setStatus(0);
			//receivingId=0,是用默认地址
			if(msgObj.getLong("receivingId")==0) {
				ReceivingInfrom receivingInfrom=receivingMapper.findByStatus(user.getId(), 1).get(0);
				order.setReceivingId(receivingInfrom.getId());
			}else {
				order.setReceivingId(msgObj.getLong("receivingId"));
			}
			order.setUpdateTime(new Date());
			order.setLogisticsMode(msgObj.getString("logisticsMode"));
			order.setPaymentMode(msgObj.getString("paymentMode"));
			order.setRemark(msgObj.getString("remark"));
			String commodityMsg=msgObj.getString("commodity");
			//商品关系
			if(StringUtils.isNotBlank(commodityMsg)) {
				orderMapper.saveOrder(order);
				saveOrderRef(order.getOrderNum(),commodityMsg);
				map.put("orderNum", order.getOrderNum());
				//购物车下单，删除
				String params=toDelete.get(user.getId().toString()+"type").toString();
				if(params.contains("shoppingCart")) {
					List<String> commodityNumList=(List<String>) toDelete.get(user.getId().toString());
					if(commodityNumList!=null && !commodityNumList.isEmpty()) {
						shoppingCartMapper.deleteShoppingCartByCommodityNum(commodityNumList, user.getId());
					}
				}
				
			}
		}
		return map;
	}
	
	/**
	 * 保存商品订单关系表数据
	 * @param orderNum
	 * @param commodityMsg
	 */
	private void saveOrderRef(String orderNum,String commodityMsg) {
		Order order=orderMapper.findOne(orderNum, null);
		JSONArray commodityArr=JSONArray.fromObject(commodityMsg);
		List<CommodityRefOrder> list=new ArrayList<>();
		for(int i=0;i<commodityArr.size();i++) {
			JSONObject commodity=commodityArr.getJSONObject(i);
			CommodityRefOrder orderRef=new CommodityRefOrder();
			orderRef.setCommodityId(commodity.getLong("commodityId"));
			orderRef.setCommodityNum(commodity.getString("commodityNum"));
			orderRef.setOrderId(order.getId());
			orderRef.setOrderNum(order.getOrderNum());
			orderRef.setCommodityTitle(commodity.getString("commodityTitle"));
			orderRef.setBuyNum(commodity.getInt("buyNum"));
			orderRef.setBuyPrice(new BigDecimal(commodity.getString("buyPrice")));
			list.add(orderRef);
		}
		commodityRefOrderMapper.saveCommodityRefOrder(list);
	}
	
	/**
	 * 付款
	 * @param user
	 * @param payMsg
	 * @return
	 */
	public Map<String,Object> pay(User user,String payMsg) {
		//paymentCode
		Map<String,Object> map=new HashMap<>();
		//paymentCode=123456&orderNum=ORDERZHANGSAN15221560486001
		if(StringUtils.isNotBlank(payMsg)) {
			if(payMsg.contains("&")) {
				String paymentCode=null,orderNum=null;
				String[] payArr=payMsg.split("&");
				for(int i=0;i<payArr.length;i++) {
					if(payArr[i].contains("paymentCode")) {
						paymentCode=payArr[i].split("=")[1];
					}else if(payArr[i].contains("orderNum")) {
						orderNum=payArr[i].split("=")[1];
					}
				}
				if(paymentCode.equals(userMapper.findById(user.getId()).getPaymentCode())) {
					//支付密码正确修改订单状态
					Order order=new Order();
					order.setPutawayTime(new Date());
					order.setUpdateTime(new Date());
					order.setStatus(2);
					orderMapper.updateOrder(order, orderNum);
					Order orderMsg=orderMapper.findOne(orderNum, null);
					//订单支付成功后需要的数据
					map.put("totalAmount", orderMsg.getTotalAmount());
					ReceivingInfrom receiving=receivingMapper.findById(orderMsg.getReceivingId());
					map.put("receiving", receiving);
				}
			}
		}
		return map;
	}
	
}
