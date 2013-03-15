package com.sohu.sur.model.admin;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "SUC_STOCKS")
public class SucStocks implements java.io.Serializable {

	private Long id;
	private Long gift_id;
	private String card_id;
	private String password;
	private Integer status;
	private Date updateTime;
	private String batchFile;
	private String editor;// 操作人
	
	public SucStocks(){}
	
	public SucStocks(Long id, Long gift_id, String card_id, String password,
			Integer status, Date updateTime, String batchFile, String editor) {
		super();
		this.id = id;
		this.gift_id = gift_id;
		this.card_id = card_id;
		this.password = password;
		this.status = status;
		this.updateTime = updateTime;
		this.batchFile = batchFile;
		this.editor = editor;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "GIFT_ID")
	public Long getGift_id() {
		return gift_id;
	}

	public void setGift_id(Long gift_id) {
		this.gift_id = gift_id;
	}

	@Column(name = "CARD_ID")
	public String getCard_id() {
		return card_id;
	}

	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}

	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "STATUS")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "UPDATETIME")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "BATCHFILE")
	public String getBatchFile() {
		return batchFile;
	}
	
	public void setBatchFile(String batchFile) {
		this.batchFile = batchFile;
	}

	@Column(name = "EDITOR")
	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

}