package cn.com.doone.tx.cloud.service.config.evt.sysParms;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

/**
 * Created by yecz on 2017/2/16 0016.
 */
public class AddSysParmEvt extends BaseEvt{

    /**
	 * 
	 */
	private static final long serialVersionUID = 3784859161409657551L;

	/**系统参数类型*/
    @Field(nullable = true , length = 15 ,value = "系统参数类型")
    private String configType;

    /**系统参数key*/
    @Field(nullable = true , length = 256 ,value = "系统参数key")
    private String configKey;

    /**系统参数value*/
    @Field(nullable = true , length = 256 ,value = "系统参数值")
    private String configValue;

    /**系统参数key说明*/
    @Field(nullable = true , length = 512 ,value = "系统参数key说明")
    private String configDesc;

    /**系统参数值说明*/
    @Field(nullable = true , length = 512 ,value = "系统参数值说明")
    private String valueDesc;

    /**系统参数值序号*/
    private Long sort;

    /**参数类型0 key信息；1 key-value信息*/
    private Long type;
    
    private Long creator;
    
    public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
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

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }
}
