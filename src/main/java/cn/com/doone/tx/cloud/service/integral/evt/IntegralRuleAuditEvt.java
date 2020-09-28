package cn.com.doone.tx.cloud.service.integral.evt;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;

import java.io.Serializable;

/**
 * ClassName: IntegralRuleAuditEvt <br/>
 * Description: <br/>
 * date: 2020/5/17 15:58<br/>
 *
 * @author zhangzhenxing<br />
 */
public class IntegralRuleAuditEvt extends BaseEvt implements Serializable {
    private static final long serialVersionUID = 6872358587200289043L;
    private Integer querySize;
    private Integer queryPage;
    private Long id;
    private Long ruleTypeId;
    private Long integralRuleId;
    private String auditRemark;
    private String auditResult;
    private Long operator;

    public Integer getQuerySize() {
        return querySize;
    }

    public void setQuerySize(Integer querySize) {
        this.querySize = querySize;
    }

    public Integer getQueryPage() {
        return queryPage;
    }

    public void setQueryPage(Integer queryPage) {
        this.queryPage = queryPage;
    }

    public Long getRuleTypeId() {
        return ruleTypeId;
    }

    public void setRuleTypeId(Long ruleTypeId) {
        this.ruleTypeId = ruleTypeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIntegralRuleId() {
        return integralRuleId;
    }

    public void setIntegralRuleId(Long integralRuleId) {
        this.integralRuleId = integralRuleId;
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark;
    }

    public String getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(String auditResult) {
        this.auditResult = auditResult;
    }

    @Override
    public Long getOperator() {
        return operator;
    }

    @Override
    public void setOperator(Long operator) {
        this.operator = operator;
    }
}
