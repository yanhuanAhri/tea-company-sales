package com.yh.sales.commodity.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.groovy.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yh.entity.Commodity;
import com.yh.entity.CommodityImg;
import com.yh.sales.commodity.mapper.CommodityMapper;
import com.yh.sales.commodityimg.mapper.CommodityImgMapper;

@Service
public class CommodityService {
	@Autowired
	private CommodityMapper commodityMapper;
	@Autowired
	private CommodityImgMapper commodityImgMapper;
	
	/*public void saveCommodity(Commodity commodity) {
		if(!StringUtils.isEmpty(commodity)) {
			Long id=commodityMapper.saveCommodity(commodity);
			if(id!=null) {
				//teji+jijie+lurunianfen+id
				//lcommodity.setCommodityId(""+id);
			}
		}
	}
	*/
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

}
