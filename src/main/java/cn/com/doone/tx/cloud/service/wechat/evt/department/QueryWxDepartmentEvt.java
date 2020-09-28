package cn.com.doone.tx.cloud.service.wechat.evt.department;

import java.io.Serializable;

import cn.com.doone.tx.cloud.tool.service.common.base.QueryEvt;

public class QueryWxDepartmentEvt extends QueryEvt implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8289641259228982195L;
	
	private Long id;
	
	private Long wxDepartmentId;
	
	private Long parentId;
	
	private String departmentName;
    
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	

}
