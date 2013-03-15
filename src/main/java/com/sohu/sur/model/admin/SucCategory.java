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
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.sohu.sur.util.EnumUtils;

/**
 * SucCategory entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SUC_CATEGORY")
public class SucCategory implements java.io.Serializable {

	private Long id;
	private String name;
	private String keyName;
	private Long parenetSortId;
	private String createUser;
	private Date createTime;
	private Date operationTime;
	private Integer status;
	private Integer prority;
	private Integer sortId;
	private Integer level;
	private Long regionId;
	private String keyWords;
	private Integer isLeaf;
	private String descr;
	/** 辅助显示 */
	private String statusString;// 状态
	private String isLeafString;// 是否是叶子结点
	private boolean isLeafBoolean;// 是否是叶子节点

	public SucCategory() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "KEY_NAME")
	public String getKeyName() {
		return this.keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	
	@Column(name = "PARENET_SORT_ID")
	public Long getParenetSortId() {
		return this.parenetSortId;
	}

	public void setParenetSortId(Long parenetSortId) {
		this.parenetSortId = parenetSortId;
	}

	@Column(name = "CREATE_USER")
	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "OPERATION_TIME")
	public Date getOperationTime() {
		return this.operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}

	@Column(name = "STATUS")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "PRORITY")
	public Integer getPrority() {
		return this.prority;
	}

	public void setPrority(Integer prority) {
		this.prority = prority;
	}

	@Column(name = "SORT_ID")
	public Integer getSortId() {
		return this.sortId;
	}

	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}

	@Column(name = "LEVEL")
	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Column(name = "REGION_ID")
	public Long getRegionId() {
		return this.regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	@Column(name = "KEY_WORDS")
	public String getKeyWords() {
		return this.keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	@Column(name = "DESCR")
	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	@Column(name = "IS_LEAF")
	public Integer getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}

	@Transient
	public String getStatusString() {
		if (status != null) {
			if (status == EnumUtils.status.正常.ordinal()) {
				statusString = EnumUtils.status.正常.name();
			} else if (status == EnumUtils.status.删除.ordinal()) {
				statusString = EnumUtils.status.删除.name();
			} else if (status == EnumUtils.status.不确定.ordinal()) {
				statusString = EnumUtils.status.不确定.name();
			}
		}

		return statusString;
	}

	@Transient
	public String getIsLeafString() {
		if (isLeaf != null) {
			if (isLeaf == EnumUtils.yesOrNo.是.ordinal()) {
				isLeafString = EnumUtils.yesOrNo.是.name();
			} else {
				isLeafString = EnumUtils.yesOrNo.否.name();
			}
		}

		return isLeafString;
	}

	@Transient
	public boolean getIsLeafBoolean() {
		if (isLeaf != null) {
			isLeafBoolean = (isLeaf == EnumUtils.yesOrNo.是.ordinal()) ? true
					: false;
		} else {
			// 默认为“非叶子”
			isLeafBoolean = false;
		}

		return isLeafBoolean;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result
				+ ((createUser == null) ? 0 : createUser.hashCode());
		result = prime * result + ((descr == null) ? 0 : descr.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isLeaf == null) ? 0 : isLeaf.hashCode());
		result = prime * result + ((keyName == null) ? 0 : keyName.hashCode());
		result = prime * result
				+ ((keyWords == null) ? 0 : keyWords.hashCode());
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((operationTime == null) ? 0 : operationTime.hashCode());
		result = prime * result
				+ ((parenetSortId == null) ? 0 : parenetSortId.hashCode());
		result = prime * result + ((prority == null) ? 0 : prority.hashCode());
		result = prime * result
				+ ((regionId == null) ? 0 : regionId.hashCode());
		result = prime * result + ((sortId == null) ? 0 : sortId.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final SucCategory other = (SucCategory) obj;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (createUser == null) {
			if (other.createUser != null)
				return false;
		} else if (!createUser.equals(other.createUser))
			return false;
		if (descr == null) {
			if (other.descr != null)
				return false;
		} else if (!descr.equals(other.descr))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isLeaf == null) {
			if (other.isLeaf != null)
				return false;
		} else if (!isLeaf.equals(other.isLeaf))
			return false;
		if (keyName == null) {
			if (other.keyName != null)
				return false;
		} else if (!keyName.equals(other.keyName))
			return false;
		if (keyWords == null) {
			if (other.keyWords != null)
				return false;
		} else if (!keyWords.equals(other.keyWords))
			return false;
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (operationTime == null) {
			if (other.operationTime != null)
				return false;
		} else if (!operationTime.equals(other.operationTime))
			return false;
		if (parenetSortId == null) {
			if (other.parenetSortId != null)
				return false;
		} else if (!parenetSortId.equals(other.parenetSortId))
			return false;
		if (prority == null) {
			if (other.prority != null)
				return false;
		} else if (!prority.equals(other.prority))
			return false;
		if (regionId == null) {
			if (other.regionId != null)
				return false;
		} else if (!regionId.equals(other.regionId))
			return false;
		if (sortId == null) {
			if (other.sortId != null)
				return false;
		} else if (!sortId.equals(other.sortId))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}


}