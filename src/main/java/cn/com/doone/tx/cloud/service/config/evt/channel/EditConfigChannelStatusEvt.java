package cn.com.doone.tx.cloud.service.config.evt.channel;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

/**
 * Created by APPLE on 2017/2/22.
 */
public class EditConfigChannelStatusEvt extends BaseEvt{

    /**
	 * 
	 */
	private static final long serialVersionUID = -4628575322261193628L;
	@Field(nullable = false , length = 150 ,value = "id不能为空")
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "EditConfigChannelStatusEvt{" +
                "id=" + id +
                '}';
    }
}
