package com.yh.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Commodity {
	private Long id;
	private String commodityNum;
	private Long productNum;
	private Long soldOutNum;
	private String tradeName;
	private BigDecimal marketPrice;
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
	private Integer purpose;
	
	private String specification;
	private String originPlace;
	private String foodProductionLicence;
	private String storeMethod;
	private Integer expirationDate;
	private String craft;
	private String packingSpeicification;
	
	private Integer status;
	private Integer repertoryStatus;
	
	public Integer getRepertoryStatus() {
		return repertoryStatus;
	}
	public void setRepertoryStatus(Integer repertoryStatus) {
		this.repertoryStatus = repertoryStatus;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getSoldOutNum() {
		return soldOutNum;
	}
	public void setSoldOutNum(Long soldOutNum) {
		this.soldOutNum = soldOutNum;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getCommodityNum() {
		return commodityNum;
	}
	public void setCommodityNum(String commodityNum) {
		this.commodityNum = commodityNum;
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
	
	public BigDecimal getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
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
	public Integer getPurpose() {
		return purpose;
	}
	public void setPurpose(Integer purpose) {
		this.purpose = purpose;
	}
	public String getSpecification() {
		return specification;
	}
	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getOriginPlace() {
		return originPlace;
	}
	public void setOriginPlace(String originPlace) {
		this.originPlace = originPlace;
	}
	public String getFoodProductionLicence() {
		return foodProductionLicence;
	}
	public void setFoodProductionLicence(String foodProductionLicence) {
		this.foodProductionLicence = foodProductionLicence;
	}
	public String getStoreMethod() {
		return storeMethod;
	}
	public void setStoreMethod(String storeMethod) {
		this.storeMethod = storeMethod;
	}
	public Integer getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Integer expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getCraft() {
		return craft;
	}
	public void setCraft(String craft) {
		this.craft = craft;
	}
	public String getPackingSpeicification() {
		return packingSpeicification;
	}
	public void setPackingSpeicification(String packingSpeicification) {
		this.packingSpeicification = packingSpeicification;
	}
	
	
	

}
