package com.yh.sales.comment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yh.entity.Comment;
import com.yh.entity.CommentVo;


@Repository
@Mapper
public interface CommentMapper {

	public void saveComment(List<Comment> comment);
	
	public List<CommentVo> findByCommodityNum(@Param("commodityNum")String commodityNum);
	
	public Integer getCount(@Param("commodityNum")String commodityNum,@Param("type")Integer type);
}
