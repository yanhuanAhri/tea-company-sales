package com.yh.commodity.service;

import org.codehaus.groovy.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yh.commodity.mapper.CommodityMapper;
import com.yh.entity.Commodity;

@Service
public class CommodityService {
	@Autowired
	private CommodityMapper commodityMapper;
	
	public void saveCommodity(Commodity commodity) {
		if(!StringUtils.isEmpty(commodity)) {
			Long id=commodityMapper.saveCommodity(commodity);
			if(id!=null) {
				//teji+jijie+lurunianfen+id
				commodity.setCommodityId(""+id);
			}
		}
	}

}
