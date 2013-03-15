package com.sohu.sur.model.admin;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户角色中间表
 * @author xuewuhao
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "MALL_USER_ROLE")
public class MallUserRole implements java.io.Serializable {

	
	private Long urId;
	private Long userId;
	private Long roleId;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getUrId() {
		return urId;
	}
	public void setUrId(Long urId) {
		this.urId = urId;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
}