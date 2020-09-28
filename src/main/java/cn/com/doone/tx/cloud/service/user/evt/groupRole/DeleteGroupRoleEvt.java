package cn.com.doone.tx.cloud.service.user.evt.groupRole;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

import java.io.Serializable;

/**
 * yecz
 */
public class DeleteGroupRoleEvt extends BaseEvt implements Serializable {

	private static final long serialVersionUID = 5537477722643256419L;
	@Field(value = "角色ID", length = 10, nullable = false)
	private Long roleId;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}
