package cn.com.doone.tx.cloud.service.wechat.evt.department;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;


public class EditWxDepartmentEvt extends BaseEvt {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6159820644924815440L;
	/** 主键 */
	@Field(value = "部门ID", length = 10, nullable = false)
	private Long id;
	
	private String departmentName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	

}
