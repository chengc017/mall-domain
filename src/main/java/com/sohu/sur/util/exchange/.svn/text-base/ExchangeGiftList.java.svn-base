package com.sohu.sur.util.exchange;


import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="gifts")
public class ExchangeGiftList {
	
	private int count;
	private List<ExchangeGift> gifts;
	
	public ExchangeGiftList() {}
	
	public ExchangeGiftList(List<ExchangeGift> gifts) {
		this.gifts = gifts;
		this.count = gifts.size();
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	@XmlElement(name="gift")
	public List<ExchangeGift> getGifts() {
		return gifts;
	}
	public void setGifts(List<ExchangeGift> gifts) {
		this.gifts = gifts;
	}
	
}
