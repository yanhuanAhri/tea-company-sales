package com.yh.sales.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yh.entity.OrderLog;

@Repository
@Mapper
public interface OrderLogMapper {
	
	public void saveOrderLog(@Param("orderLog")OrderLog orderLog);
	
	public List<OrderLog> getOrderLog(@Param("orderNum")String orderNum);
}
