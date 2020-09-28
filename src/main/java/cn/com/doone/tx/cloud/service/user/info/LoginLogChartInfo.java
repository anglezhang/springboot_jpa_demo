package cn.com.doone.tx.cloud.service.user.info;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-5-22.
 */
public class LoginLogChartInfo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7878033131018914079L;
	//登录次数
    private String loginTimes;
    //登录数
    private String loginUserCount;
    //渠道
    private String channelCode;
    //登录终端类型
    private String loginClient;
    //登录数
    private String loginCount;
    //登录浏览器
    private String loginBrowser;

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    //登录类型
    private String loginType;

    //城市
    private String cityCode;

    //手机端数量
    private String mobileNum;

    //pc端数量
    private String pcNum;

    public String getPcNum() {
        return pcNum;
    }

    public void setPcNum(String pcNum) {
        this.pcNum = pcNum;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getLoginTimes() {
        return loginTimes;
    }

    public void setLoginTimes(String loginTimes) {
        this.loginTimes = loginTimes;
    }

    public String getLoginUserCount() {
        return loginUserCount;
    }

    public void setLoginUserCount(String loginUserCount) {
        this.loginUserCount = loginUserCount;
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

    public String getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(String loginCount) {
        this.loginCount = loginCount;
    }

    public String getLoginBrowser() {
        return loginBrowser;
    }

    public void setLoginBrowser(String loginBrowser) {
        this.loginBrowser = loginBrowser;
    }
}
