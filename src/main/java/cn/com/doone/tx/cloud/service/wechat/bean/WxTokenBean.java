package cn.com.doone.tx.cloud.service.wechat.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatBaseEntity;
@Entity
@Table(name = "wx_token")
@SequenceGenerator(name = "common_generator", sequenceName = "s_wx_token", // oracle 用到的序列名 s_config_code
		allocationSize = 1, initialValue = 1) 
public class WxTokenBean  extends PlatBaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4149084979395032476L;
	
	@Column(name = "WX_CORP_ID", length = 30, nullable = false)
	private String wxCorpId;
	
	@Column(name = "WX_AGENT_ID", length = 30, nullable = true)
	private String wxAgentId;
    
	@Column(name = "TOKEN_TYPE", length = 5, nullable = true)
    private String tokenType;
    
	@Column(name = "ACCESS_TOKEN", length = 30, nullable = true)
    private String accessToken;
    
	@Column(name = "TOKEN_VALIDITY_TIME", length = 20, nullable = true)
    private long tokenValidityTime;

	public String getWxCorpId() {
		return wxCorpId;
	}

	public void setWxCorpId(String wxCorpId) {
		this.wxCorpId = wxCorpId;
	}

	public String getWxAgentId() {
		return wxAgentId;
	}

	public void setWxAgentId(String wxAgentId) {
		this.wxAgentId = wxAgentId;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public long getTokenValidityTime() {
		return tokenValidityTime;
	}

	public void setTokenValidityTime(long tokenValidityTime) {
		this.tokenValidityTime = tokenValidityTime;
	}
	
	


}
