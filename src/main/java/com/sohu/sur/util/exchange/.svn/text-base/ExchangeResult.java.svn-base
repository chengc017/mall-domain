package com.sohu.sur.util.exchange;


public class ExchangeResult {
	
	private int status;   /* 兑换 结果  -1:非法请求  0:无勋章 1:积分不足   2:兑换失败  3:兑换成功 */
	private long subpoint; /*兑换扣除积分*/
	private long remain;   /*兑换后用户剩余积分*/
	private String giftdealid; /*兑换dealid*/
	private String cat ;  // 0实物 1优惠券 2虚拟
	private String email ; 
	private String memo; /*备注*/
	
	
	public ExchangeResult() {
	}
	

	public ExchangeResult(int status, long subpoint, long remain,
			String giftdealid, String cat, String email, String memo) {
		super();
		this.status = status;
		this.subpoint = subpoint;
		this.remain = remain;
		this.giftdealid = giftdealid;
		this.cat = cat;
		this.email = email;
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
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
