package cn.com.doone.tx.cloud.service.config.info;

import java.io.Serializable;
import java.util.Date;

public class XssRuleInfo implements Serializable{

		
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5916352314117685347L;

	private Long id;
	
	private Long xssConfigId;
	
	private String ruleCode;
	
	private String ruleFlag;
	
	private String ruleEncoding;
	
	private String ruleDesc;
	
	private String status;

	private Long operator;

	private Date createTime;

	private Date updateTime;

	private Long creator;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public Long getXssConfigId() {
		return xssConfigId;
	}

	public void setXssConfigId(Long xssConfigId) {
		this.xssConfigId = xssConfigId;
	}

	
	public String getRuleCode() {
		return ruleCode;
	}

	public void setRuleCode(String ruleCode) {
		this.ruleCode = ruleCode;
	}

	public String getRuleFlag() {
		return ruleFlag;
	}

	public void setRuleFlag(String ruleFlag) {
		this.ruleFlag = ruleFlag;
	}

	public String getRuleEncoding() {
		return ruleEncoding;
	}

	public void setRuleEncoding(String ruleEncoding) {
		this.ruleEncoding = ruleEncoding;
	}

	public String getRuleDesc() {
		return ruleDesc;
	}

	public void setRuleDesc(String ruleDesc) {
		this.ruleDesc = ruleDesc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getOperator() {
		return operator;
	}

	public void setOperator(Long operator) {
		this.operator = operator;
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

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}
	
	

}
