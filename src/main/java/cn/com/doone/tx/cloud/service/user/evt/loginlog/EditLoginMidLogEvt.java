package cn.com.doone.tx.cloud.service.user.evt.loginlog;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-5-22.
 */
public class EditLoginMidLogEvt extends BaseEvt implements Serializable {
	/**
	 * 统计开始时间
	 */
	@Field(length = 12, nullable = true, value = "时间")
	private String yesterDayDateStr;

	/**
	 * 定时器日志id
	 */
	@Field(length = 10, nullable = false, value = "定时器日志id")
	private Long timingLogId;

	/**
	 * 表名
	 */
	@Field(length = 50, nullable = true, value = "表名")
	private String tableName;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getYesterDayDateStr() {
		return yesterDayDateStr;
	}

	public void setYesterDayDateStr(String yesterDayDateStr) {
		this.yesterDayDateStr = yesterDayDateStr;
	}

	public Long getTimingLogId() {
		return timingLogId;
	}

	public void setTimingLogId(Long timingLogId) {
		this.timingLogId = timingLogId;
	}
}
