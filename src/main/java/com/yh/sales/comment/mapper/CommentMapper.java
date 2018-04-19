package com.yh.sales.comment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.yh.entity.Comment;


@Repository
@Mapper
public interface CommentMapper {

	public void saveComment(List<Comment> comment);
}
