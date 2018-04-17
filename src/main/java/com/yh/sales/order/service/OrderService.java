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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.yh.core.util.EncryptionUtil;
import com.yh.core.util.SerialUtil;
import com.yh.entity.Commodity;
import com.yh.entity.CommodityRefOrder;
import com.yh.entity.Order;
import com.yh.entity.OrderLog;
import com.yh.entity.OrderVo;
import com.yh.entity.ReceivingInfrom;
import com.yh.entity.ShoppingCartVo;
import com.yh.entity.User;
import com.yh.sales.commodity.mapper.CommodityMapper;
import com.yh.sales.common.mapper.CommodityImgMapper;
import com.yh.sales.common.mapper.CommodityRefOrderMapper;
import com.yh.sales.common.mapper.OrderLogMapper;
import com.yh.sales.order.mapper.OrderMapper;
import com.yh.sales.receiving.mapper.ReceivingMapper;
import com.yh.sales.shoppingcart.mapper.ShoppingCartMapper;
import com.yh.sales.user.mapper.UserMapper;
import com.yh.sales.user.service.UserService;

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
	@Autowired
	private OrderLogMapper orderLogMapper;
	@Autowired
	private UserService userService;
	
	@Value("${encryption.way}")
	 private String encryptionWay;
	
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
				if(msg.contains("shoppingCart")) {//购物车到结算界面
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
								//buyVo.setCommodityTitle(commodity.getTradeName());
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
			String name=user.getUserName().toUpperCase();
			name=name.replace("@", "").replace(".", "");
			order.setOrderNum(SerialUtil.generateOrderSerial("ORDER"+name));
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
				Order orderInfo=orderMapper.findOne(order.getOrderNum(), null);
				//订单日志
				saveOrderLog(orderInfo.getId(), orderInfo.getOrderNum(), 0, order.getRemark(), user.getId());
				//商品订单关系表
				saveOrderRef(orderInfo,commodityMsg);
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
	 * @param order
	 * @param commodityMsg
	 */
	private void saveOrderRef(Order order,String commodityMsg) {
		//Order order=orderMapper.findOne(orderNum, null);
		JSONArray commodityArr=JSONArray.fromObject(commodityMsg);
		List<CommodityRefOrder> list=new ArrayList<>();
		List<Commodity> commodities=new ArrayList<>();
		List<String> commodityNums=new ArrayList<>();
		for(int i=0;i<commodityArr.size();i++) {
			JSONObject commodity=commodityArr.getJSONObject(i);
			CommodityRefOrder orderRef=new CommodityRefOrder();
			Commodity commodityVo=new Commodity();
			orderRef.setCommodityId(commodity.getLong("commodityId"));
			orderRef.setCommodityNum(commodity.getString("commodityNum"));
			orderRef.setOrderId(order.getId());
			orderRef.setOrderNum(order.getOrderNum());
			orderRef.setCommodityTitle(commodity.getString("commodityTitle"));
			orderRef.setBuyNum(commodity.getInt("buyNum"));
			orderRef.setBuyPrice(new BigDecimal(commodity.getString("buyPrice")));
			orderRef.setCover(commodity.getString("cover"));
			
			commodityVo.setSoldOutNum(commodity.getLong("buyNum"));
			commodityVo.setCommodityNum(commodity.getString("commodityNum"));
			if(commodityVo!=null) {
				commodities.add(commodityVo);
				commodityNums.add(commodity.getString("commodityNum"));
			}
			list.add(orderRef);
		}
		commodityMapper.updateCommodityProductNum(commodities);
		//updateCommodityProductNum(commodities, commodityNums);
		commodityRefOrderMapper.saveCommodityRefOrder(list);
	}
	
	/*private void updateCommodityProductNum(List<Commodity> commodities,List<String> commodityNums) {
		List<Commodity> commodityList=commodityMapper.findByCommodityNum(commodityNums);
		for(Commodity entity:commodityList) {
			for(Commodity vo:commodities) {
				if(entity.getCommodityNum()==vo.getCommodityNum()) {
					entity.setSoldOutNum(entity.getSoldOutNum()+vo.getSoldOutNum());
					entity.setProductNum(entity.getProductNum()-vo.getSoldOutNum());
				}
			}
		}
		commodityMapper.updateCommodityProductNum(commodityList);
	}*/
	
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
				
				try {
					String paymentCode=null,orderNum=null;
					String[] payArr=payMsg.split("&");
					for(int i=0;i<payArr.length;i++) {
						if(payArr[i].contains("paymentCode")) {
							paymentCode=payArr[i].split("=")[1];
							paymentCode= EncryptionUtil.getHash(paymentCode,encryptionWay);
						}else if(payArr[i].contains("orderNum")) {
							orderNum=payArr[i].split("=")[1];
							if(orderNum.contains("%")) {
								orderNum= URLDecoder.decode(orderNum, "utf-8");
							}
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
						//订单日志
						saveOrderLog(orderMsg.getId(), orderMsg.getOrderNum(), 2, null, user.getId());
						//支付成功修改用户积分
						userService.addIntegral(user, orderMsg.getPaymentAmount());
						//订单支付成功后需要的数据
						map.put("paymentAmount", orderMsg.getPaymentAmount());
						ReceivingInfrom receiving=receivingMapper.findById(orderMsg.getReceivingId(),null);
						map.put("receiving", receiving);
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.getMessage();
					System.out.println(e.getMessage());
				}
				
			}
		}
		return map;
	}
	
	/**
	 * 根据不同的状态，查看我的订单列表
	 * @param user
	 * @param status //订单状态 0-待付款、1-完成、2-待发货、3-待收货、4-待评价、10-退款售后、-10-交易关闭
	 * @return
	 */
	public Map<String,Object> getMyOrder(User user,List<Integer> status){
		Map<String,Object> map=new HashMap<>();
		List<OrderVo> myOrder=orderMapper.findByStatus(user.getId(), status,null);
		map.put("myOrder", myOrder);
		return map;
	}
	
	/**
	 * 订单详情
	 * @param user
	 * @param orderNum
	 * @return
	 */
	public Map<String,Object> getOrderInfo(User user,String orderNum){
		Map<String,Object> map=new HashMap<>();
		List<OrderVo> orderVoList=orderMapper.findByStatus(user.getId(), null, orderNum);
		if(orderVoList!=null && !orderVoList.isEmpty() && orderVoList.size()==1) {
			OrderVo orderVo=orderVoList.get(0);
			ReceivingInfrom receiving=receivingMapper.findById(orderVo.getReceivingId(),user.getId());
			map.put("order", orderVo);
			map.put("receiving", receiving);
		}
		return map;
	}
	
	/**
	 * 订单日志保存
	 * @param orderId
	 * @param orderNum
	 * @param orderStatus 订单状态  -10-交易关闭、0-待付款、1-完成、2-待发货、3-待收货、4-待评价、10-退款售后
	 * @param remark
	 * @param userId
	 */
	private void saveOrderLog(Long orderId,String orderNum,Integer orderStatus,String remark,Long userId){
		OrderLog entity=new OrderLog();
		entity.setOrderId(orderId);
		entity.setOrderNum(orderNum);
		entity.setOrderStatus(orderStatus);
		entity.setCreateTime(new Date());
		entity.setCreateUserId(userId);
		if(remark!=null && StringUtils.isNotBlank(remark)) {
			entity.setRemark(remark);
		}
		orderLogMapper.saveOrderLog(entity);
	}
}
