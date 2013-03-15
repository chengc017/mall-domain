package com.sohu.sur.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sohu.sur.base.exception.NoMedalsException;
import com.sohu.sur.base.exception.SucBuyGiftException;
import com.sohu.sur.base.exception.SucGiftException;
import com.sohu.sur.base.exception.SucScoreException;
import com.sohu.sur.dao.admin.SucExchangeGiftDao;
import com.sohu.sur.dao.order.MallOrderDao;
import com.sohu.sur.model.admin.MallOrder;
import com.sohu.sur.model.admin.SucExchangeActivity;
import com.sohu.sur.model.admin.SucExchangeGift;
import com.sohu.sur.util.ChangeBonusResult;
import com.sohu.sur.util.DateUtils;
import com.sohu.sur.util.EnumUtils;
import com.sohu.sur.util.Pagination;
import com.sohu.sur.util.exchange.ExchangeResult;
import com.sohu.sur.util.exchange.StringUtil;
import com.sohu.sur.util.gift.GiftEnumUtils;
import com.sohu.sur.util.gift.GiftEnumUtils.ActivityStatus;
import com.sohu.sur.util.gift.ScoreAndMedal;
import com.sohu.sur.util.lottery.Constants;
import com.sohu.sur.util.lottery.UseMedal;
import com.sohu.sur.util.lottery.UserScoreUtil;
import com.sohu.sur.util.order.ReceiverInfo;

@Service
public class ExchangeService {

    private Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private MallOrderDao mallOrderDao;

    @Autowired
    private SucExchangeGiftDao exchangeDao;

    /**
     * 用户兑换礼品的操作 将礼品兑换的详细信息保存到个人礼品明细表中 并进行积分的扣除操作 集合定单信息修改
     * 
     * @param String userIp 用户IP
     * @param Long giftId
     * @return PointForGiftsResult 兑换结果
     * @see com.sohu.suc.gift.service.GiftDetailService#createGiftDetail(Long)
     */
    public ExchangeResult createGiftDetail(ReceiverInfo receiverInfo, String userId,
            String userIp, SucExchangeGift gift) throws SucBuyGiftException, NoMedalsException, SucScoreException,
            SucGiftException {
        ExchangeResult lresult = new ExchangeResult();
        try {

            if (gift == null) {
                throw new SucBuyGiftException("操作失败，非法礼品信息！");
            }

            /**
             * 获取礼品的单价
             */
            int saleValue = gift.getShowValue();

            if (saleValue == 0) {
                throw new SucBuyGiftException("操作失败，非法礼品信息！礼品价格为 0！");
            }

            //检查当前用户的勋章是否满足 
            UseMedal useMedal = UserScoreUtil.useMedal(gift.getMedals(), userId, saleValue);
            if (null == useMedal) throw new NoMedalsException("0", "您没有所需勋章。");
            if (null != useMedal) {
                int medalId = 0;
                try {
                    medalId = Integer.parseInt(useMedal.getId());
                    switch (medalId) {
                        case -1:
                            throw new NoMedalsException("1", "您的金币不足。");
                        case -2:
                            throw new NoMedalsException("0", "您没有所需勋章。");
                        case -3:
                            throw new NoMedalsException("-1", "您的金币不足。");
                    }
                } catch (NumberFormatException nfe) {
                    //nfe.printStackTrace();
                }//
            }

            //获得对应折扣数
            float discount = useMedal.getDiscount(); //UserScoreUtil.getDiscount(useMedal);

            MallOrder order = initGiftDetail(gift, userId);
            lresult.setGiftdealid(order.getDealId());

            /**
             * 验证礼品的库存数量
             */
            validateGift(gift, order);

            /**
             * 验证兑换礼品的兑换活动 验证“限量限价”、”限时限量限价“时的现价量
             */
            validateGiftActivity(gift, order);

            /**
             * 重新获取礼品的价格，如果是活动礼品，需要取活动的价格
             */
            int subpoint = (int) (Math.floor(order.getCount() * saleValue * discount));
            order.setSaleValue(subpoint);

            /**
             * 兑换礼品时扣除积分, 检查积分是否足够?
             */
            String sdesc = new StringBuffer("{\"msg\":\"").append(gift.getName())
                    .append("\",\"url\":\"http://gift.sohu.com/gift/details/").append(gift.getId())
                    .append("\",\"logo\":\"").append(gift.getLogo()).append("\"}").toString();
            ChangeBonusResult cr = UserScoreUtil.adjustUserBonus(order.getUserId(), -1 * order.getSaleValue(), sdesc,
                    "gift");
            if (cr == null) throw new SucScoreException("扣除金币操作失败！");
            if (cr.getResult() == 8) throw new NoMedalsException("-1", "您的金币不足。");
            if (cr.getResult() != 0) throw new SucScoreException("扣除金币操作失败！");

            //将礼品名称保存到礼品兑换明细中
            order.setGiftName(gift.getName());
            order.setUserIp(userIp);

            order.setReceiveUserName(StringUtil.unescape(receiverInfo.getReceiveUserName()));
            order.setReceiveTel(receiverInfo.getReceiveTel());
            order.setReceivePostCode(receiverInfo.getReceivePostCode());
            order.setReceiveAddr(StringUtil.unescape(receiverInfo.getReceiveAddr()));
            order.setDescr(StringUtil.unescape(receiverInfo.getDescr()));

            /**
             * 保存礼品兑换明细（订单）
             */
            mallOrderDao.save(order);

            /**
             * 当消费成功后更新库存
             */
            updateGiftStock(gift, order);

            //success
            lresult.setStatus(3);
            lresult.setGiftdealid(order.getDealId());
            lresult.setSubpoint(subpoint);
            lresult.setRemain(cr.getBonus());
            // 0实物 1优惠券 2虚拟
            lresult.setCat(order.getGiftType().toString());

            //同步mongodb  
            	//非虚拟商品处理
			if (order.getGiftType() != 2) {
				UserScoreUtil.syncStoreDeals(order.getUserId(), order.getId().toString(), order.getType(), order
						.getCount().toString(), order.getSaleValue().toString(), order.getGiftId().toString(), order
						.getGiftName(), gift.getLogo(), order.getDealId(),order.getGiftType());
			}
            //虚拟商品处理
            else{
            	UserScoreUtil.syncStoreVirtualDeals(order.getUserId(), order.getId().toString(), order.getType(), order.getCount()
                        .toString(), order.getSaleValue().toString(), order.getGiftId().toString(), order.getGiftName(),
                        gift.getLogo(), order.getDealId(),saleValue,order.getGiftType());
            }

        } catch (NoMedalsException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            throw e;
        } catch (SucScoreException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            throw e;
        } catch (SucBuyGiftException e) {
        	int orderProductCount=1;
        	if(gift.getGift_type().intValue()==2){
        		orderProductCount=gift.getExchangeNum();
        	}
        	log.info("the stockCount is: "+gift.getStockCount()+"the order product count is: "+orderProductCount+"the userID:"+userId );
            log.error(e.getMessage(), e);
            throw e;

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new SucGiftException(e);
        } 

        return lresult;

    }

    /**
     * 用商品和用户部分信息初始化订单
     * 
     * @param sucGift
     * @param userId
     * @return
     */
    private MallOrder initGiftDetail(SucExchangeGift sucGift, String userId) {
        MallOrder detail = new MallOrder();
        detail.setGiftId(sucGift.getId());
        detail.setUserId(userId);
        detail.setDealId(DateUtils.getYearNo() + Constants.DEAL_GIFT_TYPE + System.currentTimeMillis());
        //除了虚拟礼物订单 其他数量都是1
        if(sucGift.getGift_type().intValue()!=2){
        	log.info("other gift count = 1");
        	detail.setCount(new Integer(1));
        }
        else{
        	detail.setCount(sucGift.getExchangeNum());
        	log.info("virtual gift count = "+sucGift.getExchangeNum());
        }
        detail.setType(Constants.GIFT_DETAIL_EXCHANGE_TYPE);
        detail.setCreateTime(new Date());
        detail.setRegionId(Constants.regionId);
        detail.setGiftType(sucGift.getGift_type());
        detail.setStatus((sucGift.getGift_type().equals(1)||sucGift.getGift_type().equals(2)) ? GiftEnumUtils.FINISHED : GiftEnumUtils.PENDING); //优惠券和虚拟物品订单生成，状态即为已完成
        return detail;
    }

    /**
     * 验证礼品的库存数量 验证礼品是否缺货（自动缺货和人为缺货） 验证礼品是否已经下架时 验证购买数量是否超过库存
     * 
     * 
     * @param gift 当前的礼品对象
     * @param giftDetail 礼品订单明细对象
     * @throws SucGiftException
     */
    public void validateGift(SucExchangeGift gift, MallOrder giftDetail) throws SucBuyGiftException {

        //用户填写的兑换数量是否正确
        if (giftDetail.getCount() == null || giftDetail.getCount() == 0) {
            throw new SucBuyGiftException("操作失败，必须要填写兑换的礼品的数量！");
        }

        //自动缺货，库存数量为0
        if (gift.getStockCount() == null || gift.getStockCount() == 0) {
            throw new SucBuyGiftException("操作失败，该礼品库存不足！");
        }

        //人为缺货，缺货状态为“缺货”
        if (GiftEnumUtils.HAVE.intValue() != gift.getLackStatus().intValue()) {
            throw new SucBuyGiftException("操作失败，该礼品已经缺货！");
        }

        //验证购买数量是否超过库存
        if (giftDetail.getCount().intValue() > gift.getStockCount().intValue()) {
            throw new SucBuyGiftException("操作失败，库存中只剩余" + gift.getStockCount() + "个！");
        }

        //“已上架”和“待重新上架”的允许购买; “未上架”或者“已下架”都不允许购买
        if (GiftEnumUtils.SHELFED.intValue() != gift.getIsShelf().intValue()
                && GiftEnumUtils.RESHELF.intValue() != gift.getIsShelf().intValue()) {
            throw new SucBuyGiftException("操作失败，该礼品已经下架！");
        }

    }

    /**
     * 验证兑换礼品的兑换活动
     * 
     * 验证“限量限价”和”限时限量限价“时的现价量 验证“限时限价”和”限时限量限价“时，限时是否过期
     * 
     * TODO 如果有过多的并发情况，则需要在打开填写个人信息页时也加入该验证
     * 
     * @param gift 兑换礼品对象
     * @param giftDetail 礼品订单明细对象
     * @throws SucBuyGiftException
     */
    private void validateGiftActivity(SucExchangeGift gift, MallOrder giftDetail) throws SucBuyGiftException {
        SucExchangeActivity activity = gift.getGiftActivity();

        if (activity != null && activity.getType() != null) {
            if (activity.getType().intValue() == ActivityStatus.LimitCountPrice.getValue()) {
                //限量限价
                if (activity.getLimitCount() == null || activity.getLimitCount() == 0) {
                    throw new SucBuyGiftException("该“限量”的礼品已兑换完，不能以优惠金币兑换，请重新选择礼品重新兑换！");
                }

                if (giftDetail.getCount().intValue() > activity.getLimitCount().intValue()) {
                    throw new SucBuyGiftException("操作失败，该“限量”的礼品的剩余量为" + activity.getLimitCount() + "个!");
                }

            } else if (activity.getType().intValue() == ActivityStatus.LimitTimeCount.getValue()) {
                //限时限价

                if (activity.getEndTime().before(new Date())) {
                    throw new SucBuyGiftException("该礼品的已经过了“限时”的有效期，已经不能以优惠金币兑换，请重新选择礼品重新兑换！");
                }
            } else if (activity.getType().intValue() == ActivityStatus.LimitTimeCountPrice.getValue()) {
                //限时限量限价

                if (activity.getLimitCount() == null || activity.getLimitCount() == 0) {
                    throw new SucBuyGiftException("该“限时”的礼品已兑换完，不能以优惠金币兑换，请重新选择礼品重新兑换！");
                }

                if (activity.getEndTime().before(new Date())) {
                    throw new SucBuyGiftException("该礼品的已经过了“限时”的有效期，已经不能以优惠金币兑换，请重新选择礼品重新兑换！");
                }

                if (giftDetail.getCount().intValue() > activity.getLimitCount().intValue()) {
                    throw new SucBuyGiftException("操作失败，该“限时”的礼品的剩余量为" + activity.getLimitCount() + "个!");
                }
            }

            //验证“每人次兑换个数”
            if (activity.getUserGiftCount() != null && activity.getUserGiftCount() != 0) {
                int userGiftCount = getUserGiftCount(giftDetail.getRegionId(), gift.getId(), giftDetail.getUserId());

                if (userGiftCount + giftDetail.getCount() > activity.getUserGiftCount()) {
                    throw new SucBuyGiftException("商品限制每个人只能兑换 " + activity.getUserGiftCount() + " 个，您已经兑换过此商品，无法再次兑换。");
                }
            }
        }
    }

    /**
     * 指定用户已经兑换过的该礼品的个数
     * 
     * @param regionId 域id
     * @param giftId 礼品id
     * @param userId 用户id
     * @return 用户已经兑换过的该礼品的个数
     */
    private int getUserGiftCount(Long regionId, Long giftId, String userId) {
        int userGiftCount = 0;

        //订单状态为有效的,即为非“已取消”和“已作废”的订单
        String queryString = "from MallOrder a where a.regionId = ? and a.giftId = ? and a.userId = ? and (a.status != ? or a.status != ?)";
        Object[] params = { regionId, giftId, userId, GiftEnumUtils.CANCEL, GiftEnumUtils.INVALIDATED };

        userGiftCount = mallOrderDao.getListByParams(queryString, params).size();

        return userGiftCount;
    }

    /**
     * 当消费成功后更新库存, 如果“限量限价”和”限时限量限价“，需更新限量的剩余数
     * 
     * @param gift 当前的礼品对象
     * @param giftDetail 礼品订单明细对象
     * @throws SucGiftException
     */
    private void updateGiftStock(SucExchangeGift gift, MallOrder giftDetail) throws SucGiftException {
        if (gift.getStockCount() - giftDetail.getCount() > 0) {
            gift.setStockCount(gift.getStockCount() - giftDetail.getCount());
        } else {
            gift.setStockCount(0);
            gift.setIsShelf(0);//下架
        }

        SucExchangeActivity activity = gift.getGiftActivity();
        if (activity != null && activity.getLimitCount() != null) {
            if (activity.getLimitCount() - giftDetail.getCount() > 0) {
                activity.setLimitCount(activity.getLimitCount() - giftDetail.getCount());
            } else {
                /*//当限量或限时限价限量中的限量为0时将活动的状态置为不限
                activity.setType(GiftEnumUtils.NO_LIMIT);*/
                activity.setLimitCount(0);
            }

            activity.setExchangeGift(gift);
        }

        exchangeDao.saveOrUpdate(gift);
    }

    /**
     * 获取用户购买某商品的单价
     * @param userPassport
     * @param gift
     * @return
     */
    public int getCurrentValue(String userPassport, SucExchangeGift gift) {
        UseMedal useMedal = UserScoreUtil.useMedal(gift.getMedals(), userPassport, gift.getShowValue());
        float discount = 1;
        if (null != useMedal) discount = useMedal.getDiscount();
        int subpoint = (int) (Math.floor(gift.getShowValue() * discount));
        return subpoint;
    }
    
    /**
     * 获取用户购买某商品的单价和最高勋章
     * @param userPassport
     * @param gift
     * @return
     */
    public ScoreAndMedal getCurrentValueAndMedal(String userPassport, SucExchangeGift gift) {
    	ScoreAndMedal sam = new ScoreAndMedal();
    	UseMedal useMedal = UserScoreUtil.useMedal2(gift.getMedals(), userPassport, gift.getShowValue());
        float discount = 1;
        String medalName = "无";
        if (null != useMedal) {
        	discount = useMedal.getDiscount();
        	sam.setUseMedal(useMedal);
        	if (Float.compare(discount, 1f)==0) {
    			medalName = "无";
    		} else if (Float.compare(discount, 0.95f)==0) {
    			medalName = "铜质勋章";
    		} else if (Float.compare(discount, 0.90f)==0) {
    			medalName = "银质勋章";
    		} else if (Float.compare(discount, 0.85f)==0) {
    			medalName = "金质勋章";
    		} else if (Float.compare(discount, 0.8f)==0) {
    			medalName = "钻石勋章";
    		}
        	sam.setMedalName(medalName);
        }
        int subpoint = (int) (Math.floor(gift.getShowValue() * discount));
        sam.setScore(subpoint);
        return sam;
    }
    /**
     * 金币首页 兑换列表
     * 
     * @see com.sohu.suc.gift.service.GiftService#getGiftList(java.lang.Long,
     *      com.sohu.suc.util.Pagination)
     */
    public List<SucExchangeGift> getExchangeList(Long regionId, Pagination pagination) {

        List result = null;
        try {
            String queryString = "from SucExchangeGift where regionId = ? and isShelf =? and isRecommend = ? order by operateTime desc";
            Object[] params = { regionId, GiftEnumUtils.SHELFED, EnumUtils.NO };
            result = this.exchangeDao.getObjectList(queryString, pagination, params);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }
    
    
    /**
     * 虚拟商品批量购买
     * @param userId
     * @param num 购买数量
     * @param userIp
     * @param gift
     * @return
     * @throws SucBuyGiftException
     * @throws NoMedalsException
     * @throws SucScoreException
     * @throws SucGiftException
     */
    public ExchangeResult createPatchGiftDetail(String userId,int num,
            String userIp, SucExchangeGift gift) throws SucBuyGiftException, NoMedalsException, SucScoreException,SucGiftException {
		ExchangeResult lresult = new ExchangeResult();
		try {
			if (gift == null) {
				throw new SucBuyGiftException("操作失败，非法礼品信息！");
			}
			/**
			 * 获取礼品的单价
			 */
			int saleValue = gift.getShowValue();
			if (saleValue == 0) {
				throw new SucBuyGiftException("操作失败，非法礼品信息！礼品价格为 0！");
			} 

			// 检查当前用户的勋章是否满足
			UseMedal useMedal = UserScoreUtil.useMedal(gift.getMedals(), userId, saleValue*num);
			if (null == useMedal)
				throw new NoMedalsException("0", "您没有所需勋章。");
			if (null != useMedal) {
				int medalId = 0;
				String medalid = useMedal.getId();
				try {
					medalId = Integer.parseInt(medalid.substring(medalid.indexOf("_")+1, medalid.length()));
					switch (medalId) {
					case -1:
						throw new NoMedalsException("1", "您的金币不足。");
					case -2:
						throw new NoMedalsException("0", "您没有所需勋章。");
					case -3:
						throw new NoMedalsException("-1", "您的金币不足。");
					}
				} catch (NumberFormatException nfe) {
					log.error("", nfe);
				}
			}
			// 获得对应折扣数
			float discount = useMedal.getDiscount();

			MallOrder order = initGiftDetail(gift, userId);
			lresult.setGiftdealid(order.getDealId());

			/**
			 * 验证礼品的库存数量
			 */
			validateGift(gift, order);

			/**
			 * 验证兑换礼品的兑换活动 验证“限量限价”、”限时限量限价“时的现价量
			 */
			validateGiftActivity(gift, order);

			/**
			 * 需要支付金币数
			 */
			int subpoint = (int) (Math.floor(saleValue * discount))*order.getCount();
			order.setSaleValue(subpoint);

			/**
			 * 兑换礼品时扣除积分, 检查积分是否足够?
			 */
			String sdesc = new StringBuffer("{\"msg\":\"").append(gift.getName())
					.append("\",\"url\":\"http://gift.sohu.com/gift/details/").append(gift.getId())
					.append("\",\"logo\":\"").append(gift.getLogo()).append("\"}").toString();
			ChangeBonusResult cr = UserScoreUtil.adjustUserBonus(order.getUserId(), -1 * order.getSaleValue(), sdesc,
					"gift");
			if (cr == null)
				throw new SucScoreException("扣除金币操作失败！");
			if (cr.getResult() == 8)
				throw new NoMedalsException("-1", "您的金币不足。");
			if (cr.getResult() == 13)
				throw new NoMedalsException("-1", "超过该动作一次变更上限");
			if (cr.getResult() != 0)
				throw new SucScoreException("扣除金币操作失败！");

			// 将礼品名称保存到礼品兑换明细中
			order.setGiftName(gift.getName());
			order.setUserIp(userIp);
			/**
			 * 保存礼品兑换明细（订单）
			 */
			mallOrderDao.save(order);

			/**
			 * 当消费成功后更新库存
			 */
			updateGiftStock(gift, order);

			// success
			lresult.setStatus(3);
			lresult.setGiftdealid(order.getDealId());
			lresult.setSubpoint(subpoint);
			lresult.setRemain(cr.getBonus());
			// 0实物 1优惠券 2虚拟
			lresult.setCat(order.getGiftType().toString());

			// 虚拟商品同步到mongodb处理
			UserScoreUtil.syncStoreVirtualDeals(order.getUserId(), order.getId().toString(), order.getType(), order
					.getCount().toString(), order.getSaleValue().toString(), order.getGiftId().toString(), order
					.getGiftName(), gift.getLogo(), order.getDealId(), gift.getShowValue(), order.getGiftType());

		} catch (NoMedalsException e) {
			log.error("NoMedalsException", e);
			throw e;
		} catch (SucScoreException e) {
			log.error("SucScoreException", e);
			throw e;
		} catch (SucBuyGiftException e) {
			log.error("SucBuyGiftException", e);
			throw e;

		} catch (Exception e) {
			log.error("", e);
			throw new SucGiftException(e);
		}
		return lresult;
	}

}
