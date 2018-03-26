package com.yh.sales.order.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yh.entity.Commodity;
import com.yh.entity.CommodityRefOrder;
import com.yh.entity.ShoppingCartVo;
import com.yh.entity.User;
import com.yh.sales.commodity.mapper.CommodityMapper;
import com.yh.sales.commodityimg.mapper.CommodityImgMapper;
import com.yh.sales.commodityreforder.mapper.CommodityRefOrderMapper;
import com.yh.sales.receiving.mapper.ReceivingMapper;

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
	
	
	/**
	 * 跳转到支付界面，商品数据展示
	 * @param msg 请求体
	 * @param user
	 * @return
	 */
	public  List<ShoppingCartVo> toBepaidMsg(String msg,User user){
		List<ShoppingCartVo> list=new ArrayList<>();
		if(StringUtils.isNotBlank(msg)) {
			//商品详情跳转结算结算展示数据
			if(msg.contains("&")) {
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
			
			/*JSONObject obj=JSONObject.fromObject(msg);
			JSONArray msgArr=JSONArray.fromObject(obj);
			for(int i=0;i<msgArr.size();i++) {
				JSONObject msgObje=msgArr.getJSONObject(i);
				String type=msgObje.getString("type");//type 结算来源类型；C-商品详情； S-购物车
				//商品详情跳转结算结算展示数据
				if(type.equals("C")) {
					Commodity commodity=commodityMapper.findOneById(null, msgObje.getString("commodityNum"));
					if(commodity!=null && commodity.getStatus()==1) {
						ShoppingCartVo buyVo=new ShoppingCartVo();
						buyVo.setCommodityId(commodity.getId());
						buyVo.setCommodityNum(commodity.getCommodityNum());
						buyVo.setCommodityTitle(
								"【"+commodity.getTradeName()+"】 "+commodity.getTeaName()+" "+commodity.getPickYear()+" "
							   +commodity.getPickSeason()+" "+commodity.getProductType()+" "+commodity.getGoodsGrade()
							   +" "+commodity.getNetContent()+"克 "+(commodity.getPurpose()==1 ? "自饮":"礼盒"));
						buyVo.setBuyNum(msgObje.getInt("buyNum"));
						buyVo.setStatus(commodity.getStatus());
						buyVo.setRepertoryStatus(commodity.getRepertoryStatus());
						buyVo.setPromotionPrice(commodity.getPromotionPrice());
						list.add(buyVo);
					}
					
				}
				//购物车跳转结算界面展示数据
				else if(type.equals("S")) {
					
				}*/
			}
		}
		return list;
	}
	
	public void createOrder(String msg,User user) {
		if(StringUtils.isNotBlank(msg)) {
			JSONObject msgObj=JSONObject.fromObject(msg);
			//{"paymentAmount":243.1,"totalAmount":243.1,"receivingId":0,"logisticsMode":"圆通",
			//"paymentMode":"银联","remark":"","commodity":[{"commodityNum":"201601","buyNum":"1"}]}
			BigDecimal paymentAmount=new BigDecimal(msgObj.getString("paymentAmount"));
			BigDecimal totalAmount=new BigDecimal(msgObj.getString("totalAmount"));
			Long receivingId=msgObj.getLong("receivingId");
			String logisticsMode=msgObj.getString("logisticsMode");
			String paymentMode=msgObj.getString("paymentMode");
			String remark=msgObj.getString("remark");
			String commodityMsg=msgObj.getString("commodity");
			if(StringUtils.isNotBlank(commodityMsg)) {
				//JSONObject commodityObj=JSONObject.fromObject(commodityMsg);
				JSONArray commodityArr=JSONArray.fromObject(commodityMsg);
				for(int i=0;i<commodityArr.size();i++) {
					JSONObject commdodity=commodityArr.getJSONObject(i);
					String commodityNum=commdodity.getString("commodityNum");
					Integer buyNum=commdodity.getInt("buyNum");
					System.out.println();
				}
			}
			
		}
	}
	
}
