package com.yh.sales.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yh.entity.Order;
import com.yh.entity.OrderVo;

@Repository
@Mapper
public interface OrderMapper {
	
	public Long saveOrder(@Param("order")Order order);
	
	public void updateOrder(@Param("order")Order order,@Param("orderNum")String orderNum);
	
	public Order findOne(@Param("orderNum")String orderNum,@Param("id")Long id);
	
	/**
	 * 根据用户id和订单状态查看订单信息
	 * @param userId
	 * @param status //订单状态   0-待付款、1-完成、2-待发货、3-待收货、4-待评价、10-退款售后、-10-交易关闭
	 * @param orderNum  订单编号
	 * @return
	 */
	public List<OrderVo> findByStatus(@Param("userId")Long userId,@Param("statusList")List<Integer> statusList,@Param("orderNum")String orderNum);
}
