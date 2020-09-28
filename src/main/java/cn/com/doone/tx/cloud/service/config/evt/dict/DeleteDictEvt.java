package cn.com.doone.tx.cloud.service.config.evt.dict;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;

import java.util.Date;

/**
 * Created by APPLE on 2017/2/22.
 */
public class DeleteDictEvt extends BaseEvt {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2999299130537280451L;

	private String tableName;

	private String columnName;

	private Long dictId;

	private Date updateTime;

	private Long operator;

	private String languageType;

	public String getLanguageType() {
		return languageType;
	}

	public void setLanguageType(String languageType) {
		this.languageType = languageType;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Long getDictId() {
		return dictId;
	}

	public void setDictId(Long dictId) {
		this.dictId = dictId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getOperator() {
		return operator;
	}

	public void setOperator(Long operator) {
		this.operator = operator;
	}

	@Override
	public String toString() {
		return "DeleteDictEvt{" + "tableName='" + tableName + '\'' + ", columnName='" + columnName + '\'' + ", dictId="
				+ dictId + ", updateTime=" + updateTime + ", operator=" + operator + '}';
	}
}
