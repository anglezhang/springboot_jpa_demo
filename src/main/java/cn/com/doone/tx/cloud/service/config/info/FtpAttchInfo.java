package cn.com.doone.tx.cloud.service.config.info;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * ftp文件响应类
 */
public class FtpAttchInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4574244448058626224L;
	/** 主键 */
	@Column(name = "Id")
	private Long id;
	/** 文件名 */
	@Column(name = "ORG_FILENAME")
	private String orgFileName;

	/** 保存文件名 */
	@Column(name = "SAVE_FILENAME")
	private String saveFilename;

	/** 文件后缀名 */
	@Column(name = "FILE_SUFFIX")
	private String fileSuffix;

	/** 文件保存相对路径 */
	@Column(name = "FILE_PATH")
	private String filePath;

	/** 状态 */
	@Column(name = "STATUS")
	private String status;
	/** 操作人员 */
	@Column(name = "operator")
	private Long operator;
	/** 创建时间 */
	@Column(name = "create_time")
	private Date createTime;
	/** 修改时间 */
	@Column(name = "update_timewq")
	private Date updateTime;
	/** ftp路径 */
	@Column(name = "FTP_URL")
	private String ftpUrl;

	/** web访问路径 */
	@Column(name = "WEB_URL")
	private String webUrl;

	/** 附件唯一编码 */
	private String attachCode;

	public String getAttachCode() {
		return attachCode;
	}

	public void setAttachCode(String attachCode) {
		this.attachCode = attachCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrgFileName() {
		return orgFileName;
	}

	public void setOrgFileName(String orgFileName) {
		this.orgFileName = orgFileName;
	}

	public String getSaveFilename() {
		return saveFilename;
	}

	public void setSaveFilename(String saveFilename) {
		this.saveFilename = saveFilename;
	}

	public String getFileSuffix() {
		return fileSuffix;
	}

	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getOperator() {
		return operator;
	}

	public void setOperator(Long operator) {
		this.operator = operator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getFtpUrl() {
		return ftpUrl;
	}

	public void setFtpUrl(String ftpUrl) {
		this.ftpUrl = ftpUrl;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}
}