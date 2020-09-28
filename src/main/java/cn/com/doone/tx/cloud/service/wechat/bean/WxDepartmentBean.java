package cn.com.doone.tx.cloud.service.wechat.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatBaseEntity;
@Entity
@Table(name = "wx_department")
@SequenceGenerator(name = "common_generator", sequenceName = "s_wx_department", // oracle 用到的序列名 s_config_code
		allocationSize = 1, initialValue = 1) 
public class WxDepartmentBean  extends PlatBaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3777367622097256640L;
	
	@Column(name = "DEPARTMENT_NAME", length = 32, nullable = false)
	private String departmentName;
	
	@Column(name = "WX_DEPARTMENT_ID",length = 32,nullable = true)
    private Long wxDepartmentId;

    @Column(name = "PARENT_ID",length = 32,nullable = true)
    private Long parentId;
    
    @Column(name = "DEPARTMENT_ORDER",length = 10,nullable = true)
    private Integer departmentOrder;

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
    
    


}
