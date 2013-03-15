package com.sohu.sur.model.admin;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * SucLotteryGift entity. @author MyEclipse Persistence Tools
 */
@Entity
@DiscriminatorValue(value = "2")
public class SucLotteryGift extends SucAbstractGift {

	private static final long serialVersionUID = -1798454201712888243L;
	private SucLotteryActivity lotteryActivity;

	@OneToOne(targetEntity = com.sohu.sur.model.admin.SucLotteryActivity.class ,
			cascade=CascadeType.ALL ,mappedBy = "lotterGift")
	public SucLotteryActivity getLotteryActivity() {
		return lotteryActivity;
	}

	public void setLotteryActivity(SucLotteryActivity lotteryActivity) {
		this.lotteryActivity = lotteryActivity;
	}
	@Transient
	public long getLimitTimeStr() {
		if (this.getLotteryActivity() != null) {
			if (!this.getLotteryActivity().isActivityStart()
					&& this.getLotteryActivity().getStartTime() != null) {
				return this.getLotteryActivity().getStartTime().getTime();
			}
			if (this.getLotteryActivity().isActivityStart()
					&& this.getLotteryActivity().getEndTime() != null) {
				return this.getLotteryActivity().getEndTime().getTime();
			}
		}
		return 0;
	}//
	
}