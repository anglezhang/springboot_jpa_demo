package cn.com.doone.tx.cloud.service.config.evt.dict;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

import java.util.Date;

/**
 * Created by APPLE on 2017/2/22.
 */
public class EditDictTableEvt extends BaseEvt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6088133238230880950L;
	private String tableName;

	private String columnName;

	private String centerDatabase;

	@Field(nullable = false, length = 64, value = "字段描述不能为空")
	private String columnRemark;

	@Field(nullable = false, length = 32, value = "旧表名不能为空")
	private String oldTableName;

	@Field(nullable = false, length = 32, value = "旧字段名不能为空")
	private String oldColumnName;

	private Date updateTime;

	private Long operator;

	private String languageType;

	public String getLanguageType() {
		return languageType;
	}

	public void setLanguageType(String languageType) {
		this.languageType = languageType;
	}

	public String getOldColumnName() {
		return oldColumnName;
	}

	public void setOldColumnName(String oldColumnName) {
		this.oldColumnName = oldColumnName;
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

	public String getOldTableName() {
		return oldTableName;
	}

	public void setOldTableName(String oldTableName) {
		this.oldTableName = oldTableName;
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
		return "EditDictTableEvt{" + "tableName='" + tableName + '\'' + ", columnName='" + columnName + '\''
				+ ", oldTableName='" + oldTableName + '\'' + ", oldColumnName='" + oldColumnName + '\''
				+ ", updateTime=" + updateTime + ", operator=" + operator + '}';
	}

	public String getCenterDatabase() {
		return centerDatabase;
	}

	public void setCenterDatabase(String centerDatabase) {
		this.centerDatabase = centerDatabase;
	}

	public String getColumnRemark() {
		return columnRemark;
	}

	public void setColumnRemark(String columnRemark) {
		this.columnRemark = columnRemark;
	}
}
