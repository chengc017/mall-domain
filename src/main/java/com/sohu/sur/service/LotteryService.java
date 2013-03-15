package com.sohu.sur.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sohu.sur.base.exception.NoMedalsException;
import com.sohu.sur.base.exception.SucBuyGiftException;
import com.sohu.sur.base.exception.SucGiftException;
import com.sohu.sur.base.exception.SucLotteryException;
import com.sohu.sur.base.exception.SucScoreException;
import com.sohu.sur.dao.admin.GoodUserDao;
import com.sohu.sur.dao.admin.SucLotteryGiftDao;
import com.sohu.sur.dao.order.MallOrderDao;
import com.sohu.sur.model.admin.MallOrder;
import com.sohu.sur.model.admin.SucAbstractGift;
import com.sohu.sur.model.admin.SucLotteryActivity;
import com.sohu.sur.model.admin.SucLotteryGift;
import com.sohu.sur.util.ChangeBonusResult;
import com.sohu.sur.util.DateUtils;
import com.sohu.sur.util.EnumUtils;
import com.sohu.sur.util.Pagination;
import com.sohu.sur.util.cache.CacheService;
import com.sohu.sur.util.gift.GiftEnumUtils;
import com.sohu.sur.util.lock.LockService;
import com.sohu.sur.util.lottery.Constants;
import com.sohu.sur.util.lottery.LotteryResult;
import com.sohu.sur.util.lottery.LotteryUtils;
import com.sohu.sur.util.lottery.UseMedal;
import com.sohu.sur.util.lottery.UserScoreUtil;

@Service
public class LotteryService {

	private Logger logger = LoggerFactory.getLogger(LotteryService.class);
	@Autowired
	private SucLotteryGiftDao lotteryGiftDao;

	@Autowired
	private MallOrderDao mallOrderDao;

	@Autowired
	private GoodUserDao goodUserDao;

	/**
	 * 验证抽奖条件+扣金币
	 * 
	 * @param lockService
	 * @param sucLotteryGift
	 * @param userId
	 * @param cr
	 * @return
	 * @throws SucBuyGiftException
	 * @throws NoMedalsException
	 * @throws SucLotteryException
	 */
	public MallOrder validateLotteryGift(LockService lockService, SucLotteryGift sucLotteryGift, String userId,
			ChangeBonusResult cr) throws SucBuyGiftException, NoMedalsException, SucLotteryException {

		MallOrder sucLotteryGiftDetail = initLotteryGiftDetail(sucLotteryGift, userId);

		
		try {

			validateGift(sucLotteryGift, sucLotteryGiftDetail);

			int saleValue = sucLotteryGift.getMarketValue();

			if (saleValue == 0) {
				throw new SucBuyGiftException("操作失败，非法礼品信息！礼品价格为 0！");
			}

			sucLotteryGiftDetail.setSaleValue(saleValue);

			/**
			 * 检查当前用户的勋章是否满足
			 */

			UseMedal useMedal = UserScoreUtil.useMedal(sucLotteryGift.getMedals(), userId, saleValue);
			if (null == useMedal)
				throw new NoMedalsException("0", "您没有所需勋章。");
			if (null != useMedal) {
				int medalId = 0;
				try {
					medalId = Integer.parseInt(useMedal.getId());
					System.out.println(useMedal.getId() + "  sds   " + medalId);
					switch (medalId) {
					case -1:
						throw new NoMedalsException("1", "您的金币不足。");
					case -2:
						throw new NoMedalsException("0", "您没有所需勋章。");
					case -3:
						throw new NoMedalsException("-1", "您的金币不足。");
					}
				} catch (NumberFormatException nfe) {
				}//
			}

			int subpoint = sucLotteryGift.getMarketValue();

			/**
			 * 抽奖时扣除积分, 检查积分是否足够?
			 */
			String sdesc = new StringBuffer("{\"msg\":\"").append(sucLotteryGift.getName())
					.append("\",\"url\":\"http://gift.sohu.com/lottery/details/").append(sucLotteryGift.getId())
					.append("\",\"logo\":\"").append(sucLotteryGift.getLogo()).append("\"}").toString();
			// String sdesc = "exchange";

			cr = UserScoreUtil.adjustUserBonus(sucLotteryGiftDetail.getUserId(), -1 * subpoint, sdesc, "lottery");
			if (cr == null)
				throw new SucScoreException("扣除金币操作失败！");
			if (cr.getResult() == 8)
				throw new NoMedalsException("-1", "您的金币不足");
			if (cr.getResult() != 0)
				throw new SucScoreException("扣除金币操作失败！");

		} catch (SucBuyGiftException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (NoMedalsException e) {
			logger.error(e.getMessage(), e);
			throw e;
		} catch (Exception ep) {
			logger.error(ep.getMessage(), ep);
			throw new SucLotteryException();
		} 

		return sucLotteryGiftDetail;
	}

	/**
	 * 根据商品和用户 初始化订单部分信息
	 * 
	 * @param sucLotteryGift
	 * @param userId
	 * @return
	 */
	private MallOrder initLotteryGiftDetail(SucLotteryGift sucLotteryGift, String userId) {

		MallOrder detail = new MallOrder();
		detail.setGiftId(sucLotteryGift.getId());
		detail.setUserId(userId);
		detail.setDealId(DateUtils.getYearNo() + Constants.DEAL_LOTTERY_TYPE + System.currentTimeMillis());
		detail.setCount(new Integer(1));

		detail.setType(Constants.GIFT_DETAIL_LOTTERY_TYPE);
		detail.setCreateTime(new Date());
		detail.setRegionId(Constants.regionId);
		detail.setGiftType(sucLotteryGift.getGift_type());
		detail.setStatus(sucLotteryGift.getGift_type().equals(1) ? GiftEnumUtils.FINISHED : GiftEnumUtils.PENDING); // 虚拟物品订单生成，状态即为已完成

		return detail;
	}

	/**
	 * 验证礼品的库存数量 验证礼品是否缺货（自动缺货和人为缺货） 验证礼品是否已经下架时 验证购买数量是否超过库存
	 * 
	 * 
	 * @param gift
	 *            当前的礼品对象
	 * @param giftDetail
	 *            礼品订单明细对象
	 * @throws SucGiftException
	 */
	public void validateGift(SucAbstractGift gift, MallOrder giftDetail) throws SucBuyGiftException {

		// 用户填写的兑换数量是否正确
		if (giftDetail.getCount() == null || giftDetail.getCount() == 0) {
			throw new SucBuyGiftException("操作失败，必须要填写兑换的礼品的数量！");
		}

		// 自动缺货，库存数量为0
		if (gift.getStockCount() == null || gift.getStockCount() == 0) {
			throw new SucBuyGiftException("操作失败，该礼品库存不足！");
		}

		// 人为缺货，缺货状态为“缺货”
		if (GiftEnumUtils.HAVE.intValue() != gift.getLackStatus().intValue()) {
			throw new SucBuyGiftException("操作失败，该礼品已经缺货！");
		}

		// 验证购买数量是否超过库存
		if (giftDetail.getCount().intValue() > gift.getStockCount().intValue()) {
			throw new SucBuyGiftException("操作失败，库存中只剩余" + gift.getStockCount() + "个！");
		}

		// “已上架”和“待重新上架”的允许购买; “未上架”或者“已下架”都不允许购买
		if (GiftEnumUtils.SHELFED.intValue() != gift.getIsShelf().intValue()
				&& GiftEnumUtils.RESHELF.intValue() != gift.getIsShelf().intValue()) {
			throw new SucBuyGiftException("操作失败，该礼品已经下架！");
		}

	}

	/**
	 * 执行抽奖的操作
	 * 
	 * @see com.sohu.suc.gift.service.LotteryService#executeLottery(SucLotteryGift,
	 *      java.lang.String, java.lang.Long)
	 */
	public Long executeLottery(CacheService cacheService, SucLotteryGift sucLotteryGift, String userId, Long id) {

		Long result = new Long(Constants.LotteryMissHit);

		try {
			/**
			 * 抽奖操作 增加白名单中奖判断
			 */
			result = doLettoryByGoodUser(cacheService, sucLotteryGift, userId);

			if (result.intValue() != Constants.LotteryMissHit && overEveryDayCountRestrict(sucLotteryGift)) {
				return new Long(Constants.LotteryMissHit);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}

	/**
	 * 每天抽奖数判断
	 * 
	 * @param sucLotteryGift
	 * @return
	 * @throws Exception
	 */
	private boolean overEveryDayCountRestrict(SucLotteryGift sucLotteryGift) throws Exception {

		boolean flag = false;
		SucLotteryActivity lotteryActivity = sucLotteryGift.getLotteryActivity();
		logger.info("lotteryActivity.getType()="+lotteryActivity.getType());
		logger.info("lotteryActivity.getLimitTimeCount()="+lotteryActivity.getLimitTimeCount());
		logger.info("lotteryActivity.getEverydayCount()="+lotteryActivity.getEverydayCount());
		if (GiftEnumUtils.LOTTERY_ACTIVITY_TYPE_EVERYDAY.intValue() == lotteryActivity.getType()|| GiftEnumUtils.LOTTERY_ACTIVITY_TYPE_LIMITTIME.intValue() == lotteryActivity.getType()) {
			// 每天
			Date date = new Date();
			String startDate = DateUtils.getStringDateAsFormat(date, DateUtils.DATE_FORMAT);// 每天的开始时间
			String endDate = DateUtils.getAfterDate(date, DateUtils.DATE_FORMAT); // 每天的结束时间

			// 每天的抽奖数
			int count = getLotteryGiftCount(sucLotteryGift.getRegionId(), sucLotteryGift.getId(),
					DateUtils.getDateByString(startDate, DateUtils.DATE_FORMAT),
					DateUtils.getDateByString(endDate, DateUtils.DATE_FORMAT));
			logger.info("count="+count);
			if (lotteryActivity.getLimitTimeCount() != null
                    && count >= lotteryActivity.getLimitTimeCount()) {
				logger.info("LimitTimeCount");
				flag =  true;
			}
			
			if (lotteryActivity.getEverydayCount() != null
                    && count >= lotteryActivity.getEverydayCount()) {
				logger.info("EverydayCount");
				flag =  true;
            }
		}
		logger.info(""+flag);
		return flag;
	}

	/**
	 * 查询指定时间段内的被抽奖抽中的礼品的个数
	 * 
	 * @param regionId
	 *            域id
	 * @param giftId
	 *            礼品id
	 * @param startTime
	 *            开始时间（可选项）
	 * @param endTime
	 *            结束时间（可选项）
	 * @return
	 */
	private int getLotteryGiftCount(Long regionId, Long giftId, Date startTime, Date endTime) {

		int userGiftCount = 0;

		// 订单状态为有效的,即为非“已取消”和“已作废”的订单
		StringBuilder queryString = new StringBuilder(
				"from MallOrder a where a.regionId = ? and a.giftId = ? and (a.status != ? or a.status != ?)");

		List params = new ArrayList();
		params.add(regionId);
		params.add(giftId);
		params.add(GiftEnumUtils.CANCEL);
		params.add(GiftEnumUtils.INVALIDATED);

		if (startTime != null) {
			queryString.append(" and a.createTime >= ?");
			params.add(startTime);
		}

		if (endTime != null) {
			queryString.append(" and a.createTime <= ?");
			params.add(endTime);
		}

		userGiftCount = lotteryGiftDao.getListByParams(queryString.toString(), params.toArray()).size();

		return userGiftCount;
	}
	/**
	 * 查找最近中奖纪录
	 * @param type
	 * @param userId
	 * @param pagination
	 * @return
	 */
	public Date userLastLotteryRecord(String type, String userId,Pagination pagination) {
		Date date = null;
		List result = null;

		try {
			String queryString = "from MallOrder where regionId = 1 and userId = ? and type = ? order by createTime desc";
			Object[] params = { userId,type  };
			result = this.lotteryGiftDao.getObjectList(queryString, pagination,params);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		if (result != null && result.size() > 0) {
			MallOrder sag = (MallOrder) result.get(0);
			date = sag.getCreateTime();
		}
		return date;
	}
	/**
	 * 返回true表示可以中奖 false不可以中奖
	 * @param now
	 * @param lastLottery
	 * @return
	 */
	public boolean duiBi(Date now,Date lastLottery){
		boolean flag = false;
		if(lastLottery==null){
			flag = true;
		}else if((now.getTime()-lastLottery.getTime())/(1000*3600*24*1.0)>15){
			flag = true;
		}
		return flag;
	}
	/**
	 * 用户抽奖（增加优质用户判断）
	 * 
	 * @param cacheService
	 * @param lotteryGift
	 * @param userId
	 * @return
	 */
	private Long doLettoryByGoodUser(CacheService cacheService, SucLotteryGift lotteryGift, String userId) {
		// 非优质用户标志
		boolean flag = false;

		Long result = new Long(Constants.LotteryMissHit);
		try {
			SucLotteryActivity lotteryActivity = lotteryGift.getLotteryActivity();

			/**
			 * 抽奖方案
			 */
			if (GiftEnumUtils.LOTTERY_ACTIVITY_SCENARIO_1.intValue() == lotteryActivity.getScenario()) {

				// 抽奖方案一:逢多少用户中奖
				// 规则是key为域id+"_"+礼品id + "_" + 礼品抽奖活动id
				String key = lotteryActivity.getRegionId() + Constants.CHAR_UNDERLINE + lotteryGift.getId()
						+ Constants.CHAR_UNDERLINE + lotteryActivity.getId();
				long lotteryNumber = LotteryUtils.incrementLotteryUserCountAndGet(this, cacheService, key);
				
				//5天之内不能再中奖判断
				Date nowDate = new Date();
				Pagination pagination = new Pagination(1,100);
				Date lastLotteryDate = this.userLastLotteryRecord("2", userId,pagination);
				
				// 设置有金牌用户抽即中奖
				if (lotteryActivity.getRewardStation() == 2) {
					// 如果是金牌用户则中奖 反之。。
					flag = this.goodUserDao.isGoodUser(userId, "1");
					if (flag)
						result = new Long(Constants.LotterySuccess);
				}
				// 逢数即中
				else if (lotteryActivity.getRewardStation() == 1) {
					if (lotteryNumber > 0 && (lotteryNumber % lotteryActivity.getScenOneCount() == 0)) {
						// 如果是优质用户则中奖 反之。。
						flag = this.goodUserDao.isGoodUser(userId, "0");
						//所有用户并且（没有中过奖或者15天之外再次抽奖)才可以中奖
						if (flag&&this.duiBi(nowDate, lastLotteryDate))
							result = lotteryNumber;
					}
				}
				// 无特殊用户抽奖限制
				else {
					if (lotteryNumber > 0 && (lotteryNumber % lotteryActivity.getScenOneCount() == 0)) {
						//所有用户并且（没有中过奖或者15天之外再次抽奖)才可以中奖
 						if(this.duiBi(nowDate, lastLotteryDate))
						result = lotteryNumber;
					}
				}
			} else if (GiftEnumUtils.LOTTERY_ACTIVITY_SCENARIO_2.intValue() == lotteryActivity.getScenario()) {
				// 抽奖方案二:预设中奖号码，从奖池中抽取号码和预设号码比较
				String key = lotteryActivity.getRegionId() + Constants.CHAR_UNDERLINE + lotteryGift.getId()
						+ Constants.CHAR_UNDERLINE + lotteryActivity.getId();
				long lotteryCount = LotteryUtils.incrementLotteryUserCountAndGet(this, cacheService, key);
				long lotteryNumber = LotteryUtils.getRandom(lotteryActivity.getScenTwoStart(),
						lotteryActivity.getScenTwoEnd());
				if (lotteryNumber == lotteryActivity.getScenTwoNumber()) {
					// 中奖
					result = lotteryNumber;
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return result;
	}

	/**
	 * 创建抽奖礼品订单（兑换记录）
	 */
	public LotteryResult createLotteryGiftDetail(SucLotteryGift sucLotteryGift, MallOrder sucLotteryGiftDetail,
			ChangeBonusResult cr) throws SucBuyGiftException, SucScoreException, SucLotteryException {
		LotteryResult lresult = new LotteryResult();
		try {

			// 将礼品名称保存到礼品兑换明细中
			sucLotteryGiftDetail.setGiftName(sucLotteryGift.getName());
			sucLotteryGiftDetail.setSaleValue(sucLotteryGift.getMarketValue());

			/**
			 * 保存礼品兑换明细（订单）
			 */
			mallOrderDao.save(sucLotteryGiftDetail);

			/**
			 * 当消费成功后更新库存
			 */
			updateLotteryGiftStock(sucLotteryGift);

			// success
			lresult.setStatus(3);
			lresult.setGiftdealid(sucLotteryGiftDetail.getDealId());
			lresult.setSubpoint(sucLotteryGiftDetail.getSaleValue());
			lresult.setRemain(cr.getBonus());
			lresult.setCat(sucLotteryGiftDetail.getGiftType() == 0 ? "1" : "2");
			// 同步mongodb
			UserScoreUtil.syncStoreDeals(sucLotteryGiftDetail.getUserId(), sucLotteryGiftDetail.getId() == null ? ""
					: sucLotteryGiftDetail.getId().toString(), sucLotteryGiftDetail.getType(), sucLotteryGiftDetail
					.getCount().toString(), sucLotteryGiftDetail.getSaleValue().toString(), sucLotteryGiftDetail
					.getGiftId().toString(), sucLotteryGiftDetail.getGiftName(), sucLotteryGift.getLogo(),
					sucLotteryGiftDetail.getDealId(),sucLotteryGift.getGift_type());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new SucLotteryException();
		}

		return lresult;
	}

	/**
	 * 更新抽奖礼品的库存数量
	 * 
	 * @param lotteryGift
	 *            抽奖礼品对象
	 * @throws SucLotteryException
	 */
	private void updateLotteryGiftStock(SucLotteryGift lotteryGift) throws SucLotteryException {
		if (lotteryGift.getStockCount() - 1 > 0) {
			lotteryGift.setStockCount(lotteryGift.getStockCount() - 1);
		} else {
			lotteryGift.setStockCount(0);
			lotteryGift.setIsShelf(0);
		}

		try {
			lotteryGiftDao.saveOrUpdate(lotteryGift);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

			throw new SucLotteryException(e);
		}
	}

	/**
	 * 将抽奖礼品的抽奖活动中方案一（逢多少用户中奖）中的当前参与的用户数更新到数据库中
	 * 
	 * @see com.sohu.suc.gift.service.LotteryService#updateLotteryUserCount(java.lang.Long,
	 *      java.lang.Long, java.lang.Long, long)
	 */
	public void updateLotteryUserCount(Long regionId, Long giftId, Long lotteryActivityId, long currLotteryUserCount)
			throws SucLotteryException {

		try {
			/*
			 * String queryString =
			 * "update SucLotteryActivity set scenOneCurCount = ? where regionId = ? and lotteryGift.id = ? and id = ? "
			 * ; Object[] params = {currLotteryUserCount, regionId, giftId,
			 * lotteryActivityId}; parentGiftDao.updateByParams(queryString,
			 * params);
			 */
			String queryString = "from SucLotteryGift where regionId = ? and id = ?";
			Object[] params = { regionId, giftId };
			SucLotteryGift lotteryGift = lotteryGiftDao.getListByParams(queryString, params).get(0);
			SucLotteryActivity lotteryActivity = lotteryGift.getLotteryActivity();
			
			if (lotteryActivity.getId().equals(lotteryActivityId)){
				lotteryActivity.setScenOneCurCount(currLotteryUserCount);
			}
				lotteryGift.setLotteryActivity(lotteryActivity);
				logger.info("逢50更新 "+giftId);
			lotteryGiftDao.saveOrUpdate(lotteryGift);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new SucLotteryException(e);
		}
	}

	/**
	 * 获取商品已抽奖数
	 * 
	 * @param regionId
	 * @param giftId
	 * @param lotteryActivityId
	 * @return
	 */
	public long getLotteryUserCount(Long regionId, Long giftId, Long lotteryActivityId) {
		String queryString = "select ScenOneCurCount from SucLotteryActivity where regionId = ?  and id = ? ";
		Object[] params = { regionId,  lotteryActivityId };
		List list = lotteryGiftDao.getListByParams(queryString.toString(), params);
		long result = 0;
		if(list!=null&&list.size()>0){
			result = (Long)list.get(0);
		}
		logger.info("load from da 抽奖数="+result);
		return result;
	}
	
	 /**
     * （金币前台）抽奖礼品列表
     * 
     * @see com.sohu.suc.gift.service.LotteryService#getLotteryGiftList(java.lang.Long, com.sohu.suc.util.Pagination)
     */
    public List<SucLotteryGift> getLotteryGiftList(Long regionId, Pagination pagination) {
        List result = null;
        try {
            String queryString = "from SucLotteryGift where regionId = ? and isShelf =? and isRecommend = ? order by operateTime desc";
            Object[] params = { regionId, GiftEnumUtils.SHELFED, EnumUtils.NO };
            result = this.lotteryGiftDao.getObjectList(queryString, pagination, params);
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        }
        return result;
    }

}
