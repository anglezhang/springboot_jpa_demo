package cn.com.doone.tx.cloud.service.config.evt.ftpAttch;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;

/**
 * ftp文件删除 yecz
 */
public class DelFtpAttchEvt extends BaseEvt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1034263138544429531L;
	/** 文件id */
	private Long id;

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
}
