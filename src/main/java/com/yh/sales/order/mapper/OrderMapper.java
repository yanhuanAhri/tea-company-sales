package com.yh.sales.order.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yh.entity.Order;

@Repository
@Mapper
public interface OrderMapper {
	
	public Long saveOrder(@Param("order")Order order);
	
	public void updateOrder(@Param("order")Order order,@Param("orderNum")String orderNum);
	
	public Order findOne(@Param("orderNum")String orderNum,@Param("id")Long id);
}
