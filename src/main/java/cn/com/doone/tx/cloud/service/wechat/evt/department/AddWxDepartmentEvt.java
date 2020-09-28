package cn.com.doone.tx.cloud.service.wechat.evt.department;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

public class AddWxDepartmentEvt extends BaseEvt {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8013733937629064940L;
	
	@Field(value = "部门名称", length = 32, nullable = false)
	private String departmentName;
	
	private Long wxDepartmentId;
	
	private Long parentId;
    
    private Integer departmentOrder;
	
	/** 创建人 */
	private Long creator;

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	

	public Long getWxDepartmentId() {
		return wxDepartmentId;
	}

	public void setWxDepartmentId(Long wxDepartmentId) {
		this.wxDepartmentId = wxDepartmentId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getDepartmentOrder() {
		return departmentOrder;
	}

	public void setDepartmentOrder(Integer departmentOrder) {
		this.departmentOrder = departmentOrder;
	}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}
	
	

}
