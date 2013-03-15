package com.sohu.sur.util.lottery;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="lotteryGifts")
public class LotteryGiftList {
	
	private int count;
	private List<LotteryGift> lotteryGifts;
	
	public LotteryGiftList() {}
	
	public LotteryGiftList(List<LotteryGift> lotteryGifts) {
		this.lotteryGifts = lotteryGifts;
		this.count = lotteryGifts.size();
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	@XmlElement(name="lotteryGift")
	public List<LotteryGift> getLotteryGifts() {
		return lotteryGifts;
	}

	public void setLotteryGifts(List<LotteryGift> lotteryGifts) {
		this.lotteryGifts = lotteryGifts;
	}
	
	
}
