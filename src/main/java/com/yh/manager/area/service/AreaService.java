package com.yh.manager.area.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yh.entity.Area;
import com.yh.manager.area.mapper.AreaMapper;

@Service
public class AreaService {
	
	@Autowired
	private AreaMapper areaMapper;
	
	/**
	 * 地区选择查询
	 * @param parentId 父级id
	 * @return
	 */
	public List<Area> findBy(Long parentId){
		List<Area> list=new ArrayList<>();
		if(parentId==null) {
			list=areaMapper.findByParentIdAndLevel(null, 1);
		}else {
			list=areaMapper.findByParentIdAndLevel(parentId, null);
		}
		return list;
	}
	
	public List<Area> getArea(){
		return areaMapper.findByParentIdAndLevel(null, null);
	}
}
