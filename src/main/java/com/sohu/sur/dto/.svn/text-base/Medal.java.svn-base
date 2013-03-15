package com.sohu.sur.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "medal")
public class Medal {

	private String name;
	private String description;
	private String code;
	private String productName;
	private float discount;

	public Medal() {
		super();
	}

	public Medal(String name, String description, String code,
			String productName, float discount) {
		super();
		this.name = name;
		this.description = description;
		this.code = code;
		this.productName = productName;
		this.discount = discount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public String getProductCode() {
		return code != null ? code.replaceAll("_\\d{2}", "") : null;
	}
}
