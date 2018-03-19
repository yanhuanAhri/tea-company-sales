package com.yh.commodityimg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yh.entity.CommodityImg;

@Repository
@Mapper
public interface CommodityImgMapper {
	
	public List<CommodityImg> findCommodityImgByType(@Param("type") Integer type,@Param("commodityId")Long commodityId,@Param("commodityNum")String commodityNum);
	
}
