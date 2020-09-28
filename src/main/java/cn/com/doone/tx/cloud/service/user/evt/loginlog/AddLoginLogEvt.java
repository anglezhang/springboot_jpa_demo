package cn.com.doone.tx.cloud.service.user.evt.loginlog;

import java.io.Serializable;
import java.util.Date;

public class AddLoginLogEvt implements Serializable {
    //登录帐号
    private String account;
    //账号类型：1-后台；2-前台
    private String accountType;
    //登录方式，字典配置
    private String loginType;
    //登录账号ID(userId)
    private String accountId;
    //登录账号名称(userName)
    private String accountName;
    //登录号码类型 1:固话 3:宽带 9:ITV 50:手机 QQ:QQ WX:微信 REGIST:注册账号
    private String accNbrType;
    //地市编码
    private String cityCode;
    //登录IP
    private String loginIp;
    //入口渠道编码
    private String loginChannelCode;
    //登录渠道编码
    private String channelCode;
    //登录终端 pc:pc端 mobile:手机端 pad:平板
    private String loginClient;
    //登录浏览器
    private String loginBrowser;
    //登录浏览器
    private String deviceNo;
    //登录状态 S：成功 F：失败
    private String loginStatus;
    //登录结果说明
    private String loginResult;
    //登录时间
    private Date loginTime;

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

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccNbrType() {
        return accNbrType;
    }

    public void setAccNbrType(String accNbrType) {
        this.accNbrType = accNbrType;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
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

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }
}
