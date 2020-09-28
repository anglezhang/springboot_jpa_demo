package cn.com.doone.tx.cloud.service.user.evt.StaffRole;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;

import java.io.Serializable;

/**
 * Created by liujx on 2017/2/20 0020.
 */
public class DeleteStaffRoleEvt extends BaseEvt implements Serializable {

	private static final long serialVersionUID = 2984985609627525516L;
	private Long staffId;

	private Long roleId;

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}
