package com.sohu.sur.util.lottery;

public class UseMedal {
	
	private String id;
	private float discount;
	
	
	
	public UseMedal() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UseMedal(String id, float discount) {
		super();
		this.id = id;
		this.discount = discount;
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return the discount
	 */
	public float getDiscount() {
		return discount;
	}
	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	
	
	
}
