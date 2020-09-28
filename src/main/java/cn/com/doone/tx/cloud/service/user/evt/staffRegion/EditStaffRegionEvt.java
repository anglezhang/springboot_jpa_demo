package cn.com.doone.tx.cloud.service.user.evt.staffRegion;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;

import java.io.Serializable;

public class EditStaffRegionEvt extends BaseEvt implements Serializable{
	
	private static final long serialVersionUID = -7627240660982472011L;

	/**
	 * 主键ID
     */
    private Long id;
    
    /**
	 * 人员ID
	 */
	private Long staffId;
	
	/**
	 * 国家编码
	 */
	private Long countryCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public Long getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(Long countryCode) {
		this.countryCode = countryCode;
	}

	@Override
	public String toString() {
		return "EditStaffRegionEvt [id=" + id + ", staffId=" + staffId + ", countryCode=" + countryCode + "]";
	}

}
