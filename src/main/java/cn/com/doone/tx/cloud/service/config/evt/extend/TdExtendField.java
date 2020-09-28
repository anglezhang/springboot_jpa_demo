package cn.com.doone.tx.cloud.service.config.evt.extend;

import cn.com.doone.tx.cloud.tool.service.common.base.QueryEvt;

import java.util.Date;

/**
 * Created by loser on 2017/2/13.
 */
public class TdExtendField extends QueryEvt {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2067611542562814902L;

	private long id;

    private String tableName;
    
    private String extendCode;
    
    private String fieldName;

    private String fieldCode;

    private Integer fieldValueType;

    private Integer fieldValueLength;

    private String fieldTitle;

    private String fieldRemark;

    private Integer fieldType;

    private Integer sort;

    private Integer isMust;

    private String regularExpress;

    private String dictTableName;

    private String dictColumnName;

    private Long sysFlag;

    private Long colType;

    private Long formId;

    private Date createTime;
    private Date updateTime;
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldCode() {
        return fieldCode;
    }

    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode;
    }

    public Integer getFieldValueType() {
        return fieldValueType;
    }

    public void setFieldValueType(Integer fieldValueType) {
        this.fieldValueType = fieldValueType;
    }

    public Integer getFieldValueLength() {
        return fieldValueLength;
    }

    public void setFieldValueLength(Integer fieldValueLength) {
        this.fieldValueLength = fieldValueLength;
    }

    public String getFieldTitle() {
        return fieldTitle;
    }

    public void setFieldTitle(String fieldTitle) {
        this.fieldTitle = fieldTitle;
    }

    public String getFieldRemark() {
        return fieldRemark;
    }

    public void setFieldRemark(String fieldRemark) {
        this.fieldRemark = fieldRemark;
    }

    public Integer getFieldType() {
        return fieldType;
    }

    public void setFieldType(Integer fieldType) {
        this.fieldType = fieldType;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getIsMust() {
        return isMust;
    }

    public void setIsMust(Integer isMust) {
        this.isMust = isMust;
    }

    public String getRegularExpress() {
        return regularExpress;
    }

    public void setRegularExpress(String regularExpress) {
        this.regularExpress = regularExpress;
    }

    public String getDictTableName() {
        return dictTableName;
    }

    public void setDictTableName(String dictTableName) {
        this.dictTableName = dictTableName;
    }

    public String getDictColumnName() {
        return dictColumnName;
    }

    public void setDictColumnName(String dictColumnName) {
        this.dictColumnName = dictColumnName;
    }

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public Long getColType() {
        return colType;
    }

    public void setColType(Long colType) {
        this.colType = colType;
    }

    public Long getSysFlag() {
        return sysFlag;
    }

    public void setSysFlag(Long sysFlag) {
        this.sysFlag = sysFlag;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public String getExtendCode() {
		return extendCode;
	}

	public void setExtendCode(String extendCode) {
		this.extendCode = extendCode;
	}
}
