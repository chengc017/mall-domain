package com.sohu.sur.util.lottery;
/**
 * Created by Eclipse.
 * @author: ShenRuiQing
 * @version: 1.0
 * @since: 1.0
 * Date: 2011-03-16
 */
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="result")
public class LotteryResult {
	
	private int status;   /* 抽奖结果  -1:非法请求  0:无勋章 1:积分不足 2:未抽中 3:抽中 */  
	private long subpoint; /*当抽中时扣除积分*/
	private long remain;   /*当抽中后用户剩余积分*/
	private String giftdealid; /*当抽中后dealid*/
	private String cat ;  //宝贝类型,   /* 1=实物，2=虚拟 */
	private String userid ; 	
	private String memo; /*备注*/
	


	public LotteryResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LotteryResult(int status, long subpoint, long remain,
			String giftdealid, String cat, String userid, String memo) {
		super();
		this.status = status;
		this.subpoint = subpoint;
		this.remain = remain;
		this.giftdealid = giftdealid;
		this.cat = cat;
		this.userid = userid;
		this.memo = memo;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getSubpoint() {
		return subpoint;
	}

	public void setSubpoint(long subpoint) {
		this.subpoint = subpoint;
	}

	public long getRemain() {
		return remain;
	}

	public void setRemain(long remain) {
		this.remain = remain;
	}

	public String getGiftdealid() {
		return giftdealid;
	}

	public void setGiftdealid(String giftdealid) {
		this.giftdealid = giftdealid;
	}

	/**
	 * @return the memo
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * @param memo the memo to set
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * @return the cat
	 */
	public String getCat() {
		return cat;
	}

	/**
	 * @param cat the cat to set
	 */
	public void setCat(String cat) {
		this.cat = cat;
	}

	/**
	 * @return the email
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * @param email the email to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	
}
