package cn.com.doone.tx.cloud.service.user.evt.role;

import cn.com.doone.tx.cloud.tool.service.common.base.QueryEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

import java.io.Serializable;

/**
 * Created by yecz on 2017/2/16 0016.
 */
public class QueryRoleEvt extends QueryEvt implements Serializable {

	private static final long serialVersionUID = -9036264265923229125L;
	/** 主键 */
	private Long id;

	/** 角色名称 */
	// @Length(max = 256 , message = "角色名称长度不能超过256")
	@Field(value = "角色名称", length = 256, nullable = true)
	private String roleName;

	/** 适用系统编码 */
	private String sysCode;
	/** 创建人 */
	private Long creator;
	/** 查询类型 */
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

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
}
