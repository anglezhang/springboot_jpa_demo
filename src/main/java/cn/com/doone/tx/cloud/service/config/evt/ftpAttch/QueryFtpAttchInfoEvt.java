package cn.com.doone.tx.cloud.service.config.evt.ftpAttch;

import java.io.Serializable;

import cn.com.doone.tx.cloud.tool.service.common.base.QueryEvt;

/**
 * 
 * ftp文件查询 yecz
 */
public class QueryFtpAttchInfoEvt extends QueryEvt implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1659287439202916022L;
	/** 附件Id */
	private Long attchId;

	/** 附件唯一编码 */
	private String attachCode;

	public String getAttachCode() {
		return attachCode;
	}

	public void setAttachCode(String attachCode) {
		this.attachCode = attachCode;
	}
	public Long getAttchId() {
		return attchId;
	}

	public void setAttchId(Long attchId) {
		this.attchId = attchId;
	}
}
