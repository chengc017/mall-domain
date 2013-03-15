package com.sohu.sur.util.lottery;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sohu.sur.service.LotteryService;
import com.sohu.sur.util.cache.CacheService;


/**
 * 礼品抽奖工具类
 * 
 * 
 * 针对抽奖礼品活动的方案一（逢多少用户中奖）
 * 缓存抽奖礼品的当前已经有多少用户参加了抽奖活动
 * 其中key为域id+"_"+礼品id + "_" + 礼品抽奖活动id , value为当前抽奖礼品的用户参与数
 * 
 * 该缓存向数据库中更新的策略是异步的将当前的用户数向数据库中更新一次
 * 在每次重新启动系统的时候会重新将数据库中的数据加载到该缓存中
 * 
 * @author xiayanming
 *
 */
public class LotteryUtils {
	
	//每隔多少个参与抽奖的用户将抽奖参与的人数更新到数据库中
	private static final long INTERVAL_COUNT = 50;
	
	private static final Log log = LogFactory.getLog(LotteryUtils.class);
    

    synchronized public static long incrementLotteryUserCountAndGet(LotteryService lotteryService,CacheService cache,String key)
	{
		long result = 0;
		if(cache.keyExists(key))
		{
			result = cache.incr(key);
			
			log.info("[key]:" + key +" , result:" + result);
			if(result % INTERVAL_COUNT == 0)
			{
				//异步的更新当前的参与的用户数
				updateLotteryUserCount(lotteryService,key, result);
			}
			
		}else
		{
			//如果不存在则从数据库中重新将其加载到该缓存中
			long currLotteryUserCount = getLotteryUserCount(lotteryService,key);	
			
			result = cache.addOrIncr(key, currLotteryUserCount ==0 ? 1:currLotteryUserCount + 1);			
		}		
		
		return result;
	}
		
	/**
	 * 单独初始化
	 * 
	 * @param key
	 */
	private static long getLotteryUserCount(LotteryService lotteryService,String key) 
	{
		long currLotteryUserCount = 0;
		
		if(!StringUtils.isEmpty(key))
		{
			String[] arr = StringUtils.split(key, Constants.CHAR_UNDERLINE);
			
			if(arr != null && arr.length == 3)
			{
				String regionId = arr[0];
				String giftId = arr[1];
				String lotteryActivityId = arr[2];
				
				currLotteryUserCount = getLotteryUserCountFormDB(lotteryService,Long.valueOf(regionId), Long.valueOf(giftId), Long.valueOf(lotteryActivityId));
			}
			
		}else
		{
			log.error("key is null !");
		}
		
		return currLotteryUserCount;
	}
	
	private static void updateLotteryUserCount(LotteryService lotteryService,String key, long currLotteryUserCount)
	{
		if(!StringUtils.isEmpty(key))
		{
			String[] arr = StringUtils.split(key, Constants.CHAR_UNDERLINE);
			
			if(arr != null && arr.length == 3)
			{
				String regionId = arr[0];
				String giftId = arr[1];
				String lotteryActivityId = arr[2];
				
				updateLotteryUserCountToDB(lotteryService,Long.valueOf(regionId), Long.valueOf(giftId), Long.valueOf(lotteryActivityId), currLotteryUserCount);	
			}			
		}		
	}
	
	/**
	 * 从数据库获取当前已经参加抽奖的用户数
	 * 
	 * @param regionId
	 * @param giftId
	 * @param lotteryActivityId
	 * @return
	 */
	private static long getLotteryUserCountFormDB(LotteryService lotteryService,Long regionId, Long giftId, Long lotteryActivityId)
	{
		long currLotteryUserCount = 0;
		
		try
		{
			currLotteryUserCount = lotteryService.getLotteryUserCount(regionId, giftId, lotteryActivityId);
			
		} catch (Exception e)
		{
			log.error(e.getMessage(),e);
		}
		
		return currLotteryUserCount;
	}
	
	/**
	 * 将当前已经参与抽奖的用户数保存到数据库中
	 * 
	 * @param regionId
	 * @param giftId
	 * @param lotteryActivityId
	 * @param currLotteryUserCount
	 */
	private static void updateLotteryUserCountToDB(final LotteryService lotteryService, final Long regionId,
			final Long giftId, final Long lotteryActivityId, final long currLotteryUserCount) {

		try {
			lotteryService.updateLotteryUserCount(regionId, giftId, lotteryActivityId, currLotteryUserCount);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

	}
	

	/**
	 * 产生min到max范围内的随机数
	 * 
	 * @param min		最小值
	 * @param max		最大值
	 * @return
	 */
	public static long getRandom(long min, long max)
	{		
		return Math.round(min + (max - min)*Math.random());
	}
}
