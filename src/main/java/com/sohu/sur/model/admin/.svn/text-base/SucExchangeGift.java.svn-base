package com.sohu.sur.model.admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sohu.sur.base.exception.SucGiftException;
import com.sohu.sur.util.DateDistance;
import com.sohu.sur.util.gift.GiftEnumUtils.ActivityStatus;

/**
 * SucGift entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@DiscriminatorValue(value = "1")
public class SucExchangeGift extends SucAbstractGift {

	private static final long serialVersionUID = 5573593946698446016L;

	private SucExchangeActivity giftActivity; // 商品兑换活动

	private int showValue;
	
	private String limitTimeStr;
	@OneToOne(targetEntity = com.sohu.sur.model.admin.SucExchangeActivity.class, 
			cascade = CascadeType.ALL, mappedBy = "exchangeGift")
	public SucExchangeActivity getGiftActivity() {
		return giftActivity;
	}

	public void setGiftActivity(SucExchangeActivity giftActivity) {
		this.giftActivity = giftActivity;
	}

	@Transient
	public int getShowValue() {
		return getSalePrice(this);
	}

	@Transient
    public long getLimitTimeStr() {
        if (this.getGiftActivity() != null) {
            if (!this.getGiftActivity().isActivityStart()
                    && this.getGiftActivity().getStartTime() != null) {
                return this.getGiftActivity().getStartTime().getTime();
            }
            if (this.getGiftActivity().isActivityStart()
                    && this.getGiftActivity().getEndTime() != null) {
                return this.getGiftActivity().getEndTime().getTime();
            }
        }
        return 0;
    }

	/**
	 * 验证活动规则和获取礼品的单价
	 * 
	 * @param gift
	 *            礼品对象，里面包括了活动的对象
	 * @return 礼品的单价
	 * @throws SucGiftException
	 */
	@Transient
	private int getSalePrice(SucExchangeGift gift) {
		int saleValue = 0;

		SucExchangeActivity activity = gift.getGiftActivity();
		if (activity != null) {

			if (activity.getType().intValue() == ActivityStatus.LimitPrice
					.getValue()) {
				// 限价
				saleValue = activity.getLimitPrice();
			} else if (activity.getType().intValue() == ActivityStatus.LimitCountPrice
					.getValue()) {
				// 限量限价
				saleValue = activity.getLimitPrice();

			} else if (activity.getType().intValue() == ActivityStatus.LimitTimeCount
					.getValue()) {
				// 限时限价

				saleValue = activity.getLimitPrice();

			} else if (activity.getType().intValue() == ActivityStatus.LimitTimeCountPrice
					.getValue()) {
				// 限时限量限价

				saleValue = activity.getLimitPrice();

			} else {
				// 不限
				saleValue = gift.getMarketValue();
			}

		} else {
			saleValue = gift.getMarketValue();
		}

		return saleValue;
	}

}