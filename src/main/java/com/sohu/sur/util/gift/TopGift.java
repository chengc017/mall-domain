package com.sohu.sur.util.gift;

import java.math.BigInteger;

/**
 * 首页左侧热门兑换
 * @author xuewuhao
 *
 */
public class TopGift {

	private BigInteger id;
	private String name;
	private String view_name;
	private String type;
	private String logo;
	
	
	
	public TopGift() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public TopGift(BigInteger id, String name, String view_name, String type,
			String logo) {
		super();
		this.id = id;
		this.name = name;
		this.view_name = view_name;
		this.type = type;
		this.logo = logo;
	}


	/**
	 * @return the id
	 */
	public BigInteger getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(BigInteger id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the view_name
	 */
	public String getView_name() {
		return view_name;
	}
	/**
	 * @param view_name the view_name to set
	 */
	public void setView_name(String view_name) {
		this.view_name = view_name;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}


	/**
	 * @return the logo
	 */
	public String getLogo() {
		return logo;
	}


	/**
	 * @param logo the logo to set
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	

}
