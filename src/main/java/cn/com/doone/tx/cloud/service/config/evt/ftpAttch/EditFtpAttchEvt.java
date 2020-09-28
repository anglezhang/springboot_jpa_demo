package cn.com.doone.tx.cloud.service.config.evt.ftpAttch;

import java.io.Serializable;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;

/**
 * ftp文件编辑 yecz
 */
public class EditFtpAttchEvt extends BaseEvt implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9194705061680760956L;
	/** 主键 */
	private Long id;
	/** 文件名 */
	private String orgFileName;

	/** 保存文件名 */
	private String saveFileName;

	/** 文件后缀名 */
	private String fileSuffix;

	/** 文件保存相对路径 */
	private String filePath;

	/** ftp路径 */
	private String ftpUrl;

	/** web访问路径 */
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
}
