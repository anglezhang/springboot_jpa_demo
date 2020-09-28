package cn.com.doone.tx.cloud.service.user.bean;

import cn.com.doone.tx.cloud.tool.hibernate.repository.WebBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="tl_login_log")
@SequenceGenerator(name="common_generator",sequenceName="s_tl_login_log",allocationSize = 1,initialValue = 1)
public class LoginLogBean extends WebBaseEntity implements Serializable {
    //登录帐号
    @Column(name="ACCOUNT",length = 64,nullable = true)
    private String account;
    //账号类型：1-后台；2-前台
    @Column(name="ACCOUNT_TYPE",length = 2,nullable = true)
    private String accountType;
    //登录方式，字典配置
    @Column(name="LOGIN_TYPE",length = 10,nullable = true)
    private String loginType;
    //登录账号ID(userId)
    @Column(name="ACCOUNT_ID",length = 10,nullable = true)
    private String accountId;
    //登录账号名称(userName)
    @Column(name="ACCOUNT_NAME",length = 100,nullable = true)
    private String accountName;
    //登录号码类型 1:固话 3:宽带 9:ITV 50:手机 QQ:QQ WX:微信 REGIST:注册账号
    @Column(name="ACC_NBR_TYPE",length = 20,nullable = true)
    private String accNbrType;
    //地市编码
    @Column(name="CITY_CODE",length = 6,nullable = true)
    private String cityCode;
    //登录IP
    @Column(name="LOGIN_IP",length = 20,nullable = true)
    private String loginIp;
    //入口渠道编码
    @Column(name="LOGIN_CHANNEL_CODE",length = 100,nullable = true)
    private String loginChannelCode;
    //登录渠道编码
    @Column(name="CHANNEL_CODE",length = 100,nullable = true)
    private String channelCode;
    //登录终端 pc:pc端 mobile:手机端 pad:平板
    @Column(name="LOGIN_CLIENT",length = 100,nullable = true)
    private String loginClient;
    //登录浏览器
    @Column(name="LOGIN_BROWSER",length = 1000,nullable = true)
    private String loginBrowser;
    //手机设备信息
    @Column(name="DEVICE_NO",length = 1000,nullable = true)
    private String deviceNo;
    //登录状态 S：成功 F：失败
    @Column(name="LOGIN_STATUS",length = 2,nullable = true)
    private String loginStatus;
    //登录结果说明
    @Column(name="LOGIN_RESULT",length = 1000,nullable = true)
    private String loginResult;
    //登录时间
    @Column(name="LOGIN_TIME",nullable = true)
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

    @Override
    public String toString() {
        return "LoginLogBean{" +
                "account='" + account + '\'' +
                ", accountType='" + accountType + '\'' +
                ", loginType='" + loginType + '\'' +
                ", accountId='" + accountId + '\'' +
                ", accountName='" + accountName + '\'' +
                ", accNbrType='" + accNbrType + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", loginIp='" + loginIp + '\'' +
                ", loginChannelCode='" + loginChannelCode + '\'' +
                ", channelCode='" + channelCode + '\'' +
                ", loginClient='" + loginClient + '\'' +
                ", loginBrowser='" + loginBrowser + '\'' +
                ", deviceNo='" + deviceNo + '\'' +
                ", loginStatus='" + loginStatus + '\'' +
                ", loginResult='" + loginResult + '\'' +
                ", loginTime=" + loginTime +
                '}';
    }
}
