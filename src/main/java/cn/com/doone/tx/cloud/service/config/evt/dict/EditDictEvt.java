package cn.com.doone.tx.cloud.service.config.evt.dict;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

import java.util.Date;

/**
 * Created by APPLE on 2017/2/22.
 */
public class EditDictEvt extends BaseEvt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8031071511998802706L;
	@Field(nullable = false, length = 32, value = "值不能为空")
	private String dictValue;
	@Field(nullable = false, length = 128, value = "值说明不能为空")
	private String valueRemark;

	private String extendRemark;

	@Field(nullable = false, length = 32, value = "id不能为空")
	private Long dictId;

	private String tableName;

	private String columnName;

	private String columnRemark;

	private Date updateTime;

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

	public Long getDictId() {
		return dictId;
	}

	public void setDictId(Long dictId) {
		this.dictId = dictId;
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

	public String getExtendRemark() {
		return extendRemark;
	}

	public void setExtendRemark(String extendRemark) {
		this.extendRemark = extendRemark;
	}

	@Override
	public String toString() {
		return "EditDictEvt{" + "dictValue='" + dictValue + '\'' + ", valueRemark='" + valueRemark + '\''
				+ ", extendRemark='" + extendRemark + '\'' + ", dictId=" + dictId + ", tableName='" + tableName + '\''
				+ ", columnName='" + columnName + '\'' + ", updateTime=" + updateTime + ", operator=" + operator + '}';
	}

	public String getColumnRemark() {
		return columnRemark;
	}

	public void setColumnRemark(String columnRemark) {
		this.columnRemark = columnRemark;
	}
}
