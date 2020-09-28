package cn.com.doone.tx.cloud.service.config.evt.xss;

import cn.com.doone.tx.cloud.tool.service.common.base.QueryEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

import java.io.Serializable;

public class QueryXssRegexEvt extends QueryEvt implements Serializable {

	private static final long serialVersionUID = -3281246360708741382L;
	@Field(length = 11, nullable = true, value = "XSS规则id")
	private Long xssRuleId;

	public Long getXssRuleId() {
		return xssRuleId;
	}

	public void setXssRuleId(Long xssRuleId) {
		this.xssRuleId = xssRuleId;
	}

}
