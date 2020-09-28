package cn.com.doone.tx.cloud.service.wechat.evt.user;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

public class AddWxUserEvt extends BaseEvt {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8487035288292760851L;
	
	@Field(value = "微信员工ID", length = 32, nullable = false)
	private String wxUserid;
	
	@Field(value = "姓名", length = 128, nullable = false)
	private String userName;
	
	private String gender;
	
	private String mobile;
	
	private String email;
	
	private Long departmentId;
	
	private String position;
	
	private String wxStatus;
		
	private Long creator;

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

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}
	
	

}
