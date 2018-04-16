package com.yh.sales.receiving.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.yh.entity.ReceivingInfrom;
import com.yh.entity.User;
import com.yh.sales.receiving.mapper.ReceivingMapper;

@Service
public class ReceivingService {
	
	@Autowired
	private ReceivingMapper receivingMapper;
	
	/**
	 * 保存或修改
	 * @param user
	 * @param receiving
	 */
	public void saveReceiving(User user,ReceivingInfrom receiving) {
		if(receiving.getId()==null ) {
			receiving.setCreateTime(new Date());
			receiving.setCreateUserId(user.getId());
			receiving.setUpdateTime(new Date());
			if(receivingMapper.getCounts(user.getId())<=0) {
				receiving.setIsDefault(1);
			}else {
				receiving.setIsDefault(0);
			}
			receivingMapper.saveReceiving(receiving);
		}else {
			ReceivingInfrom receInfo=receivingMapper.findById(receiving.getId());
			if(receiving.getIsDefault()==null) {
				receiving.setIsDefault(receInfo.getIsDefault());
			}
			receiving.setUpdateTime(new Date());
			receivingMapper.modifyReceiving(receiving, user.getId(), null);
		}
	}
	
	/**
	 * 设置默认地址
	 * @param id
	 * @param userId
	 */
	public void setDefaultAddress(Long id,Long userId){
		ReceivingInfrom receivingInfrom=new ReceivingInfrom();
		//将之前的默认地址改为非默认
		receivingInfrom.setIsDefault(0);
		receivingInfrom.setUpdateTime(new Date());
		receivingMapper.modifyReceiving(receivingInfrom, userId, 1);//isdefault参数（1），用于修改已有的默认地址
		//根据地址id改为默认地址
		receivingInfrom.setId(id);
		receivingInfrom.setIsDefault(1);
		receivingMapper.modifyReceiving(receivingInfrom, userId, null);
	}
	
	/**
	 * 查看用户的所有地址
	 * @param user
	 * @return
	 */
	public Map<String,Object> findAllReceivingByUser(User user){
		Map<String,Object> map=new HashMap<>();
		List<ReceivingInfrom> list=receivingMapper.findByCreateUserId(user.getId());
		map.put("receivingList", list);
		return map;
	}
	
	/**
	 * 查看单个地址信息
	 * @param id
	 * @return
	 */
	public Map<String,Object> findOneReceiving(Long id){
		Map<String,Object> map=new HashMap<>();
		ReceivingInfrom receivingInfrom=receivingMapper.findById(id);
		map.put("receiving", receivingInfrom);
		return map;
	}
	
	/**
	 * 删除单个地址
	 * @param id
	 * @param user
	 */
	public void delOneReceiving(Long id,User user) {
		receivingMapper.deleteReceiving(id, user.getId());
	}

}
