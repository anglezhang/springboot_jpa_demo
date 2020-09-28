package cn.com.doone.tx.cloud.service.config.evt.sysParms;

import cn.com.doone.tx.cloud.tool.service.common.base.QueryEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * Created by yecz on 2017/2/16 0016.
 */
public class QuerySysParmEvt extends QueryEvt implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4262099298512035183L;
	/**主键*/
    private Long id;
    /**系统参数类型*/
    @Field(nullable = true , length = 256 ,value = "系统参数key")
    private String configType;
    /**系统参数key*/
    @Field(nullable = true , length = 256 ,value = "系统参数key")
    private String configKey;
    /**系统参数值*/
    @Field(nullable = true , length = 256 ,value = "系统参数value")
    private String configValue;
    /**参数类型0 key信息；1 key-value信息*/
    private Long type;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }
}
