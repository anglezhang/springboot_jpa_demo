package cn.com.doone.tx.cloud.service.user.bean;

import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by liujx on 2017/2/18 0018.
 * @Description: (后台用户组-角色关联表)
 */
@Entity
@Table(name = "tr_group_role")
@SequenceGenerator(name="common_generator",sequenceName="s_tr_group_role", // oracle 用到的序列名 s_tr_group_role
        allocationSize=1,initialValue=1) // 主键生成策略,sequenceName为s_表名,其他不用动
public class GroupRoleBean extends PlatBaseEntity implements Serializable{

    private static final long serialVersionUID = -5303840195730558544L;
    @Column(name = "GROUP_ID",length = 10,nullable = true)
    private Long groupId;

    @Column(name = "ROLE_ID",length = 10,nullable = true)
    private Long roleId;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "GroupRoleBean{" +
                "groupId=" + groupId +
                ", roleId=" + roleId +
                '}';
    }
}
