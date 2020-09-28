package cn.com.doone.tx.cloud.service.wechat.evt.user;

import java.io.Serializable;

import cn.com.doone.tx.cloud.tool.service.common.base.QueryEvt;

public class QueryWxUserEvt  extends QueryEvt implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4989095253194159975L;
	
	private Long id;
	
	private Long departmentId;
	
	private String wxUserid;
	
	private String userName;
	
	private String departmentName;
	
	private String gender;
	
	private String mobile;
	
	private String email;
	
	private String position;
    
	private String status;
	
	private String idStr;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getWxUserid() {
		return wxUserid;
	}

	public void setWxUserid(String wxUserid) {
		this.wxUserid = wxUserid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
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

	public String getIdStr() {
		return idStr;
	}

	public void setIdStr(String idStr) {
		this.idStr = idStr;
	}
	
	

}
