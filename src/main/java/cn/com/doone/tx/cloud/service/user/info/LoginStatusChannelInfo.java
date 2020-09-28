package cn.com.doone.tx.cloud.service.user.info;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017-9-8.
 */
public class LoginStatusChannelInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;

	private String channelCode;
	
	private Integer failNum;
	private Integer successNum;
	private Date createTime;
	private Date updateTime;
	private Date loginTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public Integer getFailNum() {
		return failNum;
	}
	public void setFailNum(Integer failNum) {
		this.failNum = failNum;
	}
	public Integer getSuccessNum() {
		return successNum;
	}
	public void setSuccessNum(Integer successNum) {
		this.successNum = successNum;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	
}
