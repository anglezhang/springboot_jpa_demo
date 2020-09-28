package cn.com.doone.tx.cloud.service.user.bean;

import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * yecz
 */
@Entity
@Table(name = "ts_role_info")
@SequenceGenerator(name="common_generator",sequenceName="s_ts_role_info",
        allocationSize=1,initialValue=1)
public class RoleInfoBean extends PlatBaseEntity implements Serializable {

    private static final long serialVersionUID = 2852250524775186359L;

    /*@Column(name = "ID")
    private Long Id;*/
    /**角色名称*/
    @Column(name = "ROLE_NAME",length = 256,nullable = false)
    private String roleName;
    /**角色说明*/
    @Column(name = "ROLE_REMARK",length = 512,nullable = true)
    private String roleRemark;
    /**是否模版角色 1是 0否*/
    @Column(name = "IS_DEFAULT",length = 1,nullable = true)
    private Long isDefault;
    /**适用系统编码*/
    @Column(name = "SYS_CODE",length = 20,nullable = true)
    private String sysCode;
//    /**状态*/
//    @Column(name = "STATUS",length = 16,nullable = true)
//    private String status;
//    /**创建时间*/
//    @Column(name = "CREATE_TIME",length = 128,nullable = true)
//    private Date createTime;
//    /**修改时间*/
//    @Column(name = "UPDATE_TIME",length = 128,nullable = true)
//    private Date updateTime;
//    /**操作人员*/
//    @Column(name = "OPERATOR",length = 10,nullable = true)
//    private Long operator;
    /**创建人员*/
    @Column(name = "CREATOR",length = 10,nullable = true)
    private Long creator;

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

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	@Override
    public String toString() {
        return "RoleInfoBean{" +
                "roleName='" + roleName + '\'' +
                ", roleRemark='" + roleRemark + '\'' +
                ", sysCode='" + sysCode + '\'' +
                ", isDefault=" + isDefault +
                ", creator=" + creator +
                '}';
    }
}
