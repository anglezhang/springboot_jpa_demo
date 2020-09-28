package cn.com.doone.tx.cloud.service.config.info;

import java.io.Serializable;
import java.util.Date;

public class XssConfigInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3395716534192855515L;
	
	private Long id;
	/** 检测机器的IP*/
	private String checkIp;
	/** 过滤规则 */
	private String filter;
	
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

	public String getCheckIp() {
		return checkIp;
	}

	public void setCheckIp(String checkIp) {
		this.checkIp = checkIp;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
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
