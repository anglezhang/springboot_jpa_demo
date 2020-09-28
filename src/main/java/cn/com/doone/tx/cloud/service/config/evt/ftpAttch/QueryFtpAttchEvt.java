package cn.com.doone.tx.cloud.service.config.evt.ftpAttch;

import java.io.Serializable;

import cn.com.doone.tx.cloud.tool.service.common.base.QueryEvt;

/**
 * 
 * ftp文件查询 yecz
 */
public class QueryFtpAttchEvt extends QueryEvt implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4299043316805265878L;
	/** 主键 */
	private Long id;
	/** 文件名 */
	private String orgFileName;

	/** 保存文件名 */
	private String saveFilename;

	/** 文件后缀名 */
	private String fileSuffix;

	/** 文件保存相对路径 */
	private String filePath;

	/** 附件唯一编码 */
	private String attachCode;
	
	private String ids;
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

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
}
