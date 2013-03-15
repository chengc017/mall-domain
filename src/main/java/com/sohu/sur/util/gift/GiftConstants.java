package com.sohu.sur.util.gift;

public class GiftConstants {

		
	/*************************************************
	 * 
	 * 				礼品抽奖扣分接口中的常量
	 * 
	 *************************************************/	
	 
	//礼品抽奖扣分，扣分批次id
	public static final String SCORE_LOTTERY_GIFT_RID = "score.lottery_gift.consume_rule.rid";
	
	//积分抽奖扣分操作交易号的前缀部分
	public static final String SCORE_LOTTERY_DEALID_PREFIX = "lottery_";
	
	public static final Integer DEFAULT_COMPOSITORID = 100;
	
	/**
	 * 奖品兑换过程的常量定义
	 */
	
	//抽奖成功后（中奖），将中奖信息放入key为lottery_mark的缓存中
	public static final String LOTTERY_MARK = "lottery_mark";
	
	//加密抽奖成功后的结果信息中的分割字符串
	public static final String LOTTERY_SPLIT_CHAR = "#=#";
	
	//礼品抽奖管理 Service
	public static final String SPRING_BEANNAME_LOTTERY_SERVICE = "lotteryService";
	
	
	/**
	 * 抽奖结果状态，在抽奖后根据该状态来显示提示消息
	 * 
	 */
	public static final String LOTTERY_STATUS_SUCCESS = "0";//中奖
	public static final String LOTTERY_STATUS_FAIL = "1";//没有中奖
	public static final String LOTTERY_STATUS_EXCEPTION = "-1";//异常
	//前台 首页默认显示个数
	public static final String DEFAULT_INDEX_SHOW_NUM = "8";
    public static final Long regionId = 1l;
}
