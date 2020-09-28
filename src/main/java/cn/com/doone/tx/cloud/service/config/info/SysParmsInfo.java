package cn.com.doone.tx.cloud.service.config.info;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统参数响应类
 * Created by yecz on 2017/2/22.
 */
public class SysParmsInfo implements Serializable {

    private static final long serialVersionUID = -2527748620570655536L;

    @Column(name="ID")
    private String id;
    @Column(name="CONFIG_TYPE")
    private String configType;
    @Column(name="CONFIG_KEY")
    private String configKey;
    @Column(name="CODE_VALUE")
    private String configValue;
    @Column(name="CONFIG_DESC")
    private String configDesc;
    @Column(name="VALUE_DESC")
    private String valueDesc;
    @Column(name="SORT")
    private Long sort;
    @Column(name="STATUS")
    private String status;
    @Column(name="OPERATOR")
    private Long operator;
    @Column(name="CREATE_TIME")
    private Date createTime;
    @Column(name="UPDATE_TIME")
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public String getConfigDesc() {
        return configDesc;
    }

    public void setConfigDesc(String configDesc) {
        this.configDesc = configDesc;
    }

    public String getValueDesc() {
        return valueDesc;
    }

    public void setValueDesc(String valueDesc) {
        this.valueDesc = valueDesc;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getOperator() {
        return operator;
    }

    public void setOperator(Long operator) {
        this.operator = operator;
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
}
