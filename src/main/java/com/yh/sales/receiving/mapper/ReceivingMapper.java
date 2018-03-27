package com.yh.sales.receiving.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yh.entity.ReceivingInfrom;

@Repository
@Mapper
public interface ReceivingMapper {
	
	public Long saveReceiving(@Param("receiving")ReceivingInfrom receiving);
	
	//isDefault 根据状态修改receivinginfo表
	public void modifyReceiving(@Param("receiving")ReceivingInfrom receiving,@Param("userId")Long userId,@Param("isDefault")Integer isDefault);
	
	public List<ReceivingInfrom> findByCreateUserId(@Param("userId")Long userId);
	
	public ReceivingInfrom findById(@Param("id")Long id);
	
	public void deleteReceiving(@Param("receivingId")Long receivingId,@Param("userId")Long userId);
	
	public Integer getCounts(@Param("userId")Long userId);
	
	public List<ReceivingInfrom> findByStatus(@Param("userId")Long userId,@Param("isDefault")Integer isDefault);
	
	
}
