package com.sohu.sur.model.admin;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sohu.sur.util.DateUtils;
import com.sohu.sur.util.gift.GiftEnumUtils;

/**
 * 商品兑换实体
 * 
 * @author leiyangbj6779
 */
@Entity
@Table(name = "SUC_GIFT_ACTIVITY")
public class SucExchangeActivity implements java.io.Serializable {

	private static final long serialVersionUID = -1969067285913386884L;
	private Long id;
	private SucExchangeGift exchangeGift;//通过giftId和兑换商品进行一对一的映射
	private Integer type;
	private Integer limitPrice;
	private Integer limitCount;
	private Integer userGiftCount;// 每人次兑换个数
	private Date startTime;
	private Date endTime;
	private Long regionId;

	// -------辅助显示-------
	private Long leftTime;// ”限时“剩余时间，单位：秒
	private String startTimeString;
	private String endTimeString;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@OneToOne(targetEntity = com.sohu.sur.model.admin.SucExchangeGift.class)
	@JoinColumn(name = "GIFT_ID")
	public SucExchangeGift getExchangeGift() {
		return exchangeGift;
	}

	public void setExchangeGift(SucExchangeGift exchangeGift) {
		this.exchangeGift = exchangeGift;
	}

	@Column(name = "TYPE")
	public Integer getType() {
		// 判断当前的活动是否为限时，如果是限时则判断是否已经过期，如果已经过期则将Type置为空
		if (type != null
				&& (type.intValue() == GiftEnumUtils.LIMIT_TIME_COUNT
						.intValue() || type.intValue() == GiftEnumUtils.LIMIT_TIME_COUNT_PRICE
						.intValue())) {
			long intervalTime = DateUtils.interval(new Date(), endTime);

			if (intervalTime <= 0) {
				type = 0;
			}
		}

		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "START_TIME")
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Column(name = "END_TIME")
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "REGION_ID")
	public Long getRegionId() {
		return this.regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	@Column(name = "LIMIT_PRICE")
	public Integer getLimitPrice() {
		return limitPrice;
	}

	@Column(name = "LIMIT_COUNT")
	public Integer getLimitCount() {
		return limitCount;
	}

	public void setLimitPrice(Integer limitPrice) {
		this.limitPrice = limitPrice;
	}

	public void setLimitCount(Integer limitCount) {
		this.limitCount = limitCount;
	}

	@Transient
	public String getTypeName() {
		return "";
	}

	@Column(name = "USER_GIFT_COUNT")
	public Integer getUserGiftCount() {
		return userGiftCount;
	}

	public void setUserGiftCount(Integer userGiftCount) {
		this.userGiftCount = userGiftCount;
	}

	@Transient
	public Long getLeftTime() {

		if (startTime != null && endTime != null) {
			long intervalTime = endTime.getTime() - new Date().getTime();
			leftTime = intervalTime > 0 ? (intervalTime / 1000) : 0;// 单位：秒
		}

		return leftTime;
	}

	@Transient
	public String getStartTimeString() {

		if (startTime != null) {
			startTimeString = DateUtils.getStringDateAsFormat(startTime,
					DateUtils.DEFAULT_DATE_FORMAT);
		}

		return startTimeString;
	}

	@Transient
	public String getEndTimeString() {

		if (endTime != null) {
			endTimeString = DateUtils.getStringDateAsFormat(endTime,
					DateUtils.DEFAULT_DATE_FORMAT);
		}

		return endTimeString;
	}
	
	@Transient
    public boolean isActivityStart() {
        if (startTime != null && new Date().before(startTime)) {//如果为空，默认已经生效
            return false;
        }
        return true;
    }
}