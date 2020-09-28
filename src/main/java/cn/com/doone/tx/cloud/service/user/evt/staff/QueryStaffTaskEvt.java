package cn.com.doone.tx.cloud.service.user.evt.staff;

import cn.com.doone.tx.cloud.tool.service.common.base.QueryEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by haunghaung on 2018/7/6
 */
public class QueryStaffTaskEvt extends QueryEvt implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 866542045850439284L;
	/** 主键 */
	@Field(value = "登录账号", length = 10, nullable = true)
	private Long id;
	/** 登录账号 */

	/** 类型 */
	private String staffType;
	/** 状态 */
	private String status;
	/** 语言类型 */
	private String languageType;
	/** 用户组groupId */
	private Long groupId;
	/** 用户组groupIds 多个用户组id组成的字符串如："33,96,77" */
	private String groupIds;
	/** 角色roleId */
	private Long roleId;
	/** 角色roleIds 多个角色id组成的字符串如："33,96,77" */
	private String roleIds;

	/** 环节信息 */
	private List<Map<String, Object>> taskPms;

	public List<Map<String, Object>> getTaskPms() {
		return taskPms;
	}

	public void setTaskPms(List<Map<String, Object>> taskPms) {
		this.taskPms = taskPms;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(String groupIds) {
		this.groupIds = groupIds;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStaffType() {
		return staffType;
	}

	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLanguageType() {
		return languageType;
	}

	public void setLanguageType(String languageType) {
		this.languageType = languageType;
	}

}
