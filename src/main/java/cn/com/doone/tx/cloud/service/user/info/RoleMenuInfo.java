package cn.com.doone.tx.cloud.service.user.info;

import java.io.Serializable;

/**
 * yecz
 * 角色菜单信息响应类
 */
public class RoleMenuInfo implements Serializable {

    private static final long serialVersionUID = 7141229521783478741L;

    //角色Id
    private Long roleId;
    //菜单Id
    private Long menuId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}
