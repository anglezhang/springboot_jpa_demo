package cn.com.doone.tx.cloud.service.user.info;

import java.io.Serializable;

/**
 * yecz
 * 角色用户组信息响应类
 */
public class RoleGroupInfo implements Serializable {


    private static final long serialVersionUID = -6979978121652527472L;
    //角色Id
    private Long roleId;
    //菜单Id
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
