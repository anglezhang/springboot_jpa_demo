package cn.com.doone.tx.cloud.service.user.evt.StaffRole;

import cn.com.doone.tx.cloud.tool.service.common.base.QueryEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

import java.io.Serializable;

/**
 * Created by liujx on 2017/2/17 0017.
 */
public class QueryStaffRoleEvt extends QueryEvt implements Serializable {

	private static final long serialVersionUID = -1799440112141546004L;
	// @NotNull(message = "用户id不能为空")
	@Field(value = "用户组ID", length = 10, nullable = false)
	private Long staffId;

	private Integer isGrant;
	
	private String languageType;
	
	//角色id
	private Long roleId;
	
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public Integer getIsGrant() {
		return isGrant;
	}

	public void setIsGrant(Integer isGrant) {
		this.isGrant = isGrant;
	}

	public String getLanguageType() {
		return languageType;
	}

	public void setLanguageType(String languageType) {
		this.languageType = languageType;
	}
	
}
