package cn.com.doone.tx.cloud.service.user.evt.StaffRole;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;

import java.io.Serializable;

/**
 * Created by liujx on 2017/2/17 0017.
 */
public class AddStaffRoleEvt extends BaseEvt implements Serializable {

	private static final long serialVersionUID = 211383566754409285L;
	/** 用户id */
	private Long staffId;

	private Long roleId;

	private Integer isGrant;

	/** 绑定的角色id */
	private Long[] ids;

	/** 授权的角色id */
	private Long[] grantIds;

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	public Long[] getGrantIds() {
		return grantIds;
	}

	public void setGrantIds(Long[] grantIds) {
		this.grantIds = grantIds;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Integer getIsGrant() {
		return isGrant;
	}

	public void setIsGrant(Integer isGrant) {
		this.isGrant = isGrant;
	}

}
