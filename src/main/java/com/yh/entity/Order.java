package com.yh.entity;

import java.math.BigDecimal;

import com.mysql.fabric.xmlrpc.base.Data;

public class Order {
	private Long id;
	private String commodityId;
	private String commodityTitle;
	private Integer buyNum;
	private Data createTime;
	private Long createUserId;
	private BigDecimal paymentAmount;
	private BigDecimal totalAmount;
	private Integer status;
	private Long receivingId;
	private BigDecimal buyPrice;
	private Data updateTime;
	private Data putawayTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}
	public String getCommodityTitle() {
		return commodityTitle;
	}
	public void setCommodityTitle(String commodityTitle) {
		this.commodityTitle = commodityTitle;
	}
	public Integer getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}
	public Data getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Data createTime) {
		this.createTime = createTime;
	}
	public Long getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getReceivingId() {
		return receivingId;
	}
	public void setReceivingId(Long receivingId) {
		this.receivingId = receivingId;
	}
	public BigDecimal getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}
	public Data getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Data updateTime) {
		this.updateTime = updateTime;
	}
	public Data getPutawayTime() {
		return putawayTime;
	}
	public void setPutawayTime(Data putawayTime) {
		this.putawayTime = putawayTime;
	}
	
	

}
