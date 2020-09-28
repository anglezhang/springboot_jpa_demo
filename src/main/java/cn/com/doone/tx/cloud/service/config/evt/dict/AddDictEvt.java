package cn.com.doone.tx.cloud.service.config.evt.dict;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

/**
 * Created by APPLE on 2017/2/22.
 */
public class AddDictEvt extends BaseEvt {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2444307326055701701L;
	@Field(nullable = false, length = 32, value = "表名不能为空")
	private String tableName;
	@Field(nullable = false, length = 32, value = "字段名不能为空")
	private String columnName;
	// @Field(nullable = false, length = 64, value = "字段描述不能为空")
	private String columnRemark;

	private Integer parentId;

	private String dictValue;

	private String valueRemark;

	private String extendRemark;

	private Integer dictType;

	private String centerDatabase;

	private Long operator;

	private String languageType;

	private String languageTypeAdd;

	public String getLanguageTypeAdd() {
		return languageTypeAdd;
	}

	public void setLanguageTypeAdd(String languageTypeAdd) {
		this.languageTypeAdd = languageTypeAdd;
	}

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

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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

	public Integer getDictType() {
		return dictType;
	}

	public void setDictType(Integer dictType) {
		this.dictType = dictType;
	}

	public Long getOperator() {
		return operator;
	}

	public void setOperator(Long operator) {
		this.operator = operator;
	}

	public String getCenterDatabase() {
		return centerDatabase;
	}

	public void setCenterDatabase(String centerDatabase) {
		this.centerDatabase = centerDatabase;
	}

	public String getExtendRemark() {
		return extendRemark;
	}

	public void setExtendRemark(String extendRemark) {
		this.extendRemark = extendRemark;
	}

	@Override
	public String toString() {
		return "AddDictEvt{" + "tableName='" + tableName + '\'' + ", columnName='" + columnName + '\'' + ", parentId="
				+ parentId + ", dictValue='" + dictValue + '\'' + ", valueRemark='" + valueRemark + '\''
				+ ", extendRemark='" + extendRemark + '\'' + ", dictType=" + dictType + ", centerDatabase='"
				+ centerDatabase + '\'' + ", operator=" + operator + '}';
	}

	public String getColumnRemark() {
		return columnRemark;
	}

	public void setColumnRemark(String columnRemark) {
		this.columnRemark = columnRemark;
	}
}
