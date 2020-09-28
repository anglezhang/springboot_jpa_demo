package cn.com.doone.tx.cloud.service.wechat.evt.agent;

import java.io.Serializable;

import cn.com.doone.tx.cloud.tool.service.common.base.QueryEvt;

public class QueryWxAgentEvt extends QueryEvt implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8289641259228982195L;
	
	private Long id;
	
	private String wxCorpId;
	
	private String wxAgentId;
    
	private String agentName;
	
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
