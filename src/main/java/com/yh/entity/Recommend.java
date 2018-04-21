package com.yh.entity;

import java.util.Date;

public class Recommend {
	private Long id;
	private Long commodityId;
	private String commodityNum;
	private String img;
	private Long createUserId;
	private Date createTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
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
