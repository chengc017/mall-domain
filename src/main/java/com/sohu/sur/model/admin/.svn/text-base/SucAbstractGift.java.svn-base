package com.sohu.sur.model.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.InheritanceType;
import javax.persistence.Version;

import com.sohu.sur.dto.Medal;
import com.sohu.sur.util.EnumUtils;
import com.sohu.sur.util.gift.GiftConstants;
import com.sohu.sur.util.gift.GiftEnumUtils;
import com.sohu.sur.util.gift.GiftEnumUtils.ShelfStatus;
import com.sohu.sur.util.gift.MedalsJsonParse;

@SuppressWarnings("serial")
@Entity
@Table(name = "SUC_GIFT")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)   
@DiscriminatorColumn( name= "TYPE") 
public class SucAbstractGift implements java.io.Serializable {

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
	private Integer gift_type;// 商品类型 0实物，1优惠券，2虚拟
	private Integer virtual_gift_type=0;// 虚拟物品小类  默认为0
	private Integer compositorId = GiftConstants.DEFAULT_COMPOSITORID;//默认排序字段为100
	private long viewNum=0;//浏览数 默认为0
	// *******************辅助显示 begin*******************
	// 0：未上架, 1：上架, 2：下架
	private String isShelfString;
	// 0：缺货、1：现货
	private String lackStatusString;
	// 礼品类型：1：兑换礼品 2：抽奖礼品
	private String typeString;
	private String logoPicPath;
	private String smallPicPath;
	private String middlePicPath;
	// 是否为推荐礼品
	private String isRecommendString;
	//兑换数目 
	private int exchangeNum=1;
	//所需勋章
	private List<Medal> medallist;
	@Transient
	public int getExchangeNum() {
		return exchangeNum;
	}

	public void setExchangeNum(int exchangeNum) {
		this.exchangeNum = exchangeNum;
	}
	@Transient
	public String getCacheNum() {
		return cacheNum;
	}

	public void setCacheNum(String cacheNum) {
		this.cacheNum = cacheNum;
	}

	//抽奖数 实时
	private String cacheNum;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	@Column(name = "VIEW_NAME")
	public String getViewName() {
		return viewName;
	}

	@Column(name="SORT_ID")
	public Long getSortId() {
		return sortId;
	}

	@Column(name = "BRAND_ID")
	public Long getBrandId() {
		return brandId;
	}

	@Column( name= "TYPE" ,insertable= false,updatable = false)
	public String getType() {
		return type;
	}

	@Column(name = "COUNT")
	public Integer getCount() {
		return count;
	}

	@Column(name = "STOCK_COUNT")
	public Integer getStockCount() {
		return stockCount;
	}

	@Column(name = "MARKET_VALUE")
	public Integer getMarketValue() {
		return marketValue;
	}

	@Column(name = "SALE_VALUE")
	public Integer getSaleValue() {
		return saleValue;
	}

	@Column(name = "IS_SHELF")
	public Integer getIsShelf() {
		return isShelf;
	}

	@Column(name = "LACK_STATUS")
	public Integer getLackStatus() {
		return lackStatus;
	}

	public long getViewNum() {
		return viewNum;
	}

	public void setViewNum(long viewNum) {
		this.viewNum = viewNum;
	}

	@Column(name = "FREEZE_STATUS")
	public Integer getFreezeStatus() {
		return freezeStatus;
	}

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "OPERATE_TIME")
	public Date getOperateTime() {
		return operateTime;
	}

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}

	public Integer getVirtual_gift_type() {
		return virtual_gift_type;
	}

	public void setVirtual_gift_type(Integer virtual_gift_type) {
		this.virtual_gift_type = virtual_gift_type;
	}

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "SHELF_TIME")
	public Date getShelfTime() {
		return shelfTime;
	}

	@Column(name = "DESCR")
	public String getDescr() {
		return descr;
	}

	@Column(name = "REGION_ID")
	public Long getRegionId() {
		return regionId;
	}

	@Column(name = "RULE_ID")
	public Long getRuleId() {
		return ruleId;
	}

	@Column(name = "OPERATOR")
	public String getOperator() {
		return operator;
	}

	@Column(name = "LOGO")
	public String getLogo() {
		return logo;
	}

	@Column(name = "CHANNELIDS")
	public String getChannelIds() {
		return channelIds;
	}

	@Column(name = "PRIORITY")
	public Integer getPriority() {
		return priority;
	}
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "START_TIME")
	public Date getStartTime() {
		return startTime;
	}
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "END_TIME")
	public Date getEndTime() {
		return endTime;
	}

	@Version
	public Integer getVersion() {
		return version;
	}

	@ManyToOne(targetEntity = com.sohu.sur.model.admin.SucCategory.class)
	@JoinColumn(name = "SORT_ID",insertable=false,updatable = false)
	public SucCategory getCategory() {
		return category;
	}

	@Transient
	public SucBrand getBrand() {
		return brand;
	}

	@Column(name = "URL")
	public String getUrl() {
		return url;
	}

	@Column(name = "IS_RECOMMEND")
	public Integer getIsRecommend() {
		return isRecommend;
	}

	@Column(name = "GIFT_TYPE")
	public Integer getGift_type() {
		return gift_type;
	}

	@Column(name = "MEDALS")
	public String getMedals() {
		return medals != null ? (medals.trim().length() > 0 ? medals : null)
				: null;
	}
	@Column(name = "COMPOSITOR_ID")
	public Integer getCompositorId() {
		return compositorId;
	}

	// ************************ setter ***************************
	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public void setSortId(Long sortId) {
		this.sortId = sortId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public void setStockCount(Integer stockCount) {
		this.stockCount = stockCount;
	}

	public void setMarketValue(Integer marketValue) {
		this.marketValue = marketValue;
	}

	public void setSaleValue(Integer saleValue) {
		this.saleValue = saleValue;
	}

	public void setIsShelf(Integer isShelf) {
		this.isShelf = isShelf;
	}

	public void setLackStatus(Integer lackStatus) {
		this.lackStatus = lackStatus;
	}

	public void setFreezeStatus(Integer freezeStatus) {
		this.freezeStatus = freezeStatus;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setShelfTime(Date shelfTime) {
		this.shelfTime = shelfTime;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public void setChannelIds(String channelIds) {
		this.channelIds = channelIds;
	}

	public void setMedals(String medals) {
		this.medals = medals;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public void setCategory(SucCategory category) {
		this.category = category;
	}

	public void setBrand(SucBrand brand) {
		this.brand = brand;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}

	public void setGift_type(Integer gift_type) {
		this.gift_type = gift_type;
	}
	
	public void setCompositorId(Integer compositorId) {
		this.compositorId = compositorId;
	}

	// **********************辅助显示相关*************************
	@Transient
	public String getGiftUrl() {
		return "1".equals(this.type) ? "http://gift.sohu.com/gift/details/"
				+ this.id : "http://gift.sohu.com/lottery/details/" + this.id;
	}

	@Transient
	public List<Medal> getMedallist() {
		if (this.medals == null)
			return null;
		if (this.medals.trim().length() <= 0)
			return null;
		Map medalsmap = MedalsJsonParse.instance().getMedalsdesc();
		List<Medal> medallist = new ArrayList<Medal>();
		for (String med : medals.split(",")) {
			Medal medal = new Medal();
			medal.setCode(med);
			medal.setName((String) medalsmap.get(med));
			medallist.add(medal);
		}

		return medallist;
	}

	public void setIsRecommendString(String isRecommendString) {
		this.isRecommendString = isRecommendString;
	}

	public void setIsShelfString(String isShelfString) {
		this.isShelfString = isShelfString;
	}

	@Transient
	public String getIsShelfString() {

		if (isShelf != null) {
			ShelfStatus shelfStatus = GiftEnumUtils.getShelfStatus(isShelf);

			isShelfString = shelfStatus.getName();
		} else {
			isShelfString = ShelfStatus.NoShelf.getName();
		}

		return isShelfString;
	}

	@Transient
	public String getLackStatusString() {

		Map lackStatusMap = GiftEnumUtils.getLackStatusMap();

		if (lackStatusMap.containsKey(lackStatus)) {
			lackStatusString = (String) lackStatusMap.get(lackStatus);
		} else {
			lackStatusString = GiftEnumUtils.LackStatus.现货.name();
		}

		return lackStatusString;
	}

	@Transient
	public String getTypeString() {

		Map giftTypeMap = GiftEnumUtils.getGiftTypeMap();

		if (giftTypeMap.containsKey(type)) {
			typeString = (String) giftTypeMap.get(type);
		}

		return typeString;
	}

	@Transient
	public String getIsRecommendString() {

		Map map = EnumUtils.getYesOrNoMap();

		if (isRecommend != null) {
			isRecommendString = (String) map.get(isRecommend);
		} else {
			isRecommendString = (String) map.get(EnumUtils.NO);
		}

		return isRecommendString;
	}

	@Transient
	public String getLogoPicPath() {
		return logoPicPath;
	}

	@Transient
	public String getMiddlePicPath() {
		return middlePicPath;
	}

	public void setLackStatusString(String lackStatusString) {
		this.lackStatusString = lackStatusString;
	}

	public void setTypeString(String typeString) {
		this.typeString = typeString;
	}

	public void setLogoPicPath(String logoPicPath) {
		this.logoPicPath = logoPicPath;
	}

	public void setSmallPicPath(String smallPicPath) {
		this.smallPicPath = smallPicPath;
	}

	public void setMiddlePicPath(String middlePicPath) {
		this.middlePicPath = middlePicPath;
	}

	@Transient
	public String getSmallPicPath() {
		return smallPicPath;
	}

}