package cn.com.doone.tx.cloud.service.user.evt.staff;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;

import java.io.Serializable;

/**
 * yecz 查询用户菜单、按钮
 */
public class QueryStaffMenuEvt extends BaseEvt implements Serializable {

	private static final long serialVersionUID = 8135215791505407235L;

	// 用户id
	private Long staffId;

	// 菜单编码
	private String menuCode;
	
	/** 系统编码 */
	private String sysCode;

	/** 是否是超级管理员 */
	private String isSuperManager;
	
	private Integer isMenu;
	
	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getIsSuperManager() {
		return isSuperManager;
	}

	public void setIsSuperManager(String isSuperManager) {
		this.isSuperManager = isSuperManager;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public Integer getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(Integer isMenu) {
		this.isMenu = isMenu;
	}

}
