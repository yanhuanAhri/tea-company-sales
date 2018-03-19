package com.yh.commodity.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.groovy.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yh.commodity.mapper.CommodityMapper;
import com.yh.commodityimg.mapper.CommodityImgMapper;
import com.yh.entity.Commodity;
import com.yh.entity.CommodityImg;

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
	public Map<String,Object> getCommodityMsg(String commodityNum){
		Map<String,Object> map=new HashMap<>();
		
		Commodity commodity=commodityMapper.findOneById(null, commodityNum);
		//1：封面图，2：细节图，3：详情图
		//List<CommodityImg> coverList=commodityImgMapper.findCommodityImgByType(1, commodityId, null);
		List<CommodityImg> detailList=commodityImgMapper.findCommodityImgByType(2, commodity.getId(), null);
		List<CommodityImg> particularList=commodityImgMapper.findCommodityImgByType(3, commodity.getId(), null);
		map.put("commodity", commodity);
	//	map.put("coverList", coverList);
		map.put("detailList", detailList);
		map.put("particularList", particularList);
		return map;
		
	}

}
