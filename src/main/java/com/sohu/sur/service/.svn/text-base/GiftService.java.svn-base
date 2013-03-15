package com.sohu.sur.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sohu.sur.base.exception.SucGiftException;
import com.sohu.sur.base.exception.SucLotteryException;
import com.sohu.sur.dao.admin.SucAbstractGiftDao;
import com.sohu.sur.dao.admin.SucExchangeGiftDao;
import com.sohu.sur.dao.admin.SucLotteryGiftDao;
import com.sohu.sur.model.admin.SucAbstractGift;
import com.sohu.sur.model.admin.SucExchangeActivity;
import com.sohu.sur.model.admin.SucExchangeGift;
import com.sohu.sur.model.admin.SucLotteryGift;
import com.sohu.sur.util.EnumUtils;
import com.sohu.sur.util.Pagination;
import com.sohu.sur.util.PaginationSupport;
import com.sohu.sur.util.gift.GiftEnumUtils;
import com.sohu.sur.util.gift.GiftEnumUtils.ActivityStatus;
import com.sohu.sur.util.gift.GiftEnumUtils.LackStatus;
import com.sohu.sur.util.gift.GiftEnumUtils.ShelfStatus;
import com.sohu.sur.util.lottery.Constants;
import com.sohu.sur.util.lottery.GiftQueryCriteria;

/**
 * @Title: LotteryGiftService.java
 * @Package com.sohu.sur.service
 * @Description: TODO
 * @author leiyangbj6779
 * @date 2011-12-5 下午2:54:29
 * @version V1.0
 */
@Service
public class GiftService {
	private Logger logger = LoggerFactory.getLogger(GiftService.class);
	@Autowired
	private SucAbstractGiftDao parentGiftDao;
	@Autowired
	private SucExchangeGiftDao exchangeGiftDao;
	@Autowired
	private SucLotteryGiftDao lotteryGiftDao;

	public List<SucAbstractGift> getGiftList(String querySql, Object[] params, Pagination pagination) {
		return parentGiftDao.getObjectList(querySql, pagination, params);
	}

	public void createGift(SucExchangeGift gift) throws SucGiftException {

		try {
			exchangeGiftDao.saveOrUpdate(gift);

			// 更新礼品活动
			updateGiftActivity();

		} catch (Exception e) {
			logger.error("createGift", e);
			throw new SucGiftException(e);
		}

	}

	public List<SucExchangeGift> getExchangeGiftList(String querySql, Object[] params, Pagination pagination) {
		return exchangeGiftDao.getObjectList(querySql, pagination, params);
	}

	public List<SucLotteryGift> getLotteryGiftList(String querySql, Object[] params, Pagination pagination) {
		return lotteryGiftDao.getObjectList(querySql, pagination, params);
	}

	public void createLotteryGift(SucLotteryGift gift) throws SucLotteryException {
		try {
			this.lotteryGiftDao.saveOrUpdate(gift);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new SucLotteryException(e);
		}
	}

	public SucExchangeGift getExchangeGift(Long id) {
		SucExchangeGift exchangeGift = null;
		try {
			exchangeGift = exchangeGiftDao.get(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return exchangeGift;
	}

	public SucLotteryGift fetchLotteryGift(Long id) {
		SucLotteryGift lotteryGift = null;
		try {
			lotteryGift = lotteryGiftDao.get(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return lotteryGift;
	}

	/**
	 * 获取指定的礼品对象
	 * 
	 * @see com.sohu.suc.gift.service.GiftService#getGift(java.lang.Long,
	 *      java.lang.Long)
	 */
	public SucAbstractGift getGift(Long id) {
		SucAbstractGift gift = null;
		try {
			gift = parentGiftDao.get(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return gift;
	}

	/**
	 * 礼品上架和下架的操作
	 * 
	 * @see com.sohu.suc.gift.service.GiftService#deploy(java.lang.Long,
	 *      java.lang.Long, com.sohu.suc.gift.util.GiftEnumUtils.ShelfStatus)
	 */
	public void deploy(Long id, ShelfStatus shelfStatus) throws SucGiftException {
		try {
			String updateStr = "update SucAbstractGift set isShelf = ?, shelfTime = ?  where id = ?";
			Object[] params = { shelfStatus.getValue(), new Date(), id };
			parentGiftDao.updateByParams(updateStr, params);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new SucGiftException(e);
		}
	}

	public void haveOrLack(Long id, LackStatus lackStatus) throws SucGiftException {
		try {
			String updateStr = "update SucAbstractGift set lackStatus = ? where regionId = 1 and id = ?";
			Object[] params = { lackStatus.ordinal(), id };
			parentGiftDao.updateByParams(updateStr, params);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new SucGiftException(e);
		}
	}

	/**
	 * 保存编辑后的礼品对象
	 * 
	 * @see com.sohu.suc.gift.service.GiftService#updateExchangeGift(com.sohu.suc.gift.model.SucGift)
	 */
	public void updateExchangeGift(SucExchangeGift gift) throws SucGiftException {
		try {

			exchangeGiftDao.saveOrUpdate(gift);
			updateGiftActivity();// 更新礼品活动
			// RsyncCmd.rsycCmd(); //TODO 需要明白是做什么的

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new SucGiftException(e);
		}
	}

	/**
	 * (后台)管理员礼品管理-保存编辑后的抽奖礼品对象
	 * 
	 * @see com.sohu.suc.gift.service.LotteryService#updateLotteryGift(com.sohu.suc.gift.model.SucLotteryGift)
	 */
	public void updateLotteryGift(SucLotteryGift gift) throws SucLotteryException {
		try {
			lotteryGiftDao.saveOrUpdate(gift);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new SucLotteryException(e);
		}
	}

	public void updateAbstractGift(SucAbstractGift gift) throws SucLotteryException {
		try {
			parentGiftDao.saveOrUpdate(gift);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new SucLotteryException(e);
		}
	}

	/**
	 * 是否已经存在存在主推商品
	 * 
	 * @see com.sohu.suc.gift.service.GiftService#hasRecommendGift(com.sohu.suc.gift.model.SucGift)
	 */
	public boolean hasRecommendGift(SucExchangeGift gift) {

		try {
			if (gift.getIsRecommend() == null || gift.getIsRecommend().intValue() == EnumUtils.NO) {
				return false;
			}

			StringBuilder queryString = new StringBuilder(
					"select count(id) from SucExchangeGift where regionId = 1 and isRecommend = ? ");
			List<Object> params = new ArrayList<Object>();
			params.add(EnumUtils.YES);

			if (gift.getId() != null && gift.getIsRecommend().intValue() == EnumUtils.YES) {
				queryString.append(" and id != ?");
				params.add(gift.getId());
			}
			List<SucExchangeGift> results = exchangeGiftDao.getListByParams(queryString.toString(), params.toArray());

			if (results != null && results.size() > 0) {
				return true;
			}
			return false;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 是否已经存在存在主推抽奖商品
	 * 
	 * @see com.sohu.suc.gift.service.GiftService#hasRecommendGift(com.sohu.suc.gift.model.SucGift)
	 */
	public boolean hasLotteryRecommendGift(SucLotteryGift gift) {
		try {
			if (gift.getIsRecommend() == null || gift.getIsRecommend().intValue() == EnumUtils.NO) {
				return false;
			}
			StringBuilder queryString = new StringBuilder(
					"select count(id) from SucLotteryGift where regionId = 1 and isRecommend = ? ");
			List<Object> params = new ArrayList<Object>();
			params.add(EnumUtils.YES);
			if (gift.getId() != null && gift.getIsRecommend().intValue() == EnumUtils.YES) {
				queryString.append(" and id != ?");
				params.add(gift.getId());
			}
			List<SucLotteryGift> results = lotteryGiftDao.getListByParams(queryString.toString(), params.toArray());
			if (results != null && results.size() > 0) {
				return true;
			}
			return false;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 在新增礼品和更新礼品时，遍历礼品的活动，将已经过期的“限时”的活动置为正常的状态
	 */
	private void updateGiftActivity() {
		try {
			String updateStr = "update SucExchangeActivity set type = ? where (type = ? or type = ?) and endTime < ? and regionId = 1";
			Object[] params = { GiftEnumUtils.NO_LIMIT, GiftEnumUtils.LIMIT_TIME_COUNT,
					GiftEnumUtils.LIMIT_TIME_COUNT_PRICE, new Date() };
			this.exchangeGiftDao.updateByParams(updateStr, params);

		} catch (Exception e) {
			// 此处的异常可以不用抛出，不能因为更新礼品的活动失败而影响了正常的礼品操作
			logger.error(e.getMessage(), e);
		}
	}

	public ActivityStatus getGiftActivityType(SucExchangeActivity giftActivity) {

		ActivityStatus activityStatus = null;

		try {
			if (giftActivity.getLimitPrice() != null && giftActivity.getLimitPrice().intValue() != 0) {
				if (giftActivity.getLimitCount() != null && giftActivity.getEndTime() != null) {
					// 限时限量限价
					activityStatus = ActivityStatus.LimitTimeCountPrice;

				} else if (giftActivity.getLimitCount() != null && giftActivity.getLimitCount() != 0
						&& giftActivity.getEndTime() == null) {
					// 限量限价
					activityStatus = ActivityStatus.LimitCountPrice;

				} else if ((giftActivity.getLimitCount() == null || giftActivity.getLimitCount() == 0)
						&& giftActivity.getEndTime() != null) {
					// 限时限价
					activityStatus = ActivityStatus.LimitTimeCount;

				} else if (giftActivity.getLimitCount() == null) {
					// 限价
					activityStatus = ActivityStatus.LimitPrice;
				} else {
					activityStatus = ActivityStatus.NoLimit;
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return activityStatus;
	}

	/**
	 * 前台 抽奖
	 * 
	 * @param regionId
	 *            域id 默认为1
	 * @param pagination
	 * @return
	 */
	public List<SucLotteryGift> lottery_recommend(Long regionId, Pagination pagination) {
		List<SucLotteryGift> result = null;
		try {
			// 过滤掉待重新上架状态
			String queryString = "from SucLotteryGift where regionId = ? and isShelf =?  and priority>=80 and priority<90 ORDER BY compositorId asc, operateTime DESC ";
			Object[] params = { regionId, GiftEnumUtils.SHELFED };
			result = lotteryGiftDao.getObjectList(queryString, pagination, params);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}
	/**
	 * 前台 兑换
	 * 
	 * @param regionId
	 *            域id 默认为1
	 * @param pagination
	 * @return
	 */
	public List<SucExchangeGift> exchange_recommend(Long regionId, Pagination pagination) {
		List<SucExchangeGift> result = null;
		try {
			// 过滤掉待重新上架状态
			String queryString = "from SucExchangeGift where regionId = ? and isShelf =? and priority>=80 and priority<90 ORDER BY compositorId asc, operateTime DESC ";
			Object[] params = { regionId, GiftEnumUtils.SHELFED };
			result = exchangeGiftDao.getObjectList(queryString, pagination, params);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}

	
	/**
	 * 虚拟商品 前台
	 * @param virtualGiftType
	 * @param pageNo
	 * @return
	 */
	public PaginationSupport virtual(int virtualGiftType, int pageNo) {
		int pageSize = 16;
		int begin = (pageNo - 1) * pageSize;
		String queryString = "";
		PaginationSupport ps = null;
		try {
			//全部
			if(virtualGiftType==0){
				queryString = "select s from SucAbstractGift s where s.regionId = 1 and s.type = 1 and s.gift_type = 2   and s.isShelf =? order by   s.operateTime desc ";
				Object[] params = {GiftEnumUtils.SHELFED };
				int rowCount = parentGiftDao.getTotalCount(queryString, params);
			    List list = parentGiftDao.getListByPosition(begin, pageSize, queryString, params);
			    ps = new PaginationSupport(list, rowCount, begin, pageSize);
			}
			else {
				queryString = "select s from SucAbstractGift s where s.regionId = 1 and s.type = 1  and s.gift_type = 2  and s.virtual_gift_type = ? and s.isShelf =?  order by  s.operateTime desc ";
				Object[] params = { virtualGiftType, GiftEnumUtils.SHELFED };
				int rowCount = parentGiftDao.getTotalCount(queryString, params);
			    List list = parentGiftDao.getListByPosition(begin, pageSize, queryString, params);
			    ps = new PaginationSupport(list, rowCount, begin, pageSize);
			}
		       
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return ps;
	}
	
	
	
	/**
	 * 虚拟商品金币中心虚拟商品推荐接口
	 * @param virtualGiftType
	 * @param pageNo
	 * @return
	 */
	public List virtualRecommand() {
		List list = null;
		try {
			String queryString  = "select s.id,s.logo,s.name,s.marketValue,s.virtual_gift_type,s.stockCount from SucAbstractGift s where s.gift_type = 2  and s.isShelf =? and s.priority = 70  order by  s.compositorId asc ";
				Object[] params = { GiftEnumUtils.SHELFED };
			    list = parentGiftDao.getAll(queryString, params);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}
	
	/**
	 * 兑换商品 兑换数
	 * 
	 * @param productid
	 * @return
	 */
	public String exchange_num(Long productid) {
		String ret = "";
		List details = null;
		try {
			String queryString = "SELECT count(*) FROM suc_gift_detail d WHERE d.gift_Id = " + productid;
			details = exchangeGiftDao.executeList(queryString);
			for (Object obj : details) {
				ret = obj.toString();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return ret;
	}
	
	 public List<SucAbstractGift> getGiftList(String order, GiftQueryCriteria queryCriteria, Pagination pagination) {
	        List<SucAbstractGift> result = new ArrayList<SucAbstractGift>();
	        try {
	            StringBuffer queryString = new StringBuffer("from SucAbstractGift as g ")
	                    .append("left outer join g.lotteryActivity as la ").append("left outer join g.giftActivity as ga ")
	                    .append("where g.regionId=?");
	            queryString.append(" and g.isShelf=?");
	            queryString.append(queryCriteria.getQueryString());
	            queryString.append(order);
	            List tmp = parentGiftDao.getObjectList(queryString.toString(), pagination,
	                    queryCriteria.getQueryParams(Constants.regionId, GiftEnumUtils.SHELFED));
	            for (Object obj : tmp) {
	                if (obj instanceof Object[]) {
	                    Object[] objects = ((Object[]) obj);
	                    if (objects != null && objects.length > 0) {
	                        if (objects[0] instanceof SucAbstractGift) {
	                            result.add((SucAbstractGift) objects[0]);
	                        }
	                    }
	                }
	            }
	        } catch (Exception e) {
	        	logger.error(e.getMessage(), e);
	        }
	        return result;
	    }
	 
    /**
     * 热门兑换
     * 
     */
    public List<SucExchangeGift> getRecommendExchangeGiftList(Pagination pagination) {

        List result = null;

        try {
            String queryString = "from SucExchangeGift where isShelf =? and priority>=80 and priority<90 ORDER BY compositorId asc, operateTime DESC ";
            Object[] params = {GiftEnumUtils.SHELFED };
            result = exchangeGiftDao.getObjectList(queryString, pagination, params);
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        }

        return result;
    }
    
    /**
     * 热门抽奖
     * 
     */
    public List<SucLotteryGift> getRecommendLotteryGiftList(Pagination pagination) {
        List result = null;
        try {
            String queryString = "from SucLotteryGift where isShelf =?  and priority>=80 and priority<90 ORDER BY compositorId asc, operateTime DESC ";
            Object[] params = {GiftEnumUtils.SHELFED};
            result = this.lotteryGiftDao.getObjectList(queryString, pagination, params);
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        }    
        return result;
    }
  

}
