package com.sohu.sur.model.admin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.sohu.sur.util.CdsConstant;

/**
 * @Title: SucPage.java
 * @Package com.sohu.sur.model.admin
 * @Description: TODO
 * @author leiyangbj6779
 * @date 2011-12-14 上午10:56:43
 * @version V1.0
 */
@Entity
@Table(name = "CDS_PAGE")
public class Page {
	
	@Transient
	protected final Log log = LogFactory.getLog(getClass());
	@Transient
	private static final String COMPART_STRING = "@";
	@Transient
	private String realityOutPath;
	@Transient
	private String realityDeployPath;
	@Transient
	private String outDir;

	/**
	 * 人工页面
	 */
	@Transient
	public static final int TYPE_MANUAL = 1;

	/**
	 * 数据源生成碎片
	 */
	@Transient
	public static final int TYPE_PRODUCE = 0;

	/**
	 * 新生成为发布
	 */
	@Transient
	public static final int STATUS_NEW = 0;

	/**
	 * 已收回
	 */
	@Transient
	public static final int STATUS_RECALL = 3;

	/**
	 * 已部署
	 */
	@Transient
	public static final int STATUS_DEPLOY = 1;

	/**
	 * 已删除
	 */
	@Transient
	public static final int STATUS_DELETE = 2;

	@ManyToOne(targetEntity = com.sohu.sur.model.admin.Region.class)
	@JoinColumn(name = "REGION_ID")
	private Region region;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "PATH")
	private String path;
	@Column(name = "URL")
	private String url;
	@Column(name = "P_DESC")
	private String desc;
	@Column(name = "TYPE")
	private int type;
	@Column(name = "P_STATUS")
	/** 0.撤回 1.部署 2.删除 */
	private int status;
	@Column(name = "UPDATE_TIME")
	private Date updateTime;
	@Column(name = "OPERATOR")
	private String operator;

	/**
	 * 碎片类型 0.整页 1.普通碎片 2.广告碎片
	 */
	@Column(name = "P_TYPE")
	private Integer pageType;

	/**
	 * 业务id，便于定为页面
	 */
	@Column(name = "BUSINESS_ID")
	private String businessId;

	/**
	 * 产生一个随机文件名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String createUuidFileName(String fileName) {
		return fileName + COMPART_STRING + UUID.randomUUID().toString();
	}

	public Page() {

	}

	public Page(String path, String url, Date updateTime, String operator) {
		this.path = path;
		this.url = url;
		this.updateTime = updateTime;
		this.operator = operator;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the pageType
	 */
	public Integer getPageType() {
		return pageType;
	}

	/**
	 * @param pageType
	 *            the pageType to set
	 */
	public void setPageType(Integer pageType) {
		this.pageType = pageType;
	}

	/**
	 * 只删除页面 不包括持久化操作
	 * 
	 * @return
	 */
	public boolean delete() {
		if (this.getStatus() == STATUS_DEPLOY) {
			throw new java.lang.IllegalStateException("已部署的不可被删除");
		}
		String filePath = realityOutPath();
		log.debug("delete filePath is " + filePath);
		// 修改自身状态
		this.setStatus(STATUS_DELETE);

		// 不再真的删除文件
		// FileUtils.deleteQuietly(new File(filePath));
		return true;
	}

	/**
	 * 获得输出路径全路径 包括文件名
	 * 
	 * @return
	 */
	private String realityOutPath() {

		if (this.realityOutPath == null) {
			this.realityOutPath = new StringBuffer().append(region.directory())
					.append("/").append(CdsConstant.OUT_DIR).append("/")
					.append(region.getId()).append("/").append(path).toString();
		}

		return this.realityOutPath;
	}

	/**
	 * 获得部署全路径 包括文件名
	 * 
	 * @return
	 */
	private String realityDeployPath() {

		if (this.realityDeployPath == null) {

			this.realityDeployPath = new StringBuffer()
					.append(region.directory()).append("/")
					.append(CdsConstant.DEPLOY_DIR).append("/")
					.append(region.getId()).append(path).toString();
		}

		return this.realityDeployPath;
	}

	/**
	 * 获得查看的地址
	 */
	public String getOutDir() {
		if (null == outDir) {
			this.outDir = new StringBuffer().append("/")
					.append(CdsConstant.OUT_DIR).append("/")
					.append(region.getId()).append("/").append(path).toString();
		}
		return outDir;
	}

	/**
	 * 删除发布目录下的文件 然后更新自我状态
	 * 
	 * @return
	 */

	public boolean recall() {
		if (this.getStatus() != STATUS_DEPLOY) {
			throw new java.lang.IllegalStateException("只能对发布页面进行撤回操作。");
		}
		if (CdsConstant.isWindows) {
			boolean ret = false;
			try {
				FileUtils.forceDelete(new File(realityDeployPath()));
				ret = true;
			} catch (Exception e) {
				log.error(e.getMessage(),e);
				ret = false;
			}
			if (ret) {
				// 修改自身状态
				this.setStatus(STATUS_RECALL);
				return true;
			} else {
				return false;
			}
		} else {
			String deploy = realityDeployPath();
			deploy = cancelUddiFileName(deploy);
			try {
				Runtime.getRuntime().exec("unlink " + deploy);
				this.setStatus(STATUS_RECALL);
			} catch (Exception e) {
				log.error(e.getMessage(),e);
			}
			return true;
		}

	}

	/**
	 * 删除发布目录下的文件 然后更新自我状态
	 * 
	 * @return
	 */
	public boolean forceRecall() {
		/**
		 * if (FileUtils.deleteQuietly(new File(realityDeployPath()))) { //
		 * 修改自身状态 this.setStatus(STATUS_RECALL); return true; } else { return
		 * false; }
		 */

		if (CdsConstant.isWindows) {
			boolean ret = false;
			try {
				FileUtils.forceDelete(new File(realityDeployPath()));
				ret = true;
			} catch (Exception e) {
				e.printStackTrace();
				ret = false;
			}
			if (ret) {
				// 修改自身状态
				this.setStatus(STATUS_RECALL);
				return true;
			} else {
				return false;
			}
		} else {
			String deploy = realityDeployPath();
			deploy = cancelUddiFileName(deploy);
			try {
				Runtime.getRuntime().exec("unlink " + deploy);
				this.setStatus(STATUS_RECALL);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
			return true;
		}
	}

	/**
	 * 从输出目录迁移到发布目录 在windows平台下采用copy模式 linux 下采用ln模式提高效率
	 * 
	 * @return
	 */
	public boolean deploy() {
		String outFilePath = realityOutPath();

		String deploy = realityDeployPath();
		deploy = cancelUddiFileName(deploy);
		try {

			if (CdsConstant.isWindows) {
				FileUtils.copyFile(new File(outFilePath), new File(deploy));
			} else {
				File deployFile = new File(deploy);
				if (!deployFile.getParentFile().exists()) {
					if (!deployFile.getParentFile().mkdirs()) {
						throw new IOException("Destination '" + deployFile
								+ "' directory cannot be created");
					}
				}

				Runtime.getRuntime().exec(
						"ln -sf " + outFilePath + " " + deploy);
			}

			// 修改自身状态
			this.setStatus(STATUS_DEPLOY);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new java.lang.IllegalStateException("部署异常");
		}
		return true;
	}

	public static String cancelUddiFileName(String deploy) {
		if (deploy.indexOf("@") > -1) {
			deploy = deploy.substring(0, deploy.lastIndexOf("@"));
		}
		return deploy;
	}

	/**
	 * 将碎片写入文件中
	 */
	public void saveFragment(String fragment) throws IOException {
		if (this.type == TYPE_PRODUCE) {
			throw new java.lang.IllegalStateException(" 自动碎片不能做人工碎片使用 ");
		}

		if (this.path == null || this.path.equals("") || fragment == null
				|| fragment.equals("")) {
			throw new java.lang.IllegalArgumentException(" 无效参数 ");
		}

		String realyOutPathName = realityOutPath();
		log.info("realyOutPathName = " + realyOutPathName);

		File outFilePath = new File(realyOutPathName.substring(0,
				realyOutPathName.lastIndexOf('/')));

		if (!outFilePath.exists()) {
			outFilePath.mkdirs();
		}

		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(realyOutPathName), "GBK"));
			writer.write(fragment);
		} finally {
			if (writer != null) {
				writer.flush();
				writer.close();
			}
		}

	}

	/**
	 * 将碎片写入文件中
	 */
	public void saveBrandFragment(String fragment) throws IOException {

		if (this.path == null || this.path.equals("") || fragment == null
				|| fragment.equals("")) {
			throw new java.lang.IllegalArgumentException(" 无效参数 ");
		}

		String realyOutPathName = realityOutPath();

		File outFilePath = new File(realyOutPathName.substring(0,
				realyOutPathName.lastIndexOf('/')));

		if (!outFilePath.exists()) {
			outFilePath.mkdirs();
		}

		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(realyOutPathName), "GBK"));
			writer.write(fragment);
		} finally {
			if (writer != null) {
				writer.flush();
				writer.close();
			}
		}

	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	/**
	 * 将碎片文件重新读出
	 * 
	 * @return
	 * @throws IOException
	 */
	public String readFragment() throws IOException {
		StringBuffer fragment = new StringBuffer();
		if (this.path == null || this.path.equals("")) {
			throw new java.lang.IllegalArgumentException(" 无效参数 ");
		}
		String realyOutPathName = realityOutPath();

		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(
					realyOutPathName), "GBK"));
			String str;
			while ((str = in.readLine()) != null) {
				fragment.append(str).append("\n");
			}
		} finally {
			if (in != null) {
				in.close();
			}
		}

		return fragment.toString();
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Page other = (Page) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	public String toString() {
		return new ToStringBuilder(ToStringStyle.MULTI_LINE_STYLE)
				.append("class", getClass()).append("id", getId())
				.append("operator", getOperator()).append("path", getPath())
				.append("status", getStatus()).append("type", getType())
				.append("updateTime", getUpdateTime()).append("url", getUrl())
				.toString();
	}

}
