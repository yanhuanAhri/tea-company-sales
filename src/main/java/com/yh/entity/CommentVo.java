package com.yh.entity;

import java.util.Date;
import java.util.List;

public class CommentVo {
	private Long id;
	private Long orderId;
	private String orderNum;
	private Long commodityId;
	private String commodityNum;
	private Integer type;
	private String content;
	private Long createUserId;
	private Date createTime;
	private List<User> user;
	private List<CommentImg> commentImg;
	
	
	public List<User> getUser() {
		return user;
	}
	public void setUser(List<User> user) {
		this.user = user;
	}
	public List<CommentImg> getCommentImg() {
		return commentImg;
	}
	public void setCommentImg(List<CommentImg> commentImg) {
		this.commentImg = commentImg;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public Long getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(Long commodityId) {
		this.commodityId = commodityId;
	}
	public String getCommodityNum() {
		return commodityNum;
	}
	public void setCommodityNum(String commodityNum) {
		this.commodityNum = commodityNum;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
