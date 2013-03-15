package com.sohu.sur.model.admin;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Index;

/**
 * 用户实体
 * 
 * @author xuewuhao
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "GOOD_USER")
public class GoodUser implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private Date createTime;
	// 用户级别 优质用户"0" 金牌用户"1"
	private String level;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = true)
	@Index(name = "gooduser_name_index")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Temporal(value = TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getLevel() {
		//默认为优质用户
		if (level == null) {
			level = "0";
		}
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
}