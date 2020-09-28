package cn.com.doone.tx.cloud.service.integral.bean;

import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatBaseEntity;
import io.swagger.models.auth.In;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="integral_type")
@NamedQuery(name="IntegralType.findAll", query="SELECT i FROM IntegralType i")
public class IntegralType extends PlatBaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    @Column(name="TYPE_NAME")
    private String typeName;
    @Column(name="STATUS")
    private String status;
    @Column(name="CREATOR")
    private Long creator;
    @Column(name="OPERATOR")
    private Long operator;
    @Column(name="CREATE_TIME")
    private Date createTime;
    @Column(name="UPDATE_TIME")
    private Date updateTime;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
    public Long getCreator() {
        return creator;
    }

    @Override
    public void setCreator(Long creator) {
        this.creator = creator;
    }

    @Override
    public Long getOperator() {
        return operator;
    }

    @Override
    public void setOperator(Long operator) {
        this.operator = operator;
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

        sb.append(", typeName=").append(typeName);
        /*sb.append(", status=").append();
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);*/

        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}