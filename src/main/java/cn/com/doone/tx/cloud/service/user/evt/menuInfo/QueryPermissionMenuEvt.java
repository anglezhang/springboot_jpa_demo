package cn.com.doone.tx.cloud.service.user.evt.menuInfo;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;

import java.io.Serializable;

/**
 * 查询用户有权限的菜单
 */
public class QueryPermissionMenuEvt extends BaseEvt implements Serializable {
	
	private static final long serialVersionUID = 5313903315457294155L;

	// 用户id
	private Long staffId;

	//是否根节点  1 是
	private String isRoot;

	/** 是否是超级管理员 */
	private String isSuperManager;

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public String getIsRoot() {
		return isRoot;
	}

	public void setIsRoot(String isRoot) {
		this.isRoot = isRoot;
	}

	public String getIsSuperManager() {
		return isSuperManager;
	}

	public void setIsSuperManager(String isSuperManager) {
		this.isSuperManager = isSuperManager;
	}

	@Override
	public String toString() {
		return "QueryPermissionMenuEvt [staffId=" + staffId + ", isRoot="
				+ isRoot + ", isSuperManager=" + isSuperManager + "]";
	}


}
