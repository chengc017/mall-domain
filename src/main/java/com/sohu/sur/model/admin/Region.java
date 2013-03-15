/**
 * 
 */
package com.sohu.sur.model.admin;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.sohu.sur.util.CdsConstant;

/**
 * @author leiyangbj6779
 * 
 */
@Entity
@Table(name = "CDS_REGION")
public class Region {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "HOST")
	private String host;
	@Column(name = "FILE_DIRECTORY")
	private String fileDirectory;
	@Column(name = "PRODUCE_FILEDIRECTORY")
	private String produce_fileDirectory; // 生成环境下的发布目录
	@Column(name = "D_DESC")
	private String desc;
	@Column(name = "P_STATUS")
	private int status;
	@Column(name = "UPDATE_TIME")
	private Date updateTime;
	@Column(name = "APP_ID")
	private Long appId;

	public String toString() {
		return new ToStringBuilder(ToStringStyle.MULTI_LINE_STYLE)
				.append("id", name).append("name", name).append("host", host)
				.append("fileDirectory", fileDirectory)
				.append("produce_fileDirectory", produce_fileDirectory)
				.append("status", status).append("desc", desc)
				.append("updateTime", updateTime).toString();
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Region() {
	}

	public Region(Long id, String name, String host, String fileDirectory,
			String produce_fileDirectory) {
		super();
		this.id = id;
		this.name = name;
		this.host = host;
		this.fileDirectory = fileDirectory;
		this.produce_fileDirectory = produce_fileDirectory;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getFileDirectory() {
		return fileDirectory;
	}

	public String directory() {
		if (!CdsConstant.isWindows) {
			return this.produce_fileDirectory;
		}
		return this.fileDirectory;
	}

	public void setFileDirectory(String fileDirectory) {
		this.fileDirectory = fileDirectory;
	}

	public String getProduce_fileDirectory() {
		return produce_fileDirectory;
	}

	public void setProduce_fileDirectory(String produce_fileDirectory) {
		this.produce_fileDirectory = produce_fileDirectory;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fileDirectory == null) ? 0 : fileDirectory.hashCode());
		result = prime
				* result
				+ ((produce_fileDirectory == null) ? 0 : produce_fileDirectory
						.hashCode());
		result = prime * result + ((host == null) ? 0 : host.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + status;
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
		final Region other = (Region) obj;

		if (fileDirectory == null) {
			if (other.fileDirectory != null)
				return false;
		} else if (!fileDirectory.equals(other.fileDirectory))
			return false;

		if (produce_fileDirectory == null) {
			if (other.produce_fileDirectory != null)
				return false;
		} else if (!produce_fileDirectory.equals(other.produce_fileDirectory))
			return false;

		if (host == null) {
			if (other.host != null)
				return false;
		} else if (!host.equals(other.host))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

}
