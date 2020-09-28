package cn.com.doone.tx.cloud.service.user.evt.roleGroup;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

/**
 * yecz 删除角色用户组关系
 */
public class DeleteRoleGroupEvt extends BaseEvt {

	@Field(value = "角色ID", length = 10, nullable = true)
	private Long roleId;

	@Field(value = "用户组ID", length = 10, nullable = true)
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
