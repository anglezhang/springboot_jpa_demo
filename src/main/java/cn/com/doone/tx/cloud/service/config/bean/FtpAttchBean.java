package cn.com.doone.tx.cloud.service.config.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatBaseEntity;

/**
 * 文件上传Bean Created by yecz on 2017/3/2.
 */
@Entity
@Table(name = "tf_ftp_attch")
@SequenceGenerator(name = "common_generator", sequenceName = "s_tf_ftp_attch", allocationSize = 1, initialValue = 1)
public class FtpAttchBean extends PlatBaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7384602988201662875L;

	/** 文件名 */
	@Column(name = "ORG_FILENAME", length = 100, nullable = true)
	private String orgFileName;

	/** 保存文件名 */
	@Column(name = "SAVE_FILENAME", length = 100, nullable = true)
	private String saveFileName;

	/** 文件后缀名 */
	@Column(name = "FILE_SUFFIX", length = 20, nullable = true)
	private String fileSuffix;

	/** 文件保存相对路径 */
	@Column(name = "FILE_PATH", length = 200, nullable = true)
	private String filePath;

	/** ftp路径 */
	@Column(name = "FTP_URL", length = 200, nullable = true)
	private String ftpUrl;

	/** web访问路径 */
	@Column(name = "WEB_URL", length = 200, nullable = true)
	private String webUrl;

	/** 唯一编码 */
	@Column(name = "ATTACH_CODE", length = 100, nullable = true)
	private String attachCode;

	public String getAttachCode() {
		return attachCode;
	}

	public void setAttachCode(String attachCode) {
		this.attachCode = attachCode;
	}

	public String getOrgFileName() {
		return orgFileName;
	}

	public void setOrgFileName(String orgFileName) {
		this.orgFileName = orgFileName;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
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

	@Override
	public String toString() {
		return "FtpAttchBean [orgFileName=" + orgFileName + ", saveFileName=" + saveFileName + ", fileSuffix="
				+ fileSuffix + ", filePath=" + filePath + ", ftpUrl=" + ftpUrl + ", webUrl=" + webUrl + ", attachCode="
				+ attachCode + ", toString()=" + super.toString() + "]";
	}
}
