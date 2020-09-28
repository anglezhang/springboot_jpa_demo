package cn.com.doone.tx.cloud.service.integral.evt;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;


import java.io.Serializable;
import java.util.Date;

/**
 * ClassName: IntegraRuleEvt <br/>
 * Description: <br/>
 * date: 2020/5/14 17:12<br/>
 *
 * @author zhangzhenxing<br />
 */
public class IntegraRuleEvt  extends BaseEvt implements Serializable {
    private static final long serialVersionUID = 4584633988079669332L;
    /**
     * 主键
     * */
    private Long id;

    /**
     * 规则名称
     * */
    private String ruleName;

    /**
     * 积分方式ID
     * */
    private Long integralTypeId;

    /**
     * 积分方式ID
     * */
    private Long integralValue;

    /**
     * 积分方式名称
     * */
    private String integralName;

    /**
     * 状态
     * */
    private String status;

    /**
     * 适用部门ID
     * */
    private String deptIds;

    /**
     * 适用部门name
     * */
    private String deptNames;

    /**
     * 创建时间
     * */
    private Date createTime;

    /**
     * 创建人
     * */
    private Long creator;

    /**
     * 操作时间
     * */
    private Date updateTime;

    /**
     * 操作人
     * */
    private Long operator;

    /**
     * 审核人
     * */
    private String toViewOperator;

    /**
     * 审核结果
     * */
    private String auditResult;

    /**
     * 审核意见
     * */
    private String auditRemark;

    /**
     * 审核时间
     * */
    private String auditTime;

    public String getToViewOperator() {
        return toViewOperator;
    }

    public void setToViewOperator(String toViewOperator) {
        this.toViewOperator = toViewOperator;
    }

    public String getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(String auditResult) {
        this.auditResult = auditResult;
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(String deptIds) {
        this.deptIds = deptIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public Long getIntegralTypeId() {
        return integralTypeId;
    }

    public String getIntegralName() {
        return integralName;
    }

    public void setIntegralName(String integralName) {
        this.integralName = integralName;
    }

    public void setIntegralTypeId(Long integralTypeId) {
        this.integralTypeId = integralTypeId;
    }

    public Long getIntegralValue() {
        return integralValue;
    }

    public void setIntegralValue(Long integralValue) {
        this.integralValue = integralValue;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public Long getOperator() {
        return operator;
    }

    @Override
    public void setOperator(Long operator) {
        this.operator = operator;
    }

    public String getDeptNames() {
        return deptNames;
    }

    public void setDeptNames(String deptNames) {
        this.deptNames = deptNames;
    }
}
