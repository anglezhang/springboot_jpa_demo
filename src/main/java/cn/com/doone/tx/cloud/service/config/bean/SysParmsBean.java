package cn.com.doone.tx.cloud.service.config.bean;

import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 系统参数bean
 * yecz
 */
@Entity
@Table(name = "td_config")
@SequenceGenerator(name="common_generator",sequenceName="s_td_config", // oracle 用到的序列名 s_config_code
        allocationSize=1,initialValue=1) // 主键生成策略,sequenceName为s_表名,其他不用动
// mysql 的主键策略
public class SysParmsBean extends PlatBaseEntity implements Serializable {

    private static final long serialVersionUID = 3875514625151142025L;

    @Column(name="CONFIG_TYPE",length = 10,nullable = true)
    private String configType;
    @Column(name="CONFIG_KEY",length = 256,nullable = true)
    private String configKey;
    @Column(name="CONFIG_VALUE",length = 256,nullable = true)
    private String configValue;
    @Column(name="CONFIG_DESC",length = 512,nullable = true)
    private String configDesc;
    @Column(name="VALUE_DESC",length = 512,nullable = true)
    private String valueDesc;
    @Column(name="SORT",length = 10,nullable = true)
    private Long sort;
    @Column(name="TYPE",length = 10,nullable = true)
    private Long type;


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

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SysParmsBean{" +
                "configType='" + configType + '\'' +
                ", configKey='" + configKey + '\'' +
                ", configValue='" + configValue + '\'' +
                ", configDesc='" + configDesc + '\'' +
                ", valueDesc='" + valueDesc + '\'' +
                ", sort=" + sort +
                ", type=" + type +
                '}';
    }
}
