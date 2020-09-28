package cn.com.doone.tx.cloud.service.integral.evt;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;

import java.io.Serializable;

/**
 * ClassName: IntegralQueryEvt <br/>
 * Description: <br/>
 * date: 2020/5/8 10:21<br/>
 *
 * @author zhangzhenxing<br />
 */
public class IntegralQueryEvt extends BaseEvt implements Serializable {
    private static final long serialVersionUID = -8789984155288968965L;

    private Integer pageSize;
    private Integer pageNum;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    @Override
    public String toString() {
        return "IntegralQueryEvt{" +
                "pageSize=" + pageSize +
                ", pageNum=" + pageNum +
                '}';
    }
}
