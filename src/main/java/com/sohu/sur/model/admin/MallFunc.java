package com.sohu.sur.model.admin;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 功能实体类
 * 
 * @author xuewuhao
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "MALL_FUNC")
public class MallFunc implements java.io.Serializable {

	// Fields

	private Long funcId;
	private String funcName;
	private String url;
	private String funcDesc;
	private Long parentId;
	private Date createTime;

	// Constructors

	/** default constructor */
	public MallFunc() {
	}

	/** minimal constructor */
	public MallFunc(String funcName, String url, Long parentId, Date createTime) {
		this.funcName = funcName;
		this.url = url;
		this.parentId = parentId;
		this.createTime = createTime;
	}

	/** full constructor */
	public MallFunc(String funcName, String url, String funcDesc, Long parentId, Date createTime) {
		this.funcName = funcName;
		this.url = url;
		this.funcDesc = funcDesc;
		this.parentId = parentId;
		this.createTime = createTime;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getFuncId() {
		return this.funcId;
	}

	public void setFuncId(Long funcId) {
		this.funcId = funcId;
	}
	@Column(nullable=false)
	public String getFuncName() {
		return this.funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFuncDesc() {
		return this.funcDesc;
	}

	public void setFuncDesc(String funcDesc) {
		this.funcDesc = funcDesc;
	}

	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Temporal(value = TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}