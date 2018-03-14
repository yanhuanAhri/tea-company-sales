package com.yh.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Commodity {
	private Long id;
	private String commodityId;
	private Long productNum;
	private String tradeName;
	private BigDecimal markePrice;
	private BigDecimal promotionPrice;
	private Date createTime;
	private Long createUserId;
	private Date updateTime;
	private Long updateUserId;
	private String teaName;
	private String productType;
	private String pickYear;
	private String pickSeason;
	private String goodsGrade;
	private Integer netContent;
	private String purpose;
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
	public Long getProductNum() {
		return productNum;
	}
	public void setProductNum(Long productNum) {
		this.productNum = productNum;
	}
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	
	public BigDecimal getMarkePrice() {
		return markePrice;
	}
	public void setMarkePrice(BigDecimal markePrice) {
		this.markePrice = markePrice;
	}
	public BigDecimal getPromotionPrice() {
		return promotionPrice;
	}
	public void setPromotionPrice(BigDecimal promotionPrice) {
		this.promotionPrice = promotionPrice;
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
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Long getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}
	
	public String getTeaName() {
		return teaName;
	}
	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getPickYear() {
		return pickYear;
	}
	public void setPickYear(String pickYear) {
		this.pickYear = pickYear;
	}
	public String getPickSeason() {
		return pickSeason;
	}
	public void setPickSeason(String pickSeason) {
		this.pickSeason = pickSeason;
	}
	public String getGoodsGrade() {
		return goodsGrade;
	}
	public void setGoodsGrade(String goodsGrade) {
		this.goodsGrade = goodsGrade;
	}
	public Integer getNetContent() {
		return netContent;
	}
	public void setNetContent(Integer netContent) {
		this.netContent = netContent;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
	

}
