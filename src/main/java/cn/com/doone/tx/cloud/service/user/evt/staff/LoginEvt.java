package cn.com.doone.tx.cloud.service.user.evt.staff;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

import java.io.Serializable;

/**
 * 登录接口入参evt yecz.
 */
public class LoginEvt extends BaseEvt implements Serializable {

	private static final long serialVersionUID = -2798459979683527838L;

	/** 登录账号 */
	// @Length(min = 3 , max = 32 , message = "登录账号长度范围为:3~32")
	@Field(value = "登录账号", length = 32, nullable = false)
	private String staffCode;

	@Field(value = "密码", length = 32, nullable = false)
	private String passWd;
	
	/** 系统编码*/
	private String sysCode;
	/** 账号类型：1-后台；2-前台*/
	private String accountType;
	/** 登录方式*/
	private String loginType;
	/** 登录IP*/
	private String loginIp;
	/** 登录终端 pc:pc端 mobile:手机端 pad:平板*/
	private String loginClient;
	/** 登录浏览器*/
	private String loginBrowser;
	/** 登录状态  S：成功  F：失败*/
	private String loginStatus;
	/** 登录结果说明*/
	private String loginResult;
	/** 入口渠道编码*/
	private String loginChannelCode;
	/** 登录渠道编码*/
	private String channelCode;
	/** 语言类型*/
	private String languageType;

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

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public String getLanguageType() {
		return languageType;
	}

	public void setLanguageType(String languageType) {
		this.languageType = languageType;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getLoginClient() {
		return loginClient;
	}

	public void setLoginClient(String loginClient) {
		this.loginClient = loginClient;
	}

	public String getLoginBrowser() {
		return loginBrowser;
	}

	public void setLoginBrowser(String loginBrowser) {
		this.loginBrowser = loginBrowser;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public String getLoginResult() {
		return loginResult;
	}

	public void setLoginResult(String loginResult) {
		this.loginResult = loginResult;
	}

	public String getLoginChannelCode() {
		return loginChannelCode;
	}

	public void setLoginChannelCode(String loginChannelCode) {
		this.loginChannelCode = loginChannelCode;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	
}
