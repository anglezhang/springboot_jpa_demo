package cn.com.doone.tx.cloud.service.wechat.evt.user;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;

public class EditWxUserEvt extends BaseEvt {

		
	/**
	 * 
	 */
	private static final long serialVersionUID = 5180921882857304945L;
	
	private Long id;

	private String userName;
	
	private String gender;
	
	private String mobile;
	
	private String email;
	
	private Long departmentId;
	
	private String position;
	
	private String wxStatus;
	
	private String idStr;
		
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getWxStatus() {
		return wxStatus;
	}

	public void setWxStatus(String wxStatus) {
		this.wxStatus = wxStatus;
	}

	public String getIdStr() {
		return idStr;
	}

	public void setIdStr(String idStr) {
		this.idStr = idStr;
	}

	
}
