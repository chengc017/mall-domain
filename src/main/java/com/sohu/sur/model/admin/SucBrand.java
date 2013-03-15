package com.sohu.sur.model.admin;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sohu.sur.util.EnumUtils;

/**
 *品牌实体 
 *@author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SUC_BRAND")
public class SucBrand implements java.io.Serializable {

	private static final long serialVersionUID = 8790822456038456682L;
	private Long id;
	private String chName;
	private String enName;
	private String nameInitial;
	private String descr;
	private String logo;
	private Integer status;
	private Long regionId;
	private String operator;
	private Date operateTime;
	
	//-----------辅助显示----------
	private String statusString;

	public SucBrand() {
	}

	public SucBrand(Timestamp operateTime) {
		this.operateTime = operateTime;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "CH_NAME")
	public String getChName() {
		return this.chName;
	}

	public void setChName(String chName) {
		this.chName = chName;
	}
	
	@Column(name = "EN_NAME")
	public String getEnName() {
		return this.enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	@Column(name = "NAME_INITIAL")
	public String getNameInitial() {
		return this.nameInitial;
	}

	public void setNameInitial(String nameInitial) {
		this.nameInitial = nameInitial;
	}

	@Column(name = "DESCR")
	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	@Column(name = "LOGO")
	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Column(name = "STATUS")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "REGION_ID")
	public Long getRegionId() {
		return this.regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	@Column(name = "OPERATOR")
	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "OPERATE_TIME")
	public Date getOperateTime() {
		return this.operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	@Transient
	public String getStatusString() {
		
		if(status != null)
		{
			Map<Integer, String> map = EnumUtils.getStatusMap();
			
			if(map.containsKey(status))
			{
				statusString = map.get(status);
			}			
		}
		
		return statusString;
	}

}