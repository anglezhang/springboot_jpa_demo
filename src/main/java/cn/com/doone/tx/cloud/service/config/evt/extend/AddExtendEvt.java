package cn.com.doone.tx.cloud.service.config.evt.extend;

import java.util.List;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

/**
 * 添加扩展表入参 Created by loser on 2017/2/16.
 */
public class AddExtendEvt extends BaseEvt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3387429744302852601L;

	@Field(nullable = false, length = 32, value = "表名不能为空")
	// @Length(max = 32 , message = "表名最大度为:32")
	private String tableName;

	@Field(nullable = true, length = 32)
	private String extendCode;

	@Field(nullable = false, length = 32, value = "真实字段名不能为空")
	// @Length( max = 32 , message = "真实字段名最大度为:32")
	private List<String> fieldName;

	@Field(nullable = false, length = 32, value = "虚拟字段名不能为空")
	// @Length(max = 32 , message = "虚拟字段名最大度为:32")
	private List<String> fieldCode;

	@Field(nullable = false, length = 32, value = "字段类型不能为空")
	private List<String> fieldValueType;

	// @Length(max = 11 , message = "字段长度最大为11位")
	private List<String> fieldValueLength;

	private List<String> fieldTitle;

	private List<String> fieldRemark;

	@Field(nullable = false, length = 32, value = "字段组件类型不能为空")
	private List<String> fieldType;

	@Field(nullable = false, length = 32, value = "排序不能为空")
	private List<String> sort;

	@Field(nullable = false, length = 32, value = "字段是否必填不能为空")
	private List<String> isMust;

	private List<String> regularExpress;

	private List<String> dictTableName;

	private List<String> dictColumnName;

	@Field(nullable = false, length = 32, value = "状态是否必填不能为空")
	private List<String> statusa;

	// 扩展字段数量
	private String extendSize;

	public String getExtendSize() {
		return extendSize;
	}

	public void setExtendSize(String extendSize) {
		this.extendSize = extendSize;
	}

	public List<String> getStatusa() {
		return statusa;
	}

	public void setStatusa(List<String> statusa) {
		this.statusa = statusa;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<String> getFieldName() {
		return fieldName;
	}

	public void setFieldName(List<String> fieldName) {
		this.fieldName = fieldName;
	}

	public List<String> getFieldCode() {
		return fieldCode;
	}

	public void setFieldCode(List<String> fieldCode) {
		this.fieldCode = fieldCode;
	}

	public List<String> getFieldValueType() {
		return fieldValueType;
	}

	public void setFieldValueType(List<String> fieldValueType) {
		this.fieldValueType = fieldValueType;
	}

	public List<String> getFieldValueLength() {
		return fieldValueLength;
	}

	public void setFieldValueLength(List<String> fieldValueLength) {
		this.fieldValueLength = fieldValueLength;
	}

	public List<String> getFieldTitle() {
		return fieldTitle;
	}

	public void setFieldTitle(List<String> fieldTitle) {
		this.fieldTitle = fieldTitle;
	}

	public List<String> getFieldRemark() {
		return fieldRemark;
	}

	public void setFieldRemark(List<String> fieldRemark) {
		this.fieldRemark = fieldRemark;
	}

	public List<String> getFieldType() {
		return fieldType;
	}

	public void setFieldType(List<String> fieldType) {
		this.fieldType = fieldType;
	}

	public List<String> getSort() {
		return sort;
	}

	public void setSort(List<String> sort) {
		this.sort = sort;
	}

	public List<String> getIsMust() {
		return isMust;
	}

	public void setIsMust(List<String> isMust) {
		this.isMust = isMust;
	}

	public List<String> getRegularExpress() {
		return regularExpress;
	}

	public void setRegularExpress(List<String> regularExpress) {
		this.regularExpress = regularExpress;
	}

	public List<String> getDictTableName() {
		return dictTableName;
	}

	public void setDictTableName(List<String> dictTableName) {
		this.dictTableName = dictTableName;
	}

	public List<String> getDictColumnName() {
		return dictColumnName;
	}

	public void setDictColumnName(List<String> dictColumnName) {
		this.dictColumnName = dictColumnName;
	}

	public String getExtendCode() {
		return extendCode;
	}

	public void setExtendCode(String extendCode) {
		this.extendCode = extendCode;
	}
}
