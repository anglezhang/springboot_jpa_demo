package cn.com.doone.tx.cloud.service.integral.evt;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName: IntegralTypeQueryEvt <br/>
 * Description: <br/>
 * date: 2020/5/12 18:32<br/>
 *
 * @author zhangzhenxing<br />
 */
public class IntegralTypeQueryEvt extends BaseEvt implements Serializable {
    private static final long serialVersionUID = -7687387229829291096L;
    private Integer pageSize;
    private Integer pageNum;
    //积分类型
    private Long integralTypeId;
    private String ruleName;
    //值范围
    private Long maxIntegralValue;
    private Long minIntegralValue;
    //积分适配部门
    private List<Long> deptIds;

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

    public Long getIntegralTypeId() {
        return integralTypeId;
    }

    public void setIntegralTypeId(Long integralTypeId) {
        this.integralTypeId = integralTypeId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public Long getMaxIntegralValue() {
        return maxIntegralValue;
    }

    public void setMaxIntegralValue(Long maxIntegralValue) {
        this.maxIntegralValue = maxIntegralValue;
    }

    public Long getMinIntegralValue() {
        return minIntegralValue;
    }

    public void setMinIntegralValue(Long minIntegralValue) {
        this.minIntegralValue = minIntegralValue;
    }

    public List<Long> getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(List<Long> deptIds) {
        this.deptIds = deptIds;
    }
}
