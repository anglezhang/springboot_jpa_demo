package cn.com.doone.tx.cloud.service.user.evt.staff;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

import java.io.Serializable;

public class EditStaffPwdEvt extends BaseEvt implements Serializable {
	private static final long serialVersionUID = -5538053306677506239L;

	@Field(value = "登录账号", length = 32, nullable = false)
	private String staffCode;

	@Field(value = "旧密码", length = 32, nullable = false)
	private String passWd;

	@Field(value = "新密码", length = 32, nullable = false)
	private String newPassWd;

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public String getPassWd() {
		return passWd;
	}

	public void setPassWd(String passWd) {
		this.passWd = passWd;
	}

	public String getNewPassWd() {
		return newPassWd;
	}

	public void setNewPassWd(String newPassWd) {
		this.newPassWd = newPassWd;
	}
}
