package cn.com.doone.tx.cloud.service.wechat.evt.corp;

import java.io.Serializable;

import cn.com.doone.tx.cloud.tool.service.common.base.QueryEvt;

public class QueryWxCorpEvt  extends QueryEvt implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4026443074223259983L;
	
	private Long id;
	
	private String wxCorpId;
    
	private String corpName;
	
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

	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
    

}
