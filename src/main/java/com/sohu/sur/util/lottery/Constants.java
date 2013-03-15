package com.sohu.sur.util.lottery;

public class Constants {	
	
	public static final int InvalidCode = -3;
	public static final int InvalidReq = -1;
	public static final int LotteryLackMedal = 0;
	public static final int LotteryLackPoint = 1;
	public static final int LotteryMissHit = 2;
	public static final int LotterySuccess = 3;
	
	public static final int ExchangeLackMedal = 0;
    public static final int ExchangeLackPoint = 1;
    public static final int ExchangeMissHit = 2;
    public static final int ExchangeSuccess = 3;
	
	public static final int CANCELDEAL = -2;

	public static final Long regionId = new Long(1);
	
	//Cache
	public static final String DEAL_KEY = "deals";
	public static final String GIFTS_KEY = "gifts";
	
	public static final String SCORE_LOTTERY_DEALID_PREFIX = "lottery_";
	public static final String GIFT_DETAIL_EXCHANGE_TYPE = "1";
	public static final String GIFT_DETAIL_LOTTERY_TYPE = "2";
	
	public static final String DEAL_GIFT_TYPE = "1";
	public static final String DEAL_LOTTERY_TYPE = "2";
	
	   public static final String CHAR_UNDERLINE = "_";
}
