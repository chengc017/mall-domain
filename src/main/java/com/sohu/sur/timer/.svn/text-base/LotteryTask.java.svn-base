package com.sohu.sur.timer;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sohu.sur.model.admin.SucLotteryActivity;
import com.sohu.sur.model.admin.SucLotteryGift;
import com.sohu.sur.service.GiftService;
import com.sohu.sur.util.gift.GiftEnumUtils;

/**
 * @author lukeli
 * 
 *         判断抽奖礼品是否到期，抽奖活动是否到期
 */
public class LotteryTask {

	private Log log = LogFactory.getLog(getClass());

	private GiftService giftService;

	public void execute() {
		if (log.isDebugEnabled()) {
			log.debug("check whether the lottery is shelf...");
		}
		try {

			String queryString = "from SucLotteryGift where regionId = 1 and (isShelf =? or isShelf = ?)";
			Object[] params = { GiftEnumUtils.SHELFED, GiftEnumUtils.RESHELF };
			Date now = new Date();
			boolean modify = false;
			List<SucLotteryGift> lotteryList = this.getGiftService()
					.getLotteryGiftList(queryString, params, null);
			for (SucLotteryGift lottery : lotteryList) {
				modify = false;
				SucLotteryActivity lotteryActivity = lottery
						.getLotteryActivity();
				if (lottery.getIsShelf().equals(GiftEnumUtils.SHELFED)
						|| lottery.getIsShelf().equals(GiftEnumUtils.RESHELF)) {
					if (lottery.getEndTime() != null
							&& lottery.getEndTime().before(now)) {
						lottery.setIsShelf(GiftEnumUtils.OFF_SHELF);
						modify = true;
						log.info("Lottery " + lottery.getId() + " off shelf...");
					}
				}
				if (lotteryActivity != null
						&& !lotteryActivity.getType().equals(
								GiftEnumUtils.NO_LIMIT)
						&& lotteryActivity.getEndTime() != null
						&& lotteryActivity.getEndTime().before(now)) {
					lotteryActivity.setType(GiftEnumUtils.NO_LIMIT);
					modify = true;
					log.info("Lottery " + lottery.getId() + " activity over...");
				}
				if (modify) {
					this.getGiftService().updateLotteryGift(lottery);
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
