package cn.com.doone.tx.cloud.service.config.evt.ftpAttch;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

public class DelFtpAttchMultEvt extends BaseEvt {

	private static final long serialVersionUID = 7422736619125039833L;
	/**
	 * 
	 */
	/** 附件ID，用逗号隔开 **/
	@Field(nullable = false, length = 10, value = "附件ID")
	private String ids;

	/** 附件唯一编码 */
	private String attachCodes;

	public String getAttachCodes() {
		return attachCodes;
	}

	public void setAttachCodes(String attachCodes) {
		this.attachCodes = attachCodes;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}
