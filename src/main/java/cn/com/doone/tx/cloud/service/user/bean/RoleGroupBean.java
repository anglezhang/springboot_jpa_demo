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
@Table(name = "tr_data_role")
@SequenceGenerator(name="common_generator",sequenceName="s_tr_data_role",
        allocationSize=1,initialValue=1)
public class RoleGroupBean extends PlatBaseEntity implements Serializable {

    private static final long serialVersionUID = -1054154352720098666L;
    /**角色id*/
    @Column(name = "ROLE_ID",length = 10,nullable = true)
    private Long roleId;
    /**用户组id*/
    @Column(name = "GROUP_ID",length = 10,nullable = true)
    private Long groupId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
