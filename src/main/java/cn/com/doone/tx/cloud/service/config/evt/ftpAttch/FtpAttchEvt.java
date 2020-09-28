package cn.com.doone.tx.cloud.service.config.evt.ftpAttch;

import org.springframework.web.multipart.MultipartFile;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;

import java.io.Serializable;

/**
 * ftp文件上传 ycz
 */
public class FtpAttchEvt extends BaseEvt implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5118877031695189804L;

	private String webUploadPath;// 文件上传本地路径

	private String orgFileName;// 原文件名

	private String saveFilename;// 保存的文件名

	private MultipartFile file;

	public String getOrgFileName() {
		return orgFileName;
	}

	public void setOrgFileName(String orgFileName) {
		this.orgFileName = orgFileName;
	}

	public String getWebUploadPath() {
		return webUploadPath;
	}

	public void setWebUploadPath(String webUploadPath) {
		this.webUploadPath = webUploadPath;
	}

	public String getSaveFilename() {
		return saveFilename;
	}

	public void setSaveFilename(String saveFilename) {
		this.saveFilename = saveFilename;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
