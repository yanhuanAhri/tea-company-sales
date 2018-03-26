package com.yh.sales.commodityreforder.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yh.entity.CommodityRefOrder;

@Repository
@Mapper
public interface CommodityRefOrderMapper {
	
	public Long saveCommodityRefOrder(@Param("commodityRefOrder")CommodityRefOrder commodityRefOrder);
	
	public void updateCommodityRefOrder(@Param("commodityRefOrder")CommodityRefOrder commodityRefOrder,@Param("ref")CommodityRefOrder ref);
}
