package cn.com.doone.tx.cloud.service.user.info;

import java.io.Serializable;
import java.util.Date;

public class StaffRegionInfo implements Serializable{

	private static final long serialVersionUID = 1894856198041714465L;
	
	/**主键ID*/
    private Long id;
    
    /** 创建时间*/
    private Date createTime;
    
    /** 创建人*/
    private Long creator;
    
    /**操作人*/
    private Long operator;
    
    /**修改时间*/
    private String updateTime;
    
	/**人员ID/
	private Long staffId;
	
	/** 国家编码*/
	private String countryCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public Long getOperator() {
		return operator;
	}

	public void setOperator(Long operator) {
		this.operator = operator;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Override
	public String toString() {
		return "StaffRegionInfo [id=" + id + ", createTime=" + createTime + ", creator=" + creator + ", operator="
				+ operator + ", updateTime=" + updateTime + ", countryCode=" + countryCode + "]";
	}

}
