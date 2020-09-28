package cn.com.doone.tx.cloud.service.user.info;



import java.io.Serializable;
import java.util.Date;

/**
 * yecz
 * 角色信息响应类
 */
public class RoleInfo implements Serializable {

    private static final long serialVersionUID = 7797629169757577973L;
    /**主键*/
    private Long id;
    /**角色名称*/
    private String roleName;
    /**角色说明*/
    private String roleRemark;
    /**是否模版角色 1是 0否*/
    private Long isDefault;
    /**状态*/
    private String status;
    /**创建时间*/
    private Date createTime;
    /**修改时间*/
    private Date updateTime;
    /**操作人员*/
    private Long operator;
    /**创建者*/
    private Long creator;
    /**创建者名称*/
    private String creatorName;

    private Integer isGrant;
    /**适用业务系统编码*/
    private String sysCode;
    /**适用业务系统名称*/
    private String sysName;

    public Integer getIsGrant() {
        return isGrant;
    }

    public void setIsGrant(Integer isGrant) {
        this.isGrant = isGrant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleRemark() {
        return roleRemark;
    }

    public void setRoleRemark(String roleRemark) {
        this.roleRemark = roleRemark;
    }

    public Long getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Long isDefault) {
        this.isDefault = isDefault;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getOperator() {
        return operator;
    }

    public void setOperator(Long operator) {
        this.operator = operator;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	@Override
    public String toString() {
        return "RoleInfo{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", roleRemark='" + roleRemark + '\'' +
                ", isDefault=" + isDefault +
                ", status='" + status + '\'' +
                ", createTime=" + createTime + '\'' +
                ", updateTime=" + updateTime + '\'' +
                ", operator=" + operator + '\'' +
                ", creator=" + creator + '\'' +
                ", creatorName='" + creatorName + '\'' +
                ", sysCode='" + sysCode + '\'' +
                ", sysName='" + sysName + '\'' +
                ", isGrant=" + isGrant +
                '}';
    }
}
