package cn.com.doone.tx.cloud.service.wechat.evt.token;

import java.io.Serializable;

import cn.com.doone.tx.cloud.tool.service.common.base.QueryEvt;

public class QueryWxTokenEvt extends QueryEvt implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4731685958283566678L;
	
	private Long id;
	
	private String wxCorpId;
	
	private String wxAgentId;
    
	private String agentName;
	
	private String tokenType;
    
	private String accessToken;
	
	private Long tokenValidityTime;
	
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
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

	public Long getTokenValidityTime() {
		return tokenValidityTime;
	}

	public void setTokenValidityTime(Long tokenValidityTime) {
		this.tokenValidityTime = tokenValidityTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
