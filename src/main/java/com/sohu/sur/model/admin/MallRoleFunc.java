package com.sohu.sur.model.admin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 角色 功能中间表
 * @author xuewuhao
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "MALL_ROLE_FUNC")
public class MallRoleFunc implements java.io.Serializable {

	private Long rfId;
	private Long roleId;
	private Long funcId;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getFuncId() {
		return funcId;
	}

	public void setFuncId(Long funcId) {
		this.funcId = funcId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getRfId() {
		return rfId;
	}

	public void setRfId(Long rfId) {
		this.rfId = rfId;
	}

}