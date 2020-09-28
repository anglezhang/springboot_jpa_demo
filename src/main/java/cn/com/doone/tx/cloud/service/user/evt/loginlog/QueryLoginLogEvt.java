package cn.com.doone.tx.cloud.service.user.evt.loginlog;

import cn.com.doone.tx.cloud.tool.service.common.base.QueryEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-5-22.
 */
public class QueryLoginLogEvt extends QueryEvt implements Serializable {
	
	private static final long serialVersionUID = -1786418240640812647L;
	/**
	 * 统计开始时间
	 */
	//@Field(length = 12, nullable = true)
	private String startDate;
	/**
	 * 统计结束时间
	 */
	//@Field(length = 12, nullable = true)
	private String endDate;
	/**
	 * 登录渠道code
	 */
	@Field(length = 100, nullable = true)
	private String loginChannelCode;
	/**
	 * 渠道code
	 */
	@Field(length = 100, nullable = true)
	private String channelCode;
	/**
	 * 账号类型
	 */
	@Field(length = 20, nullable = true)
	private String accNbrType;
	/**
	 * 账号
	 */
	@Field(length = 64, nullable = true)
	private String account;
	/**
	 * 账号全称
	 */
	@Field(length = 64, nullable = true)
	private String accountAll;
	/**
	 * 账号类型
	 */
	@Field(length = 2, nullable = true)
	private String accountType;
	/**
	 * 地市
	 */
	@Field(length = 6, nullable = true)
	private String cityCode;

	/**
	 * 登录状态 S：成功 F：失败
	 */
	@Field(length = 2, nullable = true)
	private String loginStatus;

	/**
	 * 登录类型
	 */
	@Field(length = 2, nullable = true)
	private String loginType;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getAccNbrType() {
		return accNbrType;
	}

	public void setAccNbrType(String accNbrType) {
		this.accNbrType = accNbrType;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public String getAccountAll() {
		return accountAll;
	}

	public void setAccountAll(String accountAll) {
		this.accountAll = accountAll;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getLoginChannelCode() {
		return loginChannelCode;
	}

	public void setLoginChannelCode(String loginChannelCode) {
		this.loginChannelCode = loginChannelCode;
	}
	
}
