package cn.com.doone.tx.cloud.service.integral.bean;

import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatBaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * ClassName: IntegralRuleAudit <br/>
 * Description: <br/>
 * date: 2020/5/11 15:16<br/>
 *
 * @author zhangzhenxing<br />
 */
@Entity
@Table(name="integral_rule_audit")
@NamedQuery(name="IntegralRuleAudit.findAll", query="SELECT i FROM IntegralRuleAudit i")
public class IntegralRuleAudit extends PlatBaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Column(name="AUDIT_REMARK")
    private String auditRemark;

    @Column(name="AUDIT_RESULT")
    private String auditResult;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATE_TIME")
    private Date createTime;

    @Column(name="CREATOR")
    private Long creator;

    @Column(name="INTEGRAL_RULE_ID")
    private Long integralRuleId;

    @Column(name="OPERATOR")
    private Long operator;

    @Column(name = "STATUS")
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="UPDATE_TIME")
    private Date updateTime;


    public IntegralRuleAudit() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Long getIntegralRuleId() {
        return integralRuleId;
    }

    public void setIntegralRuleId(Long integralRuleId) {
        this.integralRuleId = integralRuleId;
    }

    public Long getOperator() {
        return operator;
    }

    public void setOperator(Long operator) {
        this.operator = operator;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
