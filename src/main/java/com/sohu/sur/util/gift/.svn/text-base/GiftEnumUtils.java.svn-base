package com.sohu.sur.util.gift;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 枚举类型工具类
 *
 * @author xiayanming
 */
public class GiftEnumUtils {


    /**
     * ***********************************************************
     * <p/>
     * ”上架“和”下架“常量及相关方法
     * <p/>
     * ************************************************************
     */

    public static final Integer NO_SHELF = ShelfStatus.NoShelf.ordinal();//未上架
    public static final Integer SHELFED = ShelfStatus.Shelfed.ordinal();//已上架
    public static final Integer RESHELF = ShelfStatus.ReShelf.ordinal();//待重新上架
    public static final Integer OFF_SHELF = ShelfStatus.OffShelf.ordinal();    //已下架
    
    /**首页展示权重枚举值*/
	public enum GiftWeight {
		TOP_RECOMMEND(100), // 置顶的推荐
		ALL_RECOMMEND(90), // 首页热点推荐
		LOTTERY_OR_GIFT_RECOMMEND(80),// 首页抽奖或者兑换推荐
		VIRTUAL_GIFT_RECOMMEND(70);// 金币可赠送礼物推荐虚拟礼物

		private Integer weight;

		private GiftWeight(Integer weight) {
			this.weight = weight;
		}

		public Integer getWeight() {
			return weight;
		}
	}
	
	

    public enum ShelfStatus {
        //0：未上架, 1：已上架, 2：待重新上架, 3:已下架

        NoShelf {
            public String getName() {
                return "未上架";
            }

            public int getValue() {
                return NoShelf.ordinal();
            }
        },
        Shelfed {
            public String getName() {
                return "已上架";
            }

            public int getValue() {
                return Shelfed.ordinal();
            }

        },
        ReShelf {
            public String getName() {
                return "待重新上架";
            }

            public int getValue() {
                return ReShelf.ordinal();
            }

        },
        OffShelf {
            public String getName() {
                return "已下架";
            }

            public int getValue() {
                return OffShelf.ordinal();
            }
        };

        //状态名称

        public abstract String getName();

        //状态值

        public abstract int getValue();
    }

    /**
     * 根据上架状态值获取上架状态信息
     *
     * @return
     */
    public static ShelfStatus getShelfStatus(int shelfStatusValue) {
        ShelfStatus[] shelfStatuss = ShelfStatus.values();

        if (shelfStatusValue > shelfStatuss.length)
            return ShelfStatus.NoShelf;


        for (ShelfStatus shelfStatus : shelfStatuss) {
            if (shelfStatus.getValue() == shelfStatusValue) {
                return shelfStatus;
            }
        }

        return ShelfStatus.NoShelf;
    }

    //主要作在下拉列表框中使用
    public static Map shelfStatusMap = null;

    public static synchronized Map getShelfStatusMap() {

        if (shelfStatusMap == null) {
            shelfStatusMap = new LinkedHashMap();

            shelfStatusMap.put(null, "全部");

            ShelfStatus[] shelfStatuss = ShelfStatus.values();
            for (ShelfStatus shelfStatus : shelfStatuss) {
                shelfStatusMap.put(shelfStatus.getValue(), shelfStatus.getName());
            }
        }

        return shelfStatusMap;
    }

    /**
     * ***********************************************************
     * <p/>
     * ”现货“和”缺货“常量及相关方法
     * <p/>
     * ************************************************************
     */
    public static final Integer LACK = LackStatus.缺货.ordinal();
    public static final Integer HAVE = LackStatus.现货.ordinal();


    public enum LackStatus {
        //0：缺货、1：现货
        缺货, 现货
    }

    public static Map lackStatusMap = null;

    public static synchronized Map getLackStatusMap() {
        if (lackStatusMap == null) {
            lackStatusMap = new LinkedHashMap();

            lackStatusMap.put(null, "全部");
            lackStatusMap.put(LACK, LackStatus.缺货.name());
            lackStatusMap.put(HAVE, LackStatus.现货.name());
        }

        return lackStatusMap;
    }

    /**
     * ***********************************************************
     * <p/>
     * ”实物“和”虚拟“常量及相关方法
     * <p/>
     * ************************************************************
     */
    public static final Integer MATERIAL = GiftPhysicsType.实物.ordinal();
    public static final Integer QUAN = GiftPhysicsType.优惠券.ordinal();
    public static final Integer VIRTUAL = GiftPhysicsType.虚拟.ordinal();


    public enum GiftPhysicsType {
        实物, 优惠券,虚拟
    }

    public static Map giftPhysicsTypeMap = null;

    public static synchronized Map getGiftPhysicsTypeMap() {
        if (giftPhysicsTypeMap == null) {
            giftPhysicsTypeMap = new LinkedHashMap();
            giftPhysicsTypeMap.put(null, "全部");
            giftPhysicsTypeMap.put(MATERIAL, GiftPhysicsType.实物.name());
            giftPhysicsTypeMap.put(QUAN, GiftPhysicsType.优惠券.name());
            giftPhysicsTypeMap.put(VIRTUAL, GiftPhysicsType.虚拟.name());
        }

        return giftPhysicsTypeMap;
    }


    /**
     * ***********************************************************
     * <p/>
     * "礼品活动"常量及相关方法(限价、限量、限时、限价限时、限量限时)
     * <p/>
     * ************************************************************
     */

    public static final Integer NO_LIMIT = ActivityStatus.NoLimit.getValue();//无
    public static final Integer LIMIT_PRICE = ActivityStatus.LimitPrice.getValue();//限价
    public static final Integer LIMIT_COUNT_PRICE = ActivityStatus.LimitCountPrice.getValue();//限量限价
    public static final Integer LIMIT_TIME_COUNT = ActivityStatus.LimitTimeCount.getValue();//限时限价
    public static final Integer LIMIT_TIME_COUNT_PRICE = ActivityStatus.LimitTimeCountPrice.getValue();//限时限量限价


    public enum ActivityStatus {

        //0.不限, 1.限价, 2.限量限价, 3.限时限价, 4.限时限量限价
        NoLimit {
            public String getName() {
                return "无";
            }

            public int getValue() {
                return NoLimit.ordinal();
            }
        },
        LimitPrice {
            public String getName() {
                return "特价";
            }

            public int getValue() {
                return LimitPrice.ordinal();
            }
        },
        LimitCountPrice {
            public String getName() {
                return "限量特价";
            }

            public int getValue() {
                return LimitCountPrice.ordinal();
            }
        },
        LimitTimeCount {
            public String getName() {
                return "限时特价";
            }

            public int getValue() {
                return LimitTimeCount.ordinal();
            }
        },
        LimitTimeCountPrice {
            public String getName() {
                return "限时限量特价";
            }

            public int getValue() {
                return LimitTimeCountPrice.ordinal();
            }
        };

        //状态名称

        public abstract String getName();

        //状态值

        public abstract int getValue();
    }

    /**
     * 根据礼品活动的类型值获取其名称
     *
     * @param activityStatusValue
     * @return
     */
    public static ActivityStatus getActivityStatus(int activityStatusValue) {
        ActivityStatus[] activityStatuss = ActivityStatus.values();

        if (activityStatusValue > activityStatuss.length)
            return ActivityStatus.NoLimit;


        for (ActivityStatus activityStatus : activityStatuss) {
            if (activityStatus.getValue() == activityStatusValue) {
                return activityStatus;
            }
        }

        return ActivityStatus.NoLimit;
    }

    /**
     * ***********************************************************
     * <p/>
     * "礼品状态"常量及相关方法(0：冻结、1：解冻)
     * <p/>
     * ************************************************************
     */
    public enum FreezeStatus {
        //0：冻结、1：解冻
        freezed, unfreeze
    }

    /**
     * ***********************************************************
     * <p/>
     * "订单状态"常量及相关方法
     * <p/>
     * 状态
     * 0.	待处理
     * 1.	已确认正在备货
     * 2.	已经发货
     * 3.	已完成
     * 4.	已作废
     * <p/>
     * 相应的动作有：确认、确认并货、发货、完成
     * <p/>
     * ************************************************************
     */
    public static final Integer PENDING = GiftDetailStatus.Pending.getValue();//待处理
    public static final Integer CONFIRMED = GiftDetailStatus.Confirmed.getValue();//已确认正在备货
    public static final Integer SEND = GiftDetailStatus.Send.getValue();//已经发货
    public static final Integer FINISHED = GiftDetailStatus.Finished.getValue();//已完成
    public static final Integer CANCEL = GiftDetailStatus.Cancel.getValue();//已取消
    public static final Integer INVALIDATED = GiftDetailStatus.Invalidated.getValue();//已作废

    public enum GiftDetailStatus {
        //0.待处理, 1.已确认正在备货, 2.已经发货, 3.已完成, 4.已取消, 5.已作废

        //对应到-->确认
        Pending {
            public String getName() {
                return "待处理";
            }

            public int getValue() {
                return Pending.ordinal();
            }
        },
        //对应到-->发货
        Confirmed {
            public String getName() {
                return "已确认正在备货";
            }

            public int getValue() {
                return Confirmed.ordinal();
            }
        },
        //对应到-->完成
        //对应到-->确认并货
        Send {
            public String getName() {
                return "已经发货";
            }

            public int getValue() {
                return Send.ordinal();
            }
        },
        Finished {
            public String getName() {
                return "已完成";
            }

            public int getValue() {
                return Finished.ordinal();
            }
        },
        Cancel {
            public String getName() {
                return "已取消";
            }

            public int getValue() {
                return Cancel.ordinal();
            }
        },
        Invalidated {
            public String getName() {
                return "已作废";
            }

            public int getValue() {
                return Invalidated.ordinal();
            }
        };

        //状态名称

        public abstract String getName();

        //状态值

        public abstract int getValue();
    }

    /**
     * 根据礼品订单状态值获取礼品订单状态
     *
     * @param giftDetailStatusValue 礼品订单状态值
     * @return
     */
    public static GiftDetailStatus getGiftDetailStatus(int giftDetailStatusValue) {

        GiftDetailStatus[] giftDetailStatuss = GiftDetailStatus.values();

        if (giftDetailStatusValue > giftDetailStatuss.length)
            return GiftDetailStatus.Pending;


        for (GiftDetailStatus giftDetailStatus : giftDetailStatuss) {
            if (giftDetailStatus.getValue() == giftDetailStatusValue) {
                return giftDetailStatus;
            }
        }

        return GiftDetailStatus.Pending;
    }

    public static Map giftDetailStatusMap = null;

    public static synchronized Map getGiftDetailStatusMap() {
        if (giftDetailStatusMap == null) {
            giftDetailStatusMap = new LinkedHashMap();

            giftDetailStatusMap.put(null, "全部");

            GiftDetailStatus[] giftDetailStatuss = GiftDetailStatus.values();
            for (GiftDetailStatus giftDetailStatus : giftDetailStatuss) {
                giftDetailStatusMap.put(giftDetailStatus.getValue(), giftDetailStatus.getName());
            }
        }

        return giftDetailStatusMap;
    }

    /**
     * ***********************************************************
     * <p/>
     * 用户兑换礼品时，是选择的“用户基本信息”还是填入的“其他的收入人信息”
     * <p/>
     * ************************************************************
     */
    public static final Integer ADDRESS_TYPE_USER = addressType.当前用户.ordinal();
    public static final Integer ADDRESS_TYPE_OTHER = addressType.其他收货人.ordinal();

    public enum addressType {
        当前用户, 其他收货人
    }

    /**
     * ***********************************************************
     * <p/>
     * 礼品类型，"兑换礼品"和"抽奖礼品"
     * 注意：该值一定要和SucAbstractGift.hbm.xml中的subclass项的discriminator-value值保存一致
     * <p/>
     * 礼品订单类型，"兑换订单"和"抽奖订单"
     * 注意：该值一定要和SucAbstractGiftDetail.hbm.xml中的subclass项的discriminator-value值保存一致
     * <p/>
     * ************************************************************
     */
    public static final Integer GIFT_TYPE_EXCHANGE = GiftType.兑换.ordinal() + 1;
    public static final Integer GIFT_TYPE_LOTTERY = GiftType.抽奖.ordinal() + 1;

    public enum GiftType {
        //1:兑换, 2: 抽奖
        兑换, 抽奖
    }

    private static Map<String, String> giftTypeMap = null;

    public static synchronized Map getGiftTypeMap() {
        if (giftTypeMap == null) {
            giftTypeMap = new LinkedHashMap<String, String>();

            giftTypeMap.put(null, "全部");
            giftTypeMap.put(String.valueOf(GIFT_TYPE_EXCHANGE), GiftType.兑换.name());
            giftTypeMap.put(String.valueOf(GIFT_TYPE_LOTTERY), GiftType.抽奖.name());
        }

        return giftTypeMap;
    }


    /**
     * ***********************************************************
     * <p/>
     * 抽奖方案，"方案一"和"方案二"
     * <p/>
     * 方案一：逢几中奖
     * 方案二：设置中奖号码和奖池范围（用户将从奖池中随机抽取号码和中奖号码比较，如果和中奖号码相同则为中奖，否则为不中奖）
     * <p/>
     * ************************************************************
     */

    public static final Integer LOTTERY_ACTIVITY_SCENARIO_1 = lotteryActivityScenario.方案一.ordinal() + 1;
    public static final Integer LOTTERY_ACTIVITY_SCENARIO_2 = lotteryActivityScenario.方案二.ordinal() + 1;

    public enum lotteryActivityScenario {
        //1: 方案一, 2: 方案二
        方案一, 方案二
    }

    /**
     * ***********************************************************
     * <p/>
     * 抽奖类型，"不限"和"每天"、"限时"
     * <p/>
     * 不限：不限制时间范围，只按照“抽奖方案”来抽奖，直到库存为0为止
     * 每天: 每天拿出一定数目的礼品来进行抽奖
     * 限时：如果该时间范围过期，则自动转为“不限”类型
     * <p/>
     * ************************************************************
     */

    public static final Integer LOTTERY_ACTIVITY_TYPE_NOLIMIT = lotteryActivityType.不限.ordinal();
    public static final Integer LOTTERY_ACTIVITY_TYPE_EVERYDAY = lotteryActivityType.每天.ordinal();
    public static final Integer LOTTERY_ACTIVITY_TYPE_LIMITTIME = lotteryActivityType.限时.ordinal();

    public enum lotteryActivityType {
        //0:不限, 1:每天, 2:限时
        不限, 每天, 限时
    }
}
