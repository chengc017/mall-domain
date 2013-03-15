package com.sohu.sur.feed.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * 商城调用FEED的发送类
 * 
 * @author leiyangbj6779 2011-11-7
 * 
 */
public class FeedMsg {

	private Log logger = LogFactory.getLog(getClass());

	private short projectType = 39; // 金币商城固定代码

	private short version = 1;// 1.0版本

	private String passport;// 用户的passport或uid

	private String wareName; // 商品名称

	private String picPath;// 商品小图片路径

	private String needCoins;// 需要几个金币兑换

	private MsgType msgType = MsgType.LOTTERY;;// 默认的消息类型为抽奖

	private Long productId;//产品的id

	private String LOTTERY_PERFIX = "http://gift.sohu.com/lottery/details/";

	private String GIFT_PERFIX = "http://gift.sohu.com/gift/details/";
	
	private int type = 1; //默认兑换，用户当新品上架的时候，处理礼品连接的问题 1：兑换  2:抽奖

	public FeedMsg(String passport, String wareName, String picPath,
			String needCoins, MsgType msgType, Long productId) {
		this.wareName = wareName;
		this.picPath = picPath;
		this.needCoins = needCoins;
		this.msgType = msgType;
		this.passport = passport;
		this.productId = productId;
	}
	/**
	 * 上架专用构造器，不是产品上架发送Feed的情况，请用另外的构造器
	 * @param passport
	 * @param wareName
	 * @param picPath
	 * @param needCoins
	 * @param msgType
	 * @param productId
	 * @param type
	 */
	public FeedMsg(String passport, String wareName, String picPath,
			String needCoins, MsgType msgType, Long productId, int type) {
		this(passport, wareName, picPath, needCoins, msgType, productId);
		this.type = type;
	}

	/**
	 * 拼接json串，发送FEED消息
	 * 如果是兑换则不发送产品的价格
	 */
	public void send() {
		try {
			JSONObject json = new JSONObject();
			json.put("type", msgType.type());
			json.put("name", wareName);
			json.put("link", getProductLink());
			json.put("pic", picPath);
			if (msgType != MsgType.EXCHANGE) {
				json.put("num", needCoins);//如果不是兑换，则加入needCoins参数
			}
			logger.info("Send Msg: passport{" + passport + "},type{"
					+ msgType.type() + "},needCoins{" + needCoins
					+ "},wareName{" + wareName + "},link{" + productId + "}."
					+ "},pic{" + picPath + "}.");
			PingUdpClient.sendMessage(passport, "", json.toString(),
					projectType, version);
		} catch (Exception e) {
			logger.error("Send feed Msg error!", e);
		}
	}

	/**
	 * 拼接连接的link串
	 * @return
	 */
	private String getProductLink() {
		StringBuffer sb = new StringBuffer();
		boolean on_lottery_ware = (msgType == MsgType.WARE_ON && type == 2);//上架抽奖产品
		if (msgType == MsgType.LOTTERY
				|| on_lottery_ware) {
			sb.append(LOTTERY_PERFIX).append(productId);
		} else {
			sb.append(GIFT_PERFIX).append(productId);
		}
		return sb.toString();
	}

	public String getWareName() {
		return wareName;
	}

	public String getPicPath() {
		return picPath;
	}

	public String getNeedCoins() {
		return needCoins;
	}

	public MsgType getMsgType() {
		return msgType;
	}

	/**
	 * 发送FEED的类型
	 * 
	 * @author leiyangbj6779
	 * 
	 */
	public enum MsgType {
		LOTTERY(1), // 抽奖
		EXCHANGE(2), // 兑换
		WARE_ON(3); // 新产品上架

		private MsgType(int type) {
			this.type = type;
		}

		private int type;

		public int type() {
			return type;
		}

	}

	public static void main(String[] args) {
		FeedMsg feedMsg = new FeedMsg(
				"wocsok@sohu.com",
				"testGift测试商品",
				"http://i2.itc.cn/20110922/a6e_9cd57d1b_80dc_1d31_77b6_ae4f99f63fb5_1.jpg ",
				"100", MsgType.LOTTERY, 1L);
		feedMsg.send();
	}

}
