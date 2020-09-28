package cn.com.doone.tx.cloud.service.wechat.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatBaseEntity;

@Entity
@Table(name = "wx_user")
@SequenceGenerator(name = "common_generator", sequenceName = "s_wx_user", 
		allocationSize = 1, initialValue = 1) 
public class WxUserBean   extends PlatBaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 192788745503428426L;
	
	@Column(name = "WX_USERID", length = 32, nullable = true)
	private String wxUserid;
	
	@Column(name = "USER_NAME", length = 128, nullable = false)
	private String userName;
	
	@Column(name = "GENDER", length = 2, nullable = true)
	private String gender;
	
	@Column(name = "MOBILE", length = 32, nullable = true)
	private String mobile;
	
	@Column(name = "EMAIL", length = 32, nullable = true)
	private String email;
	
	@Column(name = "DEPARTMENT_ID",length = 10,nullable = true)
    private Long departmentId;
	
	@Column(name = "POSITION", length = 256, nullable = true)
	private String position;
	
	@Column(name = "WX_STATUS", length = 2, nullable = true)
	private String wxStatus;

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
	
	

}
