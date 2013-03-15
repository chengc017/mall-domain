package com.sohu.sur.util.lottery;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.sohu.sur.util.gift.GiftEnumUtils;

/**
 * User: guoyong Date: 4/6/11 3:45 PM
 */
public class GiftQueryCriteria {

	private long sortId;

	private String type;

	private boolean limitedPrice;

	private boolean limitedTime;

	private int minPriceValue;

	private int maxPriceValue;

	private int userBonusValue;

	private String userMedals;

	private String medal;

	public GiftQueryCriteria() {
	}

	public long getSortId() {
		return sortId;
	}

	public void setSortId(long sortId) {
		this.sortId = sortId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isLimitedPrice() {
		return limitedPrice;
	}

	public void setLimitedPrice(boolean limitedPrice) {
		this.limitedPrice = limitedPrice;
	}

	public boolean isLimitedTime() {
		return limitedTime;
	}

	public void setLimitedTime(boolean limitedTime) {
		this.limitedTime = limitedTime;
	}

	public int getMinPriceValue() {
		return minPriceValue;
	}

	public void setMinPriceValue(int minPriceValue) {
		this.minPriceValue = minPriceValue;
	}

	public int getMaxPriceValue() {
		return maxPriceValue;
	}

	public void setMaxPriceValue(int maxPriceValue) {
		this.maxPriceValue = maxPriceValue;
	}

	public int getUserBonusValue() {
		return userBonusValue;
	}

	public void setUserBonusValue(int userBonusValue) {
		this.userBonusValue = userBonusValue;
	}

	public String getUserMedals() {
		return userMedals;
	}

	public void setUserMedals(String userMedals) {
		this.userMedals = userMedals;
	}

	public void setMedal(String medal) {
		this.medal = medal;
	}

	public String getMedal() {
		return medal;
	}

	public String getQueryString() {
		StringBuffer ret = new StringBuffer();

		if (sortId > 0) {
			ret.append(" and g.sortId=?");
		}

		if (!StringUtils.isEmpty(this.type)) {
			ret.append(" and g.type=?");
		}

		if(this.isLimitedTime()){
			ret.append(" and (ga.type in (?,?) or la.type in (?,?) )");
		}else if(this.isLimitedPrice()){
			ret.append(" and (ga.type in (?,?,?,?) or la.type in (?,?,?,?) )");
		}
		if (this.getMinPriceValue() >= 0) {
			ret.append(" and g.marketValue>=?");
		}
		if (this.getMaxPriceValue() >= 0) {
			ret.append(" and g.marketValue<=?");
		}
		if (this.getUserBonusValue() > 0) {
			ret.append(" and g.marketValue<=?");
		}
		if (StringUtils.isNotEmpty(this.getMedal())
				&& !"all".equals(this.getMedal())) {
			ret.append(" and (g.medals=null or g.medals='' or g.medals like ?)");
		}
		return ret.toString();
	}

	public Object[] getQueryParams(Object... params) {

		List<Object> paramsList = new ArrayList<Object>(10);

		for (Object param : params) {
			paramsList.add(param);
		}

		if (sortId > 0) {
			paramsList.add(sortId);
		}

		if (!StringUtils.isEmpty(this.type)) {
			paramsList.add(type);
		}

		if (this.isLimitedTime()) {
			paramsList.add(GiftEnumUtils.LIMIT_TIME_COUNT);
			paramsList.add(GiftEnumUtils.LIMIT_TIME_COUNT_PRICE);
			paramsList.add(GiftEnumUtils.LIMIT_TIME_COUNT);
			paramsList.add(GiftEnumUtils.LIMIT_TIME_COUNT_PRICE);
		} else if (this.isLimitedPrice()) {
			paramsList.add(GiftEnumUtils.LIMIT_PRICE);
			paramsList.add(GiftEnumUtils.LIMIT_COUNT_PRICE);
			paramsList.add(GiftEnumUtils.LIMIT_TIME_COUNT);
			paramsList.add(GiftEnumUtils.LIMIT_TIME_COUNT_PRICE);
			paramsList.add(GiftEnumUtils.LIMIT_PRICE);
			paramsList.add(GiftEnumUtils.LIMIT_COUNT_PRICE);
			paramsList.add(GiftEnumUtils.LIMIT_TIME_COUNT);
			paramsList.add(GiftEnumUtils.LIMIT_TIME_COUNT_PRICE);
		}
		if (this.getMinPriceValue() >= 0) {
			paramsList.add(this.getMinPriceValue());
		}
		if (this.getMaxPriceValue() >= 0) {
			paramsList.add(this.getMaxPriceValue());
		}
		if (this.getUserBonusValue() > 0) {
			paramsList.add(this.getUserBonusValue());
		}
		if (StringUtils.isNotEmpty(this.getMedal())
				&& !"all".equals(this.getMedal())) {
			paramsList.add("%" + this.getMedal() + "%");
		}
		return paramsList.toArray();
	}
}
