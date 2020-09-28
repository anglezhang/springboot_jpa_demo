package cn.com.doone.tx.cloud.service.config.evt.dict;

import cn.com.doone.tx.cloud.tool.service.common.base.QueryEvt;

/**
 * Created by APPLE on 2017/2/22.
 */
public class QueryDictConfigEvt extends QueryEvt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7840554614808476391L;

	private String tableName;

	private String columnName;

	private Long dictId;

	private String columnRemark;

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

	@Override
	public String toString() {
		return "QueryDictConfigEvt{" + "tableName='" + tableName + '\'' + ", columnName='" + columnName + '\''
				+ ", dictId=" + dictId + '}';
	}

	public String getColumnRemark() {
		return columnRemark;
	}

	public void setColumnRemark(String columnRemark) {
		this.columnRemark = columnRemark;
	}
}
