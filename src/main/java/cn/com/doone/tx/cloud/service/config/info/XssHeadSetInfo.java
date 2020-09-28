package cn.com.doone.tx.cloud.service.config.info;

import java.io.Serializable;
import java.util.Date;

public class XssHeadSetInfo implements Serializable{

		
	/**
	 * 
	 */
	private static final long serialVersionUID = -8261685694924002216L;
	
	private Long id;
	
	private Long xssConfigId;
	
	private String bType;
	
	private String sType;
	
	private String headValue;
	
	private String strLength;
	
	private String strExclude;
	
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

	public String getbType() {
		return bType;
	}

	public void setbType(String bType) {
		this.bType = bType;
	}

	public String getsType() {
		return sType;
	}

	public void setsType(String sType) {
		this.sType = sType;
	}

	public String getHeadValue() {
		return headValue;
	}

	public void setHeadValue(String headValue) {
		this.headValue = headValue;
	}

	public String getStrLength() {
		return strLength;
	}

	public void setStrLength(String strLength) {
		this.strLength = strLength;
	}

	public String getStrExclude() {
		return strExclude;
	}

	public void setStrExclude(String strExclude) {
		this.strExclude = strExclude;
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
