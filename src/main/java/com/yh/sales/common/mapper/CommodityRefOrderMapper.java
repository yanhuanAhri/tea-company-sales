package com.yh.sales.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yh.entity.CommodityRefOrder;

@Repository
@Mapper
public interface CommodityRefOrderMapper {
	
	public void saveCommodityRefOrder(List<CommodityRefOrder> commodityRefOrderList);
	
	public void updateCommodityRefOrder(@Param("commodityRefOrder")CommodityRefOrder commodityRefOrder,@Param("ref")CommodityRefOrder ref);
	
	public List<CommodityRefOrder> findByOrder(@Param("orderId")Long orderId);
}
