package com.yh.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CommodityVo {
	private String commodityNum;
	private Long productNum;
	private Long soldOutNum;
	private String tradeName;
	private BigDecimal marketPrice;
	private BigDecimal promotionPrice;
	private String teaName;
	private String productType;
	private String pickYear;
	private String pickSeason;
	private String goodsGrade;
	private Integer netContent;
	private Integer purpose;
	private String specification;
	private Integer status;//1-有货
	private String cover;
//	private Integer repertoryStatus;
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
	public Long getSoldOutNum() {
		return soldOutNum;
	}
	public void setSoldOutNum(Long soldOutNum) {
		this.soldOutNum = soldOutNum;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	
	
}
