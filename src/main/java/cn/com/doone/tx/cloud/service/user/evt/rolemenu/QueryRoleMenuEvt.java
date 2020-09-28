package cn.com.doone.tx.cloud.service.user.evt.rolemenu;

import cn.com.doone.tx.cloud.tool.service.common.base.QueryEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

import java.io.Serializable;

/**
 * Created by yecz on 2017/2/16 0016.
 */
public class QueryRoleMenuEvt extends QueryEvt implements Serializable {

	private static final long serialVersionUID = 915276637107452346L;

	@Field(value = "角色ID", length = 10, nullable = true)
	private Long roleId;
	@Field(value = "菜单ID", length = 10, nullable = true)
	private Long menuId;
	
	private String languageType;

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

	public String getLanguageType() {
		return languageType;
	}

	public void setLanguageType(String languageType) {
		this.languageType = languageType;
	}
	
}
