package cn.com.doone.tx.cloud.service.integral.evt;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;
import com.alibaba.fastjson.JSONArray;

import java.io.Serializable;

/**
 * ClassName: IntegraAddEvt <br/>
 * Description: <br/>
 * date: 2020/5/11 9:49<br/>
 *
 * @author zhangzhenxing<br />
 */
public class IntegraAddEvt extends BaseEvt implements Serializable {
    private static final long serialVersionUID = -7385555207852225925L;
    private String edtiIntegraDataArray;

    public String getEdtiIntegraDataArray() {
        return edtiIntegraDataArray;
    }

    public void setEdtiIntegraDataArray(String edtiIntegraDataArray) {
        this.edtiIntegraDataArray = edtiIntegraDataArray;
    }
}
