package cn.com.doone.tx.cloud.service.config.evt.sysParms;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

/**
 * Created by yecz on 2017/2/16 0016.
 */
public class DeleteSysParmEvt  extends BaseEvt {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7205574816607457461L;

	/**主键*/
    @Field(nullable = false , length = 15 ,value = "系统参数ID")
    private Long id;

    /**系统参数key*/
    private String configKey;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getconfigKey() {
        return configKey;
    }

    public void setconfigKey(String configKey) {
        this.configKey = configKey;
    }
}
