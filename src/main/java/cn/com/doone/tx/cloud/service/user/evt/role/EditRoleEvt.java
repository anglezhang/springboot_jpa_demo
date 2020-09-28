package cn.com.doone.tx.cloud.service.user.evt.role;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

/**
 * Created by yecz on 2017/2/16 0016.
 */
public class EditRoleEvt extends BaseEvt {

	/** 主键 */
	@Field(value = "角色ID", length = 10, nullable = false)
	private Long id;
	/** 角色名称 */
	@Field(value = "角色名称", length = 256, nullable = true)
	private String roleName;
	/** 角色说明 */
	@Field(value = "角色说明", length = 512, nullable = true)
	private String roleRemark;
	/** 适用系统编码 */
	@Field(value = "适用系统编码", length = 20, nullable = true)
	private String sysCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleRemark() {
		return roleRemark;
	}

	public void setRoleRemark(String roleRemark) {
		this.roleRemark = roleRemark;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

}
