package cn.com.doone.tx.cloud.service.config.evt.xss;

import cn.com.doone.tx.cloud.tool.service.common.base.QueryEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

import java.io.Serializable;

public class QueryXssHeadSetEvt extends QueryEvt implements Serializable {

	private static final long serialVersionUID = -6862277459668631505L;

	@Field(length = 11, nullable = true, value = "XSS配置id")
	private Long xssConfigId;

	public Long getXssConfigId() {
		return xssConfigId;
	}

	public void setXssConfigId(Long xssConfigId) {
		this.xssConfigId = xssConfigId;
	}
	
	
}
