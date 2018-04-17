package com.yh.sales.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yh.entity.CommodityImg;

@Repository
@Mapper
public interface CommodityImgMapper {
	
	/**
	 * @param type 1：封面图，2：细节图，3：详情图
	 * @param commodityId
	 * @param commodityNum
	 * @return
	 */
	public List<CommodityImg> findCommodityImgByType(@Param("type") Integer type,@Param("commodityId")Long commodityId,@Param("commodityNum")String commodityNum);
	
}
