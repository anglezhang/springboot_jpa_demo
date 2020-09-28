package cn.com.doone.tx.cloud.service.user.evt.loginlock;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017-8-29.
 */
public class QueryLoginLockEvt extends BaseEvt implements Serializable {

	@Field(length = 64, nullable = false, value = "账户")
	private String account;

	@Field(length = 2, nullable = false, value = "账户类型")
	private String accountType;

	@Field(nullable = true, value = "锁定分钟数")
	private Integer lockMinutes;

	@Field(nullable = true, value = "锁定日期")
	private Date createTime;
	
	@Field(nullable = true, value = "锁定日期")
	private Date unlockTime;

	@Field(nullable = true, value = "登录失败次数")
	private Integer loginFailNum;
	
	private String applyType;
	
	private String lockTimeRule;
	
	//账号密码是否正确 Y正确 N错误
	private String isOkAccountPassword;
	
	public String getIsOkAccountPassword() {
		return isOkAccountPassword;
	}

	public void setIsOkAccountPassword(String isOkAccountPassword) {
		this.isOkAccountPassword = isOkAccountPassword;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Integer getLockMinutes() {
		return lockMinutes;
	}

	public void setLockMinutes(Integer lockMinutes) {
		this.lockMinutes = lockMinutes;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getLoginFailNum() {
		return loginFailNum;
	}

	public void setLoginFailNum(Integer loginFailNum) {
		this.loginFailNum = loginFailNum;
	}

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public Date getUnlockTime() {
		return unlockTime;
	}

	public void setUnlockTime(Date unlockTime) {
		this.unlockTime = unlockTime;
	}

	public String getLockTimeRule() {
		return lockTimeRule;
	}

	public void setLockTimeRule(String lockTimeRule) {
		this.lockTimeRule = lockTimeRule;
	}

}
