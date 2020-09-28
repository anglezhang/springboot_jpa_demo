package cn.com.doone.tx.cloud.service.user.evt.roleGroup;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

/**
 * 增加角色用户组关系
 */
public class AddRoleGroupEvt extends BaseEvt {

	private static final long serialVersionUID = -2164518493414630881L;

	@Field(value = "角色ID", length = 10, nullable = true)
	private Long roleId;

	@Field(value = "用户组ID", length = 10, nullable = true)
	private Long groupId;

	/** 角色用户组id数组 */
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

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
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
