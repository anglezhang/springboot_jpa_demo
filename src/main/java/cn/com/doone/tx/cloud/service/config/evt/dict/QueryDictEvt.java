package cn.com.doone.tx.cloud.service.config.evt.dict;

import cn.com.doone.tx.cloud.tool.service.common.base.QueryEvt;

/**
 * Created by APPLE on 2017/2/22.
 */
public class QueryDictEvt extends QueryEvt {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8286891350888310496L;

	private String tableName;

	private String columnName;

	private String columnRemark;

	private Long dictId;
	// 查重用ID
	private Long dictExistId;

	private String dictValue;

	private String valueRemark;

	private Long parentId;

	private Long sort;

	private String languageType;

	private String languageTypeAdd;

	public Long getDictExistId() {
		return dictExistId;
	}

	public void setDictExistId(Long dictExistId) {
		this.dictExistId = dictExistId;
	}

	public String getLanguageTypeAdd() {
		return languageTypeAdd;
	}

	public void setLanguageTypeAdd(String languageTypeAdd) {
		this.languageTypeAdd = languageTypeAdd;
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

	public String getDictValue() {
		return dictValue;
	}

	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getValueRemark() {
		return valueRemark;
	}

	public void setValueRemark(String valueRemark) {
		this.valueRemark = valueRemark;
	}

	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "QueryDictEvt{" + "tableName='" + tableName + '\'' + ", columnName='" + columnName + '\'' + ", dictId="
				+ dictId + ", dictValue='" + dictValue + '\'' + ", valueRemark='" + valueRemark + '\'' + ", parentId="
				+ parentId + ", sort=" + sort + '}';
	}

	public String getColumnRemark() {
		return columnRemark;
	}

	public void setColumnRemark(String columnRemark) {
		this.columnRemark = columnRemark;
	}

	public String getLanguageType() {
		return languageType;
	}

	public void setLanguageType(String languageType) {
		this.languageType = languageType;
	}
}
