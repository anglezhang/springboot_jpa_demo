package cn.com.doone.tx.cloud.service.user.evt.role;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

/**
 * Created by yecz on 2017/2/16 0016.
 */
public class AddRoleEvt extends BaseEvt {

	private static final long serialVersionUID = -3087104945002034496L;
	/** 角色名称 */
	// @Length(max = 256 , message = "角色名称长度不能超过256")
	@Field(value = "角色名称", length = 256, nullable = false)
	private String roleName;
	/** 角色说明 */
	// @Length(max = 512 , message = "角色名称长度不能超过512")
	@Field(value = "角色名称", length = 512, nullable = true)
	private String roleRemark;
	/** 适用系统编码 */
	private String sysCode;
	/** 创建人 */
	private Long creator;

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

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	@Override
	public String toString() {
		return "AddRoleEvt{" + "roleName='" + roleName + '\''
				+ ", roleRemark='" + roleRemark + '\'' + ", sysCode="
				+ sysCode + '\'' + ", creator=" + creator + '}';
	}
}
