package cn.com.doone.tx.cloud.service.config.info;

import java.io.Serializable;
import java.util.Date;

public class XssRegexInfo implements Serializable{

		
	private static final long serialVersionUID = -6543638681706020813L;

	private Long id;
	
	private Long xssRuleId;
	
	private String regexCode;
	
	private String regexValue;
	
	private String regexDesc;
	
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

	

	

	public Long getXssRuleId() {
		return xssRuleId;
	}

	public void setXssRuleId(Long xssRuleId) {
		this.xssRuleId = xssRuleId;
	}

	public String getRegexCode() {
		return regexCode;
	}

	public void setRegexCode(String regexCode) {
		this.regexCode = regexCode;
	}

	public String getRegexValue() {
		return regexValue;
	}

	public void setRegexValue(String regexValue) {
		this.regexValue = regexValue;
	}

	public String getRegexDesc() {
		return regexDesc;
	}

	public void setRegexDesc(String regexDesc) {
		this.regexDesc = regexDesc;
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
