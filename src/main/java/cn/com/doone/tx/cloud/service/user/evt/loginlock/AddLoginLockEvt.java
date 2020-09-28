package cn.com.doone.tx.cloud.service.user.evt.loginlock;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017-8-29.
 */
public class AddLoginLockEvt extends BaseEvt implements Serializable {

	@Field(length = 64, nullable = false, value = "账户")
	private String account;

	@Field(length = 2, nullable = false, value = "账户类型")
	private String accountType;

	@Field(nullable = true, value = "创建日期")
	private Date createTime;
	
	//适用类型 B:业务系统 A:APP
	private String applyType;
	
	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
