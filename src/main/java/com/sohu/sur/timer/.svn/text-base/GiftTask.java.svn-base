package com.sohu.sur.timer;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sohu.sur.model.admin.SucAbstractGift;
import com.sohu.sur.service.GiftService;
import com.sohu.sur.util.gift.GiftEnumUtils;

/**
 * @author lukeli
 * 
 *         判断礼品是否到期，礼品活动是否到期
 */
public class GiftTask {

	private Log log = LogFactory.getLog(getClass());

	private GiftService giftService;

	public void execute() {
		if(log.isDebugEnabled()){
			log.debug("check whether the gift is shelf");
		}
		// 前台获取活动type类型时，会处理type，这里不再对活动进行处理，仅判断上下架
		// SucGiftActivity.getType()做了处理，后台再处理没什么意义，如果要改动的话
		// 涉及的代码会比较多
		try {
			//事务需要控制
			String queryString = "from SucAbstractGift where regionId = 1 and (isShelf = ? or isShelf = ?) and (endTime is not null and endTime < ?)";
			Object params[] = { GiftEnumUtils.SHELFED, GiftEnumUtils.RESHELF, new Date() };
			List<SucAbstractGift> giftList = this.getGiftService().getGiftList(queryString, params, null);
			for (SucAbstractGift gift : giftList) {
				if (gift.getIsShelf().equals(GiftEnumUtils.SHELFED) || gift.getIsShelf().equals(GiftEnumUtils.RESHELF)) {
					gift.setIsShelf(GiftEnumUtils.OFF_SHELF);
					this.getGiftService().deploy(gift.getId(), GiftEnumUtils.ShelfStatus.OffShelf);
					log.info("Gift " + gift.getId() + " off shelf...");
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
	}

	public GiftService getGiftService() {
		return giftService;
	}

	public void setGiftService(GiftService giftService) {
		this.giftService = giftService;
	}
}
