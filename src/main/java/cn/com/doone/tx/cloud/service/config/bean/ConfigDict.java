package cn.com.doone.tx.cloud.service.config.bean;

import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/1/19 0019.
 */
@Entity
@Table(name = "td_dict")
@SequenceGenerator(name = "common_generator", sequenceName = "s_td_dict", // oracle 用到的序列名 s_config_code
		allocationSize = 1, initialValue = 1) // 主键生成策略,sequenceName为s_表名,其他不用动
// mysql 的主键策略
public class ConfigDict extends PlatBaseEntity implements Serializable {

	private static final long serialVersionUID = 1331643739569790746L;

	@Column(name = "PARENT_ID", length = 11, nullable = true)
	private Integer parentId;
	@Column(name = "TABLE_NAME", length = 64, nullable = true)
	private String tableName;

	@Column(name = "COLUMN_NAME", length = 64, nullable = true)
	private String columnName;
	@Column(name = "COLUMN_REMARK", length = 64, nullable = true)
	private String columnRemark;

	@Column(name = "DICT_VALUE", length = 255, nullable = true)
	private String dictValue;
	@Column(name = "VALUE_REMARK", length = 255, nullable = true)
	private String valueRemark;
	@Column(name = "EXTEND_REMARK", length = 255, nullable = true)
	private String extendRemark;
	@Column(name = "SORT", length = 11, nullable = true)
	private Integer sort;
	@Column(name = "DICT_TYPE", length = 11, nullable = true)
	private Integer dictType;
	@Column(name = "STATUS", length = 16, nullable = true)
	private String status;
	@Column(name = "CREATE_TIME", nullable = true)
	private Date createTime;
	@Column(name = "UPDATE_TIME", nullable = true)
	private Date updateTime;
	@Column(name = "CENTER_DATABASE", length = 255, nullable = true)
	private String centerDatabase;
	@Column(name = "LANGUAGE_TYPE", length = 10, nullable = true)
	private String languageType;

	public String getLanguageType() {
		return languageType;
	}

	public void setLanguageType(String languageType) {
		this.languageType = languageType;
	}

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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
		return "ConfigDict [parentId=" + parentId + ", tableName=" + tableName + ", columnName=" + columnName
				+ ", columnRemark=" + columnRemark + ", dictValue=" + dictValue + ", valueRemark=" + valueRemark
				+ ", extendRemark=" + extendRemark + ", sort=" + sort + ", dictType=" + dictType + ", status=" + status
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + ", centerDatabase=" + centerDatabase
				+ ", languageType=" + languageType + ", toString()=" + super.toString() + "]";
	}

	public String getColumnRemark() {
		return columnRemark;
	}

	public void setColumnRemark(String columnRemark) {
		this.columnRemark = columnRemark;
	}
}
