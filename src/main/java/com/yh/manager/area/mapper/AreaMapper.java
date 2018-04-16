package com.yh.manager.area.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yh.entity.Area;

@Repository
@Mapper
public interface AreaMapper {
	
	/**
	 * @param parentId 可为null
	 * @param level 可为null
	 * @return
	 */
	List<Area> findByParentIdAndLevel(@Param("parentId") Long parentId,@Param("level")Integer level);
}
