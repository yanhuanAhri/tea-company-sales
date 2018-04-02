package com.yh.sales.commodity.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.codehaus.groovy.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yh.entity.Commodity;
import com.yh.entity.CommodityImg;
import com.yh.entity.CommodityVo;
import com.yh.sales.commodity.mapper.CommodityMapper;
import com.yh.sales.commodityimg.mapper.CommodityImgMapper;

import net.sf.json.JSONObject;

@Service
public class CommodityService {
	@Autowired
	private CommodityMapper commodityMapper;
	@Autowired
	private CommodityImgMapper commodityImgMapper;
	
	
	/**
	 * 查看商品详情
	 * @param commodityNum 商品编号
	 * @return
	 */
	public Map<String,Object> getCommodityMsg(String commodityNum){
		Map<String,Object> map=new HashMap<>();
		Commodity commodity=commodityMapper.findOneById(null, commodityNum);
		//1：封面图，2：细节图，3：详情图
		CommodityImg cover=commodityImgMapper.findCommodityImgByType(1, commodity.getId(), null).get(0);
		List<CommodityImg> detailList=commodityImgMapper.findCommodityImgByType(2, commodity.getId(), null);
		List<CommodityImg> particularList=commodityImgMapper.findCommodityImgByType(3, commodity.getId(), null);
		map.put("commodity", commodity);
		map.put("cover", cover);
		map.put("detailList", detailList);
		map.put("particularList", particularList);
		return map;
	}
	
	/**
	 * 商品搜索
	 * @param search 搜索框输入数据
	 * @param msg 页面选择数据
	 * @return
	 */
	public Map<String,Object> searchCommodity(String search,String msg){
		Map<String,Object> map=new HashMap<>();
		Integer pageStart=0;
		Integer pageSize=12; 
		StringBuffer sort=new StringBuffer("c.create_time desc");
		CommodityVo commodityVo=new CommodityVo();
		if(msg!=null && StringUtils.isNotBlank(msg) && !msg.equals("null")) {
			JSONObject msgObj=JSONObject.fromObject(msg);
			commodityVo.setProductType(msgObj.getString("productType").equals("null")? null:msgObj.getString("productType"));
			commodityVo.setPickYear(msgObj.getString("pickYear").equals("null")? null:msgObj.getString("pickYear"));
			commodityVo.setPurpose((msgObj.getString("purpose").equals("null")? null:(msgObj.getString("purpose").equals("自饮")? 1:2)));
			Integer pageNum=msgObj.getInt("pageNum");
			pageStart=pageSize*(pageNum-1);
		}
		
		List<CommodityVo> list=commodityMapper.findCommodityVoBySearch(search, commodityVo, pageStart, pageSize, sort.toString());
		//数据为空返回查询的第一页数据
		if(list.isEmpty() && pageStart!=0) {
			pageStart=0;
			list=commodityMapper.findCommodityVoBySearch(search, commodityVo, pageStart, pageSize, sort.toString());
		}
		Integer count=commodityMapper.getCountBySearch(search, commodityVo);
		map.put("commodityVoList", list);
		map.put("count", count);
		return map;
	}
	
	/**
	 * 首页数据
	 * @return
	 */
	public Map<String,Object> getIndexData(){
		Map<String,Object> map=new HashMap<>();
		
		List<CommodityVo> newCommodity=commodityMapper.findCommodityVoBySearch(null, null, 0, 7, "c.create_time desc");//新品
		List<CommodityVo> hotCommodity=commodityMapper.findCommodityVoBySearch(null, null, 0, 7, "c.sold_out_num desc");//爆款
		CommodityVo commodityVo=new CommodityVo();
		commodityVo.setProductType("乌龙茶");
		List<CommodityVo> oolong =commodityMapper.findCommodityVoBySearch(null, commodityVo, 0, 7, "c.create_time desc,c.sold_out_num desc");//乌龙茶
		commodityVo.setProductType("红茶");
		List<CommodityVo>  blackTea =commodityMapper.findCommodityVoBySearch(null, commodityVo, 0, 7, "c.create_time desc,c.sold_out_num desc");
		commodityVo.setProductType("绿茶");
		List<CommodityVo>   greenTea =commodityMapper.findCommodityVoBySearch(null, commodityVo, 0, 7, "c.create_time desc,c.sold_out_num desc");
		commodityVo.setProductType("黑茶");
		List<CommodityVo>  darkGreenTea =commodityMapper.findCommodityVoBySearch(null, commodityVo, 0, 7, "c.create_time desc,c.sold_out_num desc");
		commodityVo.setProductType("白茶");
		List<CommodityVo>  whiteTea =commodityMapper.findCommodityVoBySearch(null, commodityVo, 0, 7, "c.create_time desc,c.sold_out_num desc");
		commodityVo.setProductType("花茶");
		List<CommodityVo>  scentedTea =commodityMapper.findCommodityVoBySearch(null, commodityVo, 0, 7, "c.create_time desc,c.sold_out_num desc");
		commodityVo.setProductType("茶器");
		List<CommodityVo>  teaSet =commodityMapper.findCommodityVoBySearch(null, commodityVo, 0, 7, "c.create_time desc,c.sold_out_num desc");
		
		map.put("newCommodity", newCommodity);
		map.put("hotCommodity", hotCommodity);
		map.put("oolong", oolong);
		map.put("blackTea", blackTea);
		map.put("greenTea", greenTea);
		map.put("darkGreenTea", darkGreenTea);
		map.put("whiteTea", whiteTea);
		map.put("scentedTea", scentedTea);
		map.put("teaSet", teaSet);
		
		return map;
	}

}
