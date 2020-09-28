package cn.com.doone.tx.cloud.service.user.evt.rolemenu;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

/**
 * yecz 增加角色菜单关系
 */
public class AddRoleMenuEvt extends BaseEvt {

	private static final long serialVersionUID = 9146915739564268564L;
	/** 角色id */
	@Field(value = "角色ID", length = 10, nullable = true)
	private Long roleId;
	/** 菜单id */
	@Field(value = "菜单ID", length = 10, nullable = true)
	private Long menuId;

	/** 角色菜单id数组 */
	private Long[] ids;
	
	private Long creator;
	
	private Long operator;
	
	private String status;

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

	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
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
	
}
