package com.yh.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderVo {
	private Long id;
	private String orderNum;
	//private Integer buyNum;
	private Date createTime;
	private Long createUserId;
	private BigDecimal paymentAmount;
	private BigDecimal totalAmount;
	private Integer status;//订单状态   0-待付款、1-完成、2-待发货、3-待收货、4-待评价、10-退款售后
	private Long receivingId;
	private Date updateTime;
	private Date putawayTime;
	private String logisticsMode;
	private String paymentMode;
	private String remark;
	
	private List<CommodityRefOrder> commodityRefOrder;
	
	
	public List<CommodityRefOrder> getCommodityRefOrder() {
		return commodityRefOrder;
	}
	public void setCommodityRefOrder(List<CommodityRefOrder> commodityRefOrder) {
		this.commodityRefOrder = commodityRefOrder;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getLogisticsMode() {
		return logisticsMode;
	}
	public void setLogisticsMode(String logisticsMode) {
		this.logisticsMode = logisticsMode;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
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
	
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getPutawayTime() {
		return putawayTime;
	}
	public void setPutawayTime(Date putawayTime) {
		this.putawayTime = putawayTime;
	}
}
