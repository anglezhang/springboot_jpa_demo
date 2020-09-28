package cn.com.doone.tx.cloud.service.integral.evt;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;

import java.io.Serializable;

/**
 * ClassName: IntegraDelEvt <br/>
 * Description: <br/>
 * date: 2020/5/9 18:37<br/>
 *
 * @author zhangzhenxing<br />
 */
public class IntegraDelEvt extends BaseEvt implements Serializable {
    private static final long serialVersionUID = -5998443147960813624L;
    /**
     * 积分类型主键
     * */
    private Long integraId;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getIntegraId() {
        return integraId;
    }

    public void setIntegraId(Long integraId) {
        this.integraId = integraId;
    }
}
