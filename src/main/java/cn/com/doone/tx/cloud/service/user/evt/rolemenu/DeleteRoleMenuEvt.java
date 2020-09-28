package cn.com.doone.tx.cloud.service.user.evt.rolemenu;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

/**
 * yecz 删除角色用户组关系
 */
public class DeleteRoleMenuEvt extends BaseEvt {

	/** 角色Id */
	@Field(value = "角色ID", length = 10, nullable = true)
	private Long roleId;

	/** 菜单id */
	@Field(value = "菜单ID", length = 10, nullable = true)
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
