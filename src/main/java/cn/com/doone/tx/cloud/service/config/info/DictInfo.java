package cn.com.doone.tx.cloud.service.config.info;

import java.io.Serializable;

public class DictInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8211408218975691930L;
	
	private Integer parentId;
	
	private String tableName;

	private String columnName;
	
	private String columnRemark;

	private String dictValue;
	
	private String valueRemark;
	
	private String extendRemark;
	
	private Integer sort;
	
	private Integer dictType;
	
	private String status;
	
	private String createTime;
	
	private String updateTime;
	
	private String centerDatabase;
	
	private String languageType;

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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

	public String getColumnRemark() {
		return columnRemark;
	}

	public void setColumnRemark(String columnRemark) {
		this.columnRemark = columnRemark;
	}

	public String getDictValue() {
		return dictValue;
	}

	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}

	public String getValueRemark() {
		return valueRemark;
	}

	public void setValueRemark(String valueRemark) {
		this.valueRemark = valueRemark;
	}

	public String getExtendRemark() {
		return extendRemark;
	}

	public void setExtendRemark(String extendRemark) {
		this.extendRemark = extendRemark;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getDictType() {
		return dictType;
	}

	public void setDictType(Integer dictType) {
		this.dictType = dictType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getCenterDatabase() {
		return centerDatabase;
	}

	public void setCenterDatabase(String centerDatabase) {
		this.centerDatabase = centerDatabase;
	}

	public String getLanguageType() {
		return languageType;
	}

	public void setLanguageType(String languageType) {
		this.languageType = languageType;
	}
	
	


}
