package cn.com.doone.tx.cloud.service.integral.bean;

import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatBaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="integral_rule")
@NamedQuery(name="IntegralRule.findAll", query="SELECT i FROM IntegralRule i")
public class IntegralRule extends PlatBaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATE_TIME")
    private Date createTime;

    @Column(name="CREATOR")
    private Long creator;

    @Column(name="INTEGRAL_TYPE_ID")
    private Long integralTypeId;

    @Column(name="INTEGRAL_VALUE")
    private Long integralValue;

    @Column(name="OPERATOR")
    private Long operator;

    @Column(name="RULE_NAME")
    private String ruleName;

    @Column(name = "STATUS")
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="UPDATE_TIME")
    private Date updateTime;

    public IntegralRule() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public Long getCreator() {
        return creator;
    }

    @Override
    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Long getIntegralTypeId() {
        return integralTypeId;
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
    public Long getOperator() {
        return operator;
    }

    @Override
    public void setOperator(Long operator) {
        this.operator = operator;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
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
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", ruleName=").append(ruleName);
        sb.append(", integralTypeId=").append(integralTypeId);
        sb.append(", integralValue=").append(integralValue);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", creator=").append(creator);
        sb.append(", operator=").append(operator);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}