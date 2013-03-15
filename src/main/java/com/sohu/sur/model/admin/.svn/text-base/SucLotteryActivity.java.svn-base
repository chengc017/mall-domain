package com.sohu.sur.model.admin;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * SucLotteryActivity entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SUC_LOTTERY_ACTIVITY")
public class SucLotteryActivity implements java.io.Serializable {

	private Long id;
//	private Long giftId;//通过giftId和抽奖商品进行一对一的映射
	private Integer type;
	private SucLotteryGift lotterGift;
	private Date startTime;
	private Date endTime;
	private Integer limitTimeCount;
	private Integer everydayCount;
	private Integer scenario;
	private Long scenOneCount;
	private Long scenOneCurCount;
	private Long scenTwoNumber;
	private Long scenTwoStart;
	private Long scenTwoEnd;
	private Integer limitPrice;
	private Long regionId;
	
	/**
	 * 中奖用户条件限制 
	 * REWARDSTATION.put("0", "无");
	   REWARDSTATION.put("1", "缝数即中");
       REWARDSTATION.put("2", "抽即中");
	 */
	private Integer rewardStation=0;

	// -------辅助显示-------
	private Long leftTime;// ”限时“剩余时间，单位：秒

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToOne(targetEntity = com.sohu.sur.model.admin.SucLotteryGift.class)
	@JoinColumn(name = "GIFT_ID")
	public SucLotteryGift getLotterGift() {
		return lotterGift;
	}

	public void setLotterGift(SucLotteryGift lotterGift) {
		this.lotterGift = lotterGift;
	}

	@Column(name = "TYPE")
	public Integer getType() {
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

	@Column(name = "SCENARIO")
	public Integer getScenario() {
		return this.scenario;
	}

	public void setScenario(Integer scenario) {
		this.scenario = scenario;
	}

	@Column(name = "SCEN_ONE_COUNT")
	public Long getScenOneCount() {
		return this.scenOneCount;
	}

	public void setScenOneCount(Long scenOneCount) {
		this.scenOneCount = scenOneCount;
	}

	@Column(name = "SCEN_ONE_CUR_COUNT")
	public Long getScenOneCurCount() {
		return this.scenOneCurCount;
	}

	public void setScenOneCurCount(Long scenOneCurCount) {
		this.scenOneCurCount = scenOneCurCount;
	}
	@Column(name = "REWARD_STATION")
	public Integer getRewardStation() {
		if(rewardStation==null){
			rewardStation=0;
		}
		return rewardStation;
	}

	public void setRewardStation(Integer rewardStation) {
		this.rewardStation = rewardStation;
	}
	@Column(name = "SCEN_TWO_NUMBER")
	public Long getScenTwoNumber() {
		return this.scenTwoNumber;
	}

	public void setScenTwoNumber(Long scenTwoNumber) {
		this.scenTwoNumber = scenTwoNumber;
	}

	@Column(name = "SCEN_TWO_START")
	public Long getScenTwoStart() {
		return this.scenTwoStart;
	}

	public void setScenTwoStart(Long scenTwoStart) {
		this.scenTwoStart = scenTwoStart;
	}

	@Column(name = "SCEN_TWO_END")
	public Long getScenTwoEnd() {
		return this.scenTwoEnd;
	}

	public void setScenTwoEnd(Long scenTwoEnd) {
		this.scenTwoEnd = scenTwoEnd;
	}

	@Column(name = "LIMIT_PRICE")
	public Integer getLimitPrice() {
		return this.limitPrice;
	}

	public void setLimitPrice(Integer limitPrice) {
		this.limitPrice = limitPrice;
	}

	@Column(name = "REGION_ID")
	public Long getRegionId() {
		return this.regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	@Column(name = "LIMIT_TIME_COUNT")
	public Integer getLimitTimeCount() {
		return limitTimeCount;
	}

	@Column(name = "EVERYDAY_COUNT")
	public Integer getEverydayCount() {
		return everydayCount;
	}

	public void setLimitTimeCount(Integer limitTimeCount) {
		this.limitTimeCount = limitTimeCount;
	}

	public void setEverydayCount(Integer everydayCount) {
		this.everydayCount = everydayCount;
	}
	@Transient
	public boolean isActivityStart(){
		if(startTime != null  &&  new Date().before(startTime)){//如果为空，默认已经生效
			return false;
		}
		return true;
	}
	@Transient
	public Long getLeftTime() {

		if (startTime != null && endTime != null) {
			long intervalTime = endTime.getTime() - new Date().getTime();
			leftTime = intervalTime > 0 ? (intervalTime / 1000) : 0;// 单位：秒
		}

		return leftTime;
	}

}