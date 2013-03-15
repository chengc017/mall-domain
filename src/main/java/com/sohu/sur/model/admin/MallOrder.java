package com.sohu.sur.model.admin;

import java.util.Date;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import com.sohu.sur.util.DateDistance;
import com.sohu.sur.util.gift.GiftEnumUtils;
import com.sohu.sur.util.gift.GiftEnumUtils.GiftDetailStatus;

/**
 * 订单管理查询 实体对象
 * 
 * @author xuewuhao
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SUC_GIFT_DETAIL")
public class MallOrder implements java.io.Serializable {

	private Long id;
	/**
	 * 1兑换 2抽奖
	 */
	private String type;
	private String dealId;
	private Long giftId;
	private String giftName;
	private String userId;
	private Integer count;
	private Integer saleValue;
	private Date operateTime;
	private Date createTime;
	private String receiveUserName;
	private String receiveTel;
	private String receiveAddr;
	private String receivePostCode;
	private String descr;
	private Long regionId;
	private Integer status;
	private String operator;
	private String useMedal;
	private Integer discount;

	private Integer giftType;
	private String userIp;
	private String memo; // 回执

	// 辅助显示
	private String statusString;
	private String typeString;
	private String hitMinites; /* 交易时长,分钟 */
	private String userDesc; /* 用户desc */
	private String userUrl;//连接到用户积分概览页面的Url
	/** default constructor */
	public MallOrder() {
	}

	/** full constructor */
	public MallOrder(Long giftId, String giftName, String dealId, String userId, Integer count, Integer saleValue,
			Date operateTime, Date createTime, String receiveUserName, String receiveTel, String receiveAddr,
			String receivePostCode, String descr, Long regionId, Integer status, String operator, String type,
			Integer giftType) {

		this.dealId = dealId;
		this.giftId = giftId;
		this.giftName = giftName;
		this.userId = userId;
		this.count = count;
		this.saleValue = saleValue;
		this.operateTime = operateTime;
		this.createTime = createTime;
		this.receiveUserName = receiveUserName;
		this.receiveTel = receiveTel;
		this.receiveAddr = receiveAddr;
		this.receivePostCode = receivePostCode;
		this.descr = descr;
		this.regionId = regionId;
		this.status = status;
		this.operator = operator;
		this.type = type;
		this.giftType = giftType;
	}

	@Column(name = "GIFT_TYPE")
	public Integer getGiftType() {
		return giftType;
	}

	public void setGiftType(Integer giftType) {
		this.giftType = giftType;
	}

	@Column(name = "USEMEDAL")
	public String getUseMedal() {
		return useMedal;
	}

	public void setUseMedal(String useMedal) {
		this.useMedal = useMedal;
	}

	@Column(name = "DISCOUNT")
	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "GIFT_ID")
	public Long getGiftId() {
		return this.giftId;
	}

	public void setGiftId(Long giftId) {
		this.giftId = giftId;
	}

	@Column(name = "REGION_ID")
	public Long getRegionId() {
		return this.regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	@Column(name = "COUNT")
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Column(name = "SALE_VALUE")
	public Integer getSaleValue() {
		return saleValue;
	}

	public void setSaleValue(Integer saleValue) {
		this.saleValue = saleValue;
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

	@Column(name = "RECEIVE_USERNAME")
	public String getReceiveUserName() {
		return receiveUserName;
	}

	@Column(name = "RECEIVE_TEL")
	public String getReceiveTel() {
		return receiveTel;
	}

	@Column(name = "RECEIVE_ADDR")
	public String getReceiveAddr() {
		return receiveAddr;
	}

	@Column(name = "DESCR")
	public String getDescr() {
		return descr;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setReceiveUserName(String receiveUserName) {
		this.receiveUserName = receiveUserName;
	}

	public void setReceiveTel(String receiveTel) {
		this.receiveTel = receiveTel;
	}

	public void setReceiveAddr(String receiveAddr) {
		this.receiveAddr = receiveAddr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	@Column(name = "RECEIVE_POSTCODE")
	public String getReceivePostCode() {
		return receivePostCode;
	}

	public void setReceivePostCode(String receivePostCode) {
		this.receivePostCode = receivePostCode;
	}

	@Column(name = "USER_ID")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "DEAL_ID")
	public String getDealId() {
		return dealId;
	}

	public void setDealId(String dealId) {
		this.dealId = dealId;
	}

	@Column(name = "STATUS")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "OPERATOR")
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "GIFT_NAME")
	public String getGiftName() {
		return giftName;
	}

	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}

	@Column(name = "TYPE")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "USERIP")
	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	@Column(name = "MEMO")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Transient
	public String getStatusString() {

		if (status != null) {
			GiftDetailStatus giftDetailStatus = GiftEnumUtils.getGiftDetailStatus(status);

			statusString = giftDetailStatus.getName();

		} else {
			statusString = GiftEnumUtils.GiftDetailStatus.Pending.getName();
		}

		return statusString;
	}
	@Transient
	public String getUserUrl() {
		return userUrl;
	}

	public void setUserUrl(String userUrl) {
		this.userUrl = userUrl;
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
	public String getHitMinites() {
		return DateDistance.getDistanceTimeStr(new Date(), this.getCreateTime());
	}

	@Transient
	public String getUserDesc() {
		if (userId != null && userId.trim().length() > 0 && userId.trim().indexOf("@") > 0)
			return userId.substring(0, userId.indexOf("@"));
		return userId;
	}
}
