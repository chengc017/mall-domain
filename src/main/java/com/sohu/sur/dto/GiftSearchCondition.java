package com.sohu.sur.dto;

import java.util.Date;

import com.sohu.sur.model.admin.SucBrand;
import com.sohu.sur.model.admin.SucCategory;

/**
 * @Title: GiftSearchCondition.java
 * @Package com.sohu.sur.dto
 * @Description: 礼品查询DTO
 * @author leiyangbj6779
 * @date 2011-12-6 上午11:32:30
 * @version V1.0
 */
public class GiftSearchCondition {
	private Long id;
	private String name;
	private String viewName;
	private Long sortId;// 分类Id
	private Long brandId;// 品牌Id
	private String type;// 商品类型 1.兑换 2.抽奖
	private Integer count;
	private Integer stockCount;
	private Integer marketValue;
	private Integer saleValue; // 废弃字段
	private Integer isShelf;
	private Integer lackStatus;
	private Integer freezeStatus;
	private Date operateTime;
	private Date createTime;
	private Date shelfTime;
	private String descr;
	private Long regionId;
	private Long ruleId;// 扣分规则id
	private String operator;// 操作人
	private String logo;// logo图片
	private String channelIds; // 频道
	private String medals; // 所需勋章
	private Integer priority;
	private Date startTime;
	private Date endTime;
	private Integer version;
	private SucCategory category;// 类别对象
	private SucBrand brand;// 品牌
	private String url;// 礼品详细信息url
	private Integer isRecommend;// 是否为推荐礼品
	private Integer gift_type;// 商品类型 0实物，1虚拟
	private String isShelfString;// 0：未上架, 1：上架, 2：下架
	private String lackStatusString;// 0：缺货、1：现货
	private String typeString;// 礼品类型：1：兑换礼品 2：抽奖礼品
	private String logoPicPath;
	private String smallPicPath;
	private String middlePicPath;
	private String isRecommendString;// 是否为推荐礼品
	private String shelfStartTime;//上下架开始时间
    private String shelfEndTime;//上下架结束时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public Long getSortId() {
		return sortId;
	}

	public void setSortId(Long sortId) {
		this.sortId = sortId;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getStockCount() {
		return stockCount;
	}

	public void setStockCount(Integer stockCount) {
		this.stockCount = stockCount;
	}

	public Integer getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(Integer marketValue) {
		this.marketValue = marketValue;
	}

	public Integer getSaleValue() {
		return saleValue;
	}

	public void setSaleValue(Integer saleValue) {
		this.saleValue = saleValue;
	}

	public Integer getIsShelf() {
		return isShelf;
	}

	public void setIsShelf(Integer isShelf) {
		this.isShelf = isShelf;
	}

	public Integer getLackStatus() {
		return lackStatus;
	}

	public void setLackStatus(Integer lackStatus) {
		this.lackStatus = lackStatus;
	}

	public Integer getFreezeStatus() {
		return freezeStatus;
	}

	public void setFreezeStatus(Integer freezeStatus) {
		this.freezeStatus = freezeStatus;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getShelfTime() {
		return shelfTime;
	}

	public void setShelfTime(Date shelfTime) {
		this.shelfTime = shelfTime;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	public Long getRuleId() {
		return ruleId;
	}

	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getChannelIds() {
		return channelIds;
	}

	public void setChannelIds(String channelIds) {
		this.channelIds = channelIds;
	}

	public String getMedals() {
		return medals;
	}

	public void setMedals(String medals) {
		this.medals = medals;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public SucCategory getCategory() {
		return category;
	}

	public void setCategory(SucCategory category) {
		this.category = category;
	}

	public SucBrand getBrand() {
		return brand;
	}

	public void setBrand(SucBrand brand) {
		this.brand = brand;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}

	public Integer getGift_type() {
		return gift_type;
	}

	public void setGift_type(Integer gift_type) {
		this.gift_type = gift_type;
	}

	public String getIsShelfString() {
		return isShelfString;
	}

	public void setIsShelfString(String isShelfString) {
		this.isShelfString = isShelfString;
	}

	public String getLackStatusString() {
		return lackStatusString;
	}

	public void setLackStatusString(String lackStatusString) {
		this.lackStatusString = lackStatusString;
	}

	public String getTypeString() {
		return typeString;
	}

	public void setTypeString(String typeString) {
		this.typeString = typeString;
	}

	public String getLogoPicPath() {
		return logoPicPath;
	}

	public void setLogoPicPath(String logoPicPath) {
		this.logoPicPath = logoPicPath;
	}

	public String getSmallPicPath() {
		return smallPicPath;
	}

	public void setSmallPicPath(String smallPicPath) {
		this.smallPicPath = smallPicPath;
	}

	public String getMiddlePicPath() {
		return middlePicPath;
	}

	public void setMiddlePicPath(String middlePicPath) {
		this.middlePicPath = middlePicPath;
	}

	public String getIsRecommendString() {
		return isRecommendString;
	}

	public void setIsRecommendString(String isRecommendString) {
		this.isRecommendString = isRecommendString;
	}

	public String getShelfStartTime() {
		return shelfStartTime;
	}

	public void setShelfStartTime(String shelfStartTime) {
		this.shelfStartTime = shelfStartTime;
	}

	public String getShelfEndTime() {
		return shelfEndTime;
	}

	public void setShelfEndTime(String shelfEndTime) {
		this.shelfEndTime = shelfEndTime;
	}

}
