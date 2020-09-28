package cn.com.doone.tx.cloud.service.config.bean;
import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatBaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * @author wanggl
 * @version V1.0
 * @Title: ExtendFieldBean
 * @Package cn.com.doone.tx.cloud.config.service.bean
 * @Description: (用一句话描述该文件做什么)
 * @date 2017/3/28 10:31
 */
@Entity
@Table(name = "td_extend_field")
@SequenceGenerator(name="common_generator",sequenceName="s_field_id", allocationSize=1,initialValue=1)
public class ExtendFieldBean extends PlatBaseEntity implements Serializable {
    private static final long serialVersionUID = 2235901224398156680L;
    /** 表名 */
    @Column(name = "TABLE_NAME" , length = 32)
    private String tableName;
    @Column(name = "EXTEND_CODE" , length = 32)
    private String extendCode;
    /** 字段名称 Field1到Field30 */
    @Column(name ="FIELD_NAME" , length = 32)
    private String fieldName;
    /** 字段编码 例如:sex,cardNo等 */
    @Column(name = "FIELD_CODE" ,length = 32)
    private String fieldCode;
    /** 值类型 */
    @Column(name = "FIELD_VALUE_TYPE" , length = 11)
    private Integer fieldValueType;
    /** 值长度 */
    @Column(name = "FIELD_VALUE_LENGTH" ,length = 11)
    private Integer fieldValueLength;
    /** 字段标题 */
    @Column(name = "FIELD_TITLE" ,length = 64)
    private String fieldTitle;
    /** 字典说明 */
    @Column(name = "FIELD_REMARK" , length = 256)
    private String fieldRemark;
    /** 字段类型 1 text 2 radio 3 checkbox 4 select 5 file 6 date 7 textbox */
    @Column(name = "FIELD_TYPE" , length = 11)
    private Integer fieldType;
    /** 排序 */
    @Column(name = "SORT",length = 11)
    private Integer sort;
    /**  */
    @Column(name = "IS_MUST" , length = 2)
    private Integer isMust;
    /**  */
    @Column(name = "REGULAR_EXPRESS" ,length = 256)
    private String regularExpress;
    /** 字典表名 */
    @Column(name = "DICT_TABLE_NAME" , length = 32)
    private String dictTableName;
    /** 字典列名 */
    @Column(name = "DICT_COLUMN_NAME" , length = 32)
    private String dictColumnName;
    /**  */
    @Column(name = "SYS_FLAG")
    private Long sysFlag;
    /**  */
    @Column(name = "COL_TYPE")
    private Long colType;
    /**  */
    @Column(name = "FORM_ID")
    private Long formId;
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
    public Long getSysFlag() {
        return sysFlag;
    }
    public void setSysFlag(Long sysFlag) {
        this.sysFlag = sysFlag;
    }
    public Long getColType() {
        return colType;
    }
    public void setColType(Long colType) {
        this.colType = colType;
    }
    public Long getFormId() {
        return formId;
    }
    public void setFormId(Long formId) {
        this.formId = formId;
    }
	public String getExtendCode() {
		return extendCode;
	}
	public void setExtendCode(String extendCode) {
		this.extendCode = extendCode;
	}
}