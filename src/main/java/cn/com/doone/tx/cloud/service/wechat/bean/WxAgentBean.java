package cn.com.doone.tx.cloud.service.wechat.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatBaseEntity;
@Entity
@Table(name = "wx_agent")
@SequenceGenerator(name = "common_generator", sequenceName = "s_wx_agent", // oracle 用到的序列名 s_config_code
		allocationSize = 1, initialValue = 1) 
public class WxAgentBean extends PlatBaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1278809316187074496L;
	
	@Column(name = "WX_CORP_ID", length = 30, nullable = false)
	private String wxCorpId;
	
	@Column(name = "WX_AGENT_ID", length = 30, nullable = false)
	private String wxAgentId;
    
	@Column(name = "AGENT_NAME", length = 100, nullable = true)
    private String agentName;
    
	@Column(name = "AGENT_DESCRIPTION", length = 200, nullable = true)
    private String agentDescription;
    
	@Column(name = "AGENT_SECRET", length = 100, nullable = true)
    private String agentSecret;

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

	public String getAgentDescription() {
		return agentDescription;
	}

	public void setAgentDescription(String agentDescription) {
		this.agentDescription = agentDescription;
	}

	public String getAgentSecret() {
		return agentSecret;
	}

	public void setAgentSecret(String agentSecret) {
		this.agentSecret = agentSecret;
	}
	
	

}
